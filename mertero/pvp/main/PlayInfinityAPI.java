package de.mertero.pvp.main;

import java.util.ArrayList;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.sql.*;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


/**
* Created by HerrPrefix on 08.01.2017.
* <p>
* load() Methode der RangAPI bitte im onEnable ausführen!
* MySQL-Verbindungen bitte im onDisable disconnecten!
*/
public class PlayInfinityAPI {

    private static RangAPI rang;
    private static PlayerAPI player;
    private ChatClient client;
    private MySQL mysql;

    private ArrayList<String> tosend = new ArrayList<>();
    private String prefix = "§bPlayInfinity §8● ";
    private String name, netty_host;
    private Integer netty_port;

    public PlayInfinityAPI(String name, String host, String database, String user, String password, String netty_host, Integer netty_port) {
        if (rang != null || player != null) {
            throw new RuntimeException("API was already assigned!");
        }

        this.name = name;
        this.netty_host = netty_host;
        this.netty_port = netty_port;

        mysql = new MySQL(host, database, user, password, 3306);

        rang = new RangAPI();
        player = new PlayerAPI();
        rang.load();

        new Thread(() -> start()).start();

    }

    public RangAPI getRangAPI() {
        return rang;
    }

    public PlayerAPI getPlayerAPI() {
        return player;
    }

    public ChatClient getClientChat() {
        return client;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }

    public MySQL getMySQL() {
        return mysql;
    }

    /**
     * @param: String msg, Nachricht die per Netty verschickt werden soll
     */
    public void addMessage(String msg) {
        tosend.add(msg);
    }

