package de.mertero.pvp.main;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.mertero.pvp.commands.CMD_Operator;
import de.mertero.pvp.commands.CMD_arena;
import de.mertero.pvp.commands.CMD_chest;
import de.mertero.pvp.commands.CMD_clearchat;
import de.mertero.pvp.commands.CMD_day;
import de.mertero.pvp.commands.CMD_ec;
import de.mertero.pvp.commands.CMD_pvp;
import de.mertero.pvp.commands.CMD_enchant;
import de.mertero.pvp.commands.CMD_endersee;
import de.mertero.pvp.commands.CMD_fly;
import de.mertero.pvp.commands.CMD_heal;
import de.mertero.pvp.commands.CMD_help;
import de.mertero.pvp.commands.CMD_home;
import de.mertero.pvp.commands.CMD_invsee;
import de.mertero.pvp.commands.CMD_kits;
import de.mertero.pvp.commands.CMD_menu;
import de.mertero.pvp.commands.CMD_night;
import de.mertero.pvp.commands.CMD_online;
import de.mertero.pvp.commands.CMD_setarena;
import de.mertero.pvp.commands.CMD_setspawn;
import de.mertero.pvp.commands.CMD_spawn;
import de.mertero.pvp.commands.CMD_stats;
import de.mertero.pvp.commands.CMD_top3;
import de.mertero.pvp.commands.CMD_tp;
import de.mertero.pvp.commands.CMD_tpa;
import de.mertero.pvp.commands.CMD_vanish;
import de.mertero.pvp.commands.CMD_warp;
import de.mertero.pvp.commands.CMD_wb;
import de.mertero.pvp.commands.Nick;
import de.mertero.pvp.events.AsyncStuff;
import de.mertero.pvp.events.CombatListener;
import de.mertero.pvp.events.InventoryClickListener;
import de.mertero.pvp.events.InventoryKitsListener;
import de.mertero.pvp.events.InventoryOnlineListener;
import de.mertero.pvp.events.JoinLeaveListener;
import de.mertero.pvp.events.MoveListener;
import de.mertero.pvp.events.PlayerDeathListener;
import de.mertero.pvp.events.PlayerFlyDamageListener;
import de.mertero.pvp.events.PlayerInteractListener;
import de.mertero.pvp.events.RespawnListener;
import de.mertero.pvp.events.ScoreboardListener;
import de.mertero.pvp.manager.ChestLocation;
import de.mertero.pvp.manager.LocationManager;
import de.mertero.pvp.mysql.MySQL;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static MySQL mysql;

	private String host = "***";
	private String user = "root";
	private String database = "playinfinity";
	private String password = "***";

	/*
	 * private String host = "localhost"; private String user = "root"; private
	 * String database = "skypvp"; private String password = "123456";
	 */

	private static PlayInfinityAPI api;
	
	
	private LocationManager lm;
	private ChestLocation cl;
	private String prefix = "§8[§3PVP§8] §7";
	public int time = 15;
	public int onmoveint = 3;
	
	PluginManager pm = Bukkit.getPluginManager();

	public static Scoreboard sb;

	private ArrayList<Player> fly = new ArrayList<Player>();
	private ArrayList<String> friede = new ArrayList<String>(); 
	private HashMap<Player, Location> vanish = new HashMap<Player, Location>();
	private HashMap<Player, Integer> killstrike = new HashMap<Player, Integer>();
	private HashMap<String, Long> cooldown = new HashMap<>();
	private HashMap<Integer, String> top3 = new HashMap<Integer, String>();
	public static ArrayList<Player> combat = new ArrayList<Player>();
	public static ArrayList<Player> onMove = new ArrayList<Player>();

	public void onEnable() {
		plugin = this;
		api = new PlayInfinityAPI("PvP", "***", "playinfinity", "root", "***",
				"***", 54335);
		// api = new PlayInfinityAPI(name, host, db, user, password, netty_host,
		// netty_port);
		lm = new LocationManager();
		lm.saveCfg();
		
		cl = new ChestLocation();
		cl.saveCfg();
		
		cl.PvPChest();
		
		
		getCommand();
		getEvents();
		ConnectMySQL();
		
	    getConfig().options().copyDefaults(true);
	    saveDefaultConfig();
	    saveConfig();
	}

	public void onDisable() {

		mysql.close();

	}

	private void ConnectMySQL() {
		mysql = new MySQL(host, database, user, password);
		mysql.connect();
		mysql.update("CREATE TABLE IF NOT EXISTS pvp(name varchar(100) UNIQUE, kills int, deaths int)");
	}

	public static Main getInstance() {
		return plugin;
	}

	public void getCommand() {
		getCommand("setspawn").setExecutor(new CMD_setspawn());
		getCommand("spawn").setExecutor(new CMD_spawn());
		getCommand("fly").setExecutor(new CMD_fly());
		getCommand("stats").setExecutor(new CMD_stats());
		getCommand("kits").setExecutor(new CMD_kits());
		getCommand("top3").setExecutor(new CMD_top3());
		getCommand("chest").setExecutor(new CMD_chest());
		getCommand("vanish").setExecutor(new CMD_vanish());
		getCommand("day").setExecutor(new CMD_day());
		getCommand("night").setExecutor(new CMD_night());
		getCommand("clearchat").setExecutor(new CMD_clearchat());
		getCommand("heal").setExecutor(new CMD_heal());
		getCommand("help").setExecutor(new CMD_help());
		//getCommand("friede").setExecutor(new CMD_friede());
		getCommand("operator").setExecutor(new CMD_Operator());
		getCommand("nick").setExecutor(new Nick());
		getCommand("invsee").setExecutor(new CMD_invsee());
		getCommand("endersee").setExecutor(new CMD_endersee());
		getCommand("tpa").setExecutor(new CMD_tpa());
		getCommand("tp").setExecutor(new CMD_tp());
		getCommand("warp").setExecutor(new CMD_warp());
		getCommand("home").setExecutor(new CMD_home());
		getCommand("wb").setExecutor(new CMD_wb());
		getCommand("enchant").setExecutor(new CMD_enchant());
		getCommand("menu").setExecutor(new CMD_menu());
		getCommand("setarena").setExecutor(new CMD_setarena());
		getCommand("arena").setExecutor(new CMD_arena());
		getCommand("online").setExecutor(new CMD_online());
		getCommand("ec").setExecutor(new CMD_ec());
		getCommand("pvp").setExecutor(new CMD_pvp());

	}

	public void getEvents() {
		pm.registerEvents(new ScoreboardListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new JoinLeaveListener(), this);
		pm.registerEvents(new RespawnListener(), this);
		pm.registerEvents(new PlayerFlyDamageListener(), this);
		pm.registerEvents(new AsyncStuff(), this);
		pm.registerEvents(new PlayerInteractListener(), this);
		pm.registerEvents(new CombatListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		pm.registerEvents(new InventoryOnlineListener(), this);
		pm.registerEvents(new InventoryKitsListener(), this);
		pm.registerEvents(new CMD_ec(), this);
		pm.registerEvents(new MoveListener(), this);



	}

	public LocationManager getLm() {
		return lm;
	}
	
	public ChestLocation getCL(){
		return cl;
	}

	public String getPrefix() {
		return prefix;
	}

	public ArrayList<Player> getFly() {
		return fly;
	}

	public HashMap<Player, Integer> getKillstrike() {
		return killstrike;
	}

	public String getPerkPermission(String perk) {
		return getInstance().getConfig().getString("perks." + perk.toLowerCase() + ".perm");

	}

	public HashMap<String, Long> getCooldown() {
		return cooldown;
	}

	public HashMap<Integer, String> getTop3() {
		return top3;
	}

	public HashMap<Player, Location> getVanish() {
		return vanish;
	}

	public ArrayList<String> getFriede() {
		return friede;
	}

	public static void setTab(Player p) {

		PlayInfinityAPI.PlayerAPI.InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
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

		if (rang.getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("JrDeveloper").getPermission()
				|| rang.getName().equals("Hoster")) {
			String rn = rang.getName().replace("Moderator", "Mod").replace("Supporter", "Sup")
					.replace("Administrator", "Admin").replace("Developer", "Dev");
			rangname = rangname + rn + " §8⎜ " + rang.getTab();
		}

		team.setPrefix(rangname);
		team.addEntry(p.getName());

		for (Player all : Bukkit.getOnlinePlayers()) {
			all.setScoreboard(sb);
		}
	}

	public static void setNickTab(Player p) {

		PlayInfinityAPI.RangAPI.Rang rang = Main.getInstance().getAPI().getRangAPI().getRankByName("Spieler");

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

		if (rang.getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("JrDeveloper").getPermission()
				|| rang.getName().equals("Hoster")) {
			String rn = rang.getName().replace("Moderator", "Mod").replace("Supporter", "Sup")
					.replace("Administrator", "Admin").replace("Developer", "Dev");
			rangname = rangname + rn + " §8⎜ " + rang.getTab();
		}

		team.setPrefix(rangname);
		team.addEntry(p.getName());

		for (Player all : Bukkit.getOnlinePlayers()) {
			all.setScoreboard(sb);
		}
	}

	public PlayInfinityAPI getAPI() {
		// TODO Auto-generated method stub
		return api;
	}
	

}