    public void start() {
        while (true) {
            try {
                client = new ChatClient(netty_host, netty_port);
                client.run();
                client.stop();
            } catch (Exception e) {
                System.out.println("Netty Connection closed!");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                }
            }
        }
    }

    /**
     * Stoppe die MySQL-Verbindungen aller APIs
     */
    public void disconnect() {
        mysql.disconnect();
        client.stop();
    }
    
    public void setTab(Player p) {

        PlayInfinityAPI.PlayerAPI.InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(p.getName());
        PlayInfinityAPI.RangAPI.Rang rang = ip.getRang();

        String id = rang.getID() + "";

        while (id.length() < 3) {
            id = "0" + id;
        }

        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = sb.getTeam(id + rang.getName());

        if (team == null) {
            team = sb.registerNewTeam(id + rang.getName());
        }

        String rangname = rang.getTab();

        if (rang.getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("JrDeveloper").getPermission() || rang.getName().equals("Hoster")) {
            String rn = rang.getName().replace("Moderator", "Mod").replace("Supporter", "Sup").replace("Administrator", "Admin").replace("Developer", "Dev");
            rangname = rangname + rn + " §8⎜ " + rang.getTab();
        }

        team.setPrefix(rangname);
        team.addEntry(p.getName());

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(sb);
        }
    }

    public class MySQL {

        private String host, database, user, password;
        private Integer port, currentcon;
        private Connection c1, c2;

        public MySQL(String host, String database, String user, String password, Integer port) {

            this.host = host;
            this.database = database;
            this.user = user;
            this.password = password;
            this.port = port;

            c1 = connect();
            c2 = connect();

            currentcon = 1;

        }

        public Connection connect() {
            try {
                return DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.user, this.password);
            } catch (Exception localException) {
            	localException.printStackTrace();
            }
            return null;
        }

        public Connection getConnection() {
            switch (currentcon) {
                case 1:
                    currentcon = 2;
                    return c1;
                case 2:
                    currentcon = 1;
                    return c2;
            }
            return null;
        }

        public void close(Connection con) {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void disconnect() {
            try {
                c1.close();
                c2.close();
            } catch (SQLException e) {
            }
        }

        public void update(String qry) {
            try {
                Statement st = getConnection().createStatement();
                st.executeUpdate(qry);
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public ResultSet query(String qry) {

            ResultSet rs = null;

            try {
                Statement st = getConnection().createStatement();
                rs = st.executeQuery(qry);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rs;
        }
    }


    public class RangAPI {

        private ArrayList<Rang> ranks = new ArrayList<>();

        public class Rang {

            private String name;
            private Integer id;
            private Integer perm;
            private String chat;
            private String tab;

            public Rang(String name, Integer id, Integer perm, String chat, String tab) {
                this.name = name;
                this.id = id;
                this.perm = perm;
                this.chat = chat;
                this.tab = tab;
            }

            public String getName() {
                return name;
            }

            public Integer getID() {
                return id;
            }

            public Integer getPermission() {
                return perm;
            }

            public String getChat() {
                return chat;
            }

            public String getTab() {
                return tab;
            }
        }

        public ArrayList<Rang> getRanks() {
            return ranks;
        }

        /**
         * Lädt alle Ränge
         * Bitte in der onEnable Methode ausführen!
         */
        public void load() {

            ResultSet rs = mysql.query("SELECT * FROM ranks");

            try {
                ranks.clear();
                while (rs.next()) {
                    ranks.add(new Rang(rs.getString("name"), rs.getInt("id"), rs.getInt("perm"), rs.getString("chat").replaceAll("&", "§"), rs.getString("tab").replaceAll("&", "§")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * @param: String name, Eindeutiger Rangname
         * @return: Rang mit den Namen name
         */
        public Rang getRankByName(String name) {
            for (Rang rang : getRanks()) {
                if (rang.getName().equalsIgnoreCase(name)) {
                    return rang;
                }
            }
            return null;
        }

        /**
         * @param: Integer id, Eindeutige Rangid
         * @return: Rang mit der ID id
         */
        public Rang getRankByID(Integer id) {
            for (Rang rang : getRanks()) {
                if (rang.getID() == id) {
                    return rang;
                }
            }
            return null;
        }
    }

    public class PlayerAPI {

        private HashMap<String, InfinityPlayer> infinityplayer = new HashMap<>();

        public class InfinityPlayer {

            private RangAPI.Rang rang;
            private String ip;
            private Integer infinitys, banpoints;
            private Long time, online;
            private Boolean nick;

            public InfinityPlayer(RangAPI.Rang rang, Integer infinitys, Long time, Long online, String ip, Boolean nick, Integer banpoints) {
                this.rang = rang;
                this.infinitys = infinitys;
                this.time = time;
                this.online = online;
                this.ip = ip;
                this.nick = nick;
                this.banpoints = banpoints;
            }

            public RangAPI.Rang getRang() {
                return rang;
            }

            public Integer getInfinitys() {
                return infinitys;
            }

            public Long getTime() {
                return time;
            }

            public Long getOnline() {
                return online;
            }

            public String getIP() {
                return ip;
            }

            public Boolean getNick() {
                return nick;
            }

            public Integer getBanPoints() {
                return banpoints;
            }

            public void setRang(RangAPI.Rang rang) {
                this.rang = rang;
            }

            public void setInfinitys(Integer infinitys) {
                this.infinitys = infinitys;
            }

            public void setTime(Long time) {
                this.time = time;
            }

            public void setNick(Boolean nick) {
                this.nick = nick;
            }
        }

        /**
         * @param: String name, der Name des Spielers der registriert sein soll
         * @return: Boolean ob der Spieler in der Datenbank vorhanden ist
         */
        public boolean isRegistered(String name) {

            ResultSet rs = mysql.query("SELECT * FROM players WHERE name = '" + name + "' LIMIT 1");

            try {
                return rs.next();
            } catch (SQLException e) {
            }
            return false;
        }

        /**
         * @param: String name, der Name des zu registrierenden Spielers
         */
        public void registerPlayer(String name) {
            mysql.update("INSERT INTO players(name, rank, infinitys, ranktime, online, ip, nick, banpoints) VALUES ('" + name + "', 'Spieler', 0, -1, -1, '-1', false, 0)");
        }

        /**
         * @param: String name, der Name des Spielers
         * @return: Boolean ob der Spieler geladen wurde
         */
        public boolean isLoaded(String name) {
            return infinityplayer.containsKey(name);
        }

        public HashMap<String, InfinityPlayer> getPlayers() {
            return infinityplayer;
        }

        /**
         * @param: String name, der Spielername der geladen werden soll
         * Am besten im Join oder AsyncLogin Event ausführen!
         */
        public void loadPlayer(String name) {
            if (infinityplayer.containsKey(name)) {
                infinityplayer.remove(name);
            }

            ResultSet rs = mysql.query("SELECT * FROM players WHERE name = '" + name + "' LIMIT 1");

            try {
                while (rs.next()) {
                    infinityplayer.put(name, new InfinityPlayer(getRangAPI().getRankByName(rs.getString("rank")), rs.getInt("infinitys"), rs.getLong("ranktime"), rs.getLong("online"), rs.getString("ip"), rs.getBoolean("nick"), rs.getInt("banpoints")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * @param: String name, Name des Spielers
         * @return: InfinityPlayer
         */
        public InfinityPlayer getPlayerFromDB(String name) {

            ResultSet rs = mysql.query("SELECT * FROM players WHERE name = '" + name + "' LIMIT 1");

            try {
                while (rs.next()) {
                    return new InfinityPlayer(getRangAPI().getRankByName(rs.getString("rank")), rs.getInt("infinitys"), rs.getLong("ranktime"), rs.getLong("online"), rs.getString("ip"), rs.getBoolean("nick"), rs.getInt("banpoints"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * @param: InfinityPlayer p, der Spieler dessen Rang überprüft werden soll
         * @return: Boolean ob die Rangzeit noch nicht ausgelaufen ist
         */
        public boolean checkRang(InfinityPlayer p) {
            if (p.getTime() != -1L && p.getTime() <= System.currentTimeMillis()) {
                return true;
            }
            return false;
        }

        /**
         * @param: String name, Name des Spielers
         * @param: String rang, Rang der gesetzt werden soll
         * @param: String time, Zeit für den der Rang vergeben werden soll (Also Long + CurrentMillis oder -1L für Permanent)
         */
        public void setRangToDB(String name, RangAPI.Rang rang, Long time) {
            mysql.update("UPDATE players SET rank = '" + rang.getName() + "', ranktime = " + time + " WHERE name = '" + name + "'");
        }

        /**
         * @param: String name, Name des Spielers
         * @param: Integer infinitys, Infinitys die gesetzt werden sollen
         */
        public void setInfinitysToDB(String name, Integer infinitys) {
            mysql.update("UPDATE players SET infinitys = " + infinitys + " WHERE name = '" + name + "'");
        }

        /**
         * @param: String name, Name des Spielers
         * @param: Long online, Letzter Login
         */
        public void setOnlineToDB(String name, Long online) {
            mysql.update("UPDATE players SET online = " + online + " WHERE name = '" + name + "'");
        }

        /**
         * @param: String name, Name des Spielers
         * @param: String ip, Letzte IP
         */
        public void setIPToDB(String name, String ip) {
            mysql.update("UPDATE players SET ip = '" + ip + "' WHERE name = '" + name + "'");
        }

        /**
         * @param: String name, Name des Spielers
         * @param: Boolean nick, Autonick
         */
        public void setNickToDB(String name, Boolean nick) {
            mysql.update("UPDATE players SET nick = " + (nick ? 1 : 0) + " WHERE name = '" + name + "'");
        }

        /**
         * @param: String name, Name des Spielers
         * @param: Integer points, Banpunkte
         */
        public void setBanPointsToDB(String name, Integer points) {
            mysql.update("UPDATE players SET banpoints = " + points + " WHERE name = '" + name + "'");
        }

        /**
         * @param: String name, Name des Spieler der entfernt werden soll
         * Bitte beim leaven eines Spieler ausführen!
         */
        public void removePlayer(String name) {
            if (infinityplayer.containsKey(name)) {
                infinityplayer.remove(name);
            }
        }

        /**
         * @param: String name, Name des Spielers der auf allen Servern aktualisiert werden soll
         */
        public void updatePlayerGlobal(String name) {
            addMessage("player update " + name);
        }

        /**
         * @param: String name, Name des Spielers der gesucht wird
         * @return: InfinityPlayer der vorher geladen wurde
         */
        public InfinityPlayer getPlayer(String name) {
            if (infinityplayer.containsKey(name)) {
                return infinityplayer.get(name);
            }
            return null;
        }
    }

    private class ChatClient {

        private String host;
        private Integer port;
        private Boolean stop;

        public ChatClient(String host, Integer port) {
            this.host = host;
            this.port = port;
            stop = false;
        }

        public void stop() {
            stop = true;
        }

        public void run() {

            EventLoopGroup group = new NioEventLoopGroup();

            try {

                Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class).handler(new ChatClientInitializer()).option(ChannelOption.SO_KEEPALIVE, true);
                Channel channel = bootstrap.connect(host, port).sync().channel();

                while (!stop && channel != null && channel.isActive()) {
                    for (String msg : tosend) {
                        channel.write(msg.replaceAll("§", "&") + "\r\n");
                    }
                    if (tosend.size() != 0) {
                        channel.flush();
                    }
                    tosend.clear();
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
            } finally {
                group.shutdownGracefully();
            }
        }
    }

    public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            // Klasse bitte manuell erstellen und die Methode "receive" einfügen!
            NettyReceiver.receive(msg.replaceAll("&", "§").split(" "));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }

    public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {

        protected void initChannel(SocketChannel channel) throws Exception {

            ChannelPipeline pipeline = channel.pipeline();

            pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
            pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
            pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
            pipeline.addLast("handler", new ChatClientHandler());

        }
    }
}