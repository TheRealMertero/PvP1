package de.mertero.pvp.commands;


import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class CMD_warp implements CommandExecutor {
	

	public static File warpsFile = new File("plugins//PvP", "warps.yml");
    public static YamlConfiguration warpscfg = YamlConfiguration.loadConfiguration(warpsFile);
 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
 
        if (sender instanceof Player) {
            Player p = (Player) sender;
            // /warp
            // /warp <Warp>
            // /warp <Warp> <Spieler>
            // /warp set <Warp>
            // /warp del <Warp>
            // /warp
            if (args.length == 0) {
                // Warps: text, test, hallo, haus
 
                StringBuilder sb = new StringBuilder();
                sb.append(Main.getInstance().getPrefix() + "§aWarps: §6");
 
                int i = 0;
                for (String warp : warpscfg.getKeys(true)) {
                    if (warpscfg.contains(warp + ".name")) {
                        if (i == 1) {
                            sb.append("§7, §6");
                        } else {
                            i = 1;
                        }
                        sb.append(warpscfg.getString(warp + ".name"));
                    }
                }
 
                p.sendMessage(sb.toString());
            }
            
            // /warp <Warp>
            if (args.length == 1) {
                    String warp = args[0].toLowerCase();
                    if(!Main.combat.contains(p)){
                    if (warpscfg.contains(warp + ".name")) {
                        World world = Bukkit.getWorld(warpscfg.getString(warp + ".world"));
                        double x = warpscfg.getDouble(warp + ".x");
                        double y = warpscfg.getDouble(warp + ".y");
                        double z = warpscfg.getDouble(warp + ".z");
                        double yaw = warpscfg.getDouble(warp + ".yaw");
                        double pitch = warpscfg.getDouble(warp + ".pitch");
 
                        if (world != null) {
                            Location loc = new Location(world, x, y, z);
                            loc.setPitch((float) pitch);
                            loc.setYaw((float) yaw);
 
                            p.teleport(loc);
                            p.sendMessage(Main.getInstance().getPrefix() + "§aErfolgreich zu Warp§7: §6"
                                    + warpscfg.getString(warp + ".name") + " §ateleportiert.");
 
                        } else {
                            p.sendMessage(Main.getInstance().getPrefix() + "§cFehler§7: §6" + warpscfg.getString(warp + ".name")
                                    + " §cunbekannten Welt.");
                        }
                    } else {
                        p.sendMessage(Main.getInstance().getPrefix() + "§cFehler: §c Warp§7: §6" + args[0] + " existiert nicht.");
                    }
            } else {
            	p.sendMessage(Main.getInstance().getPrefix() + "Im Kampf nicht möglich!");
            }
                
            }
 
            if (args.length == 2) {
 
                if (args[0].equalsIgnoreCase("set")) {
                    // /warp set <Warp>
					InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
					if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("SrDeveloper").getPermission()){
                        String warp = args[1].toLowerCase();
                        Location loc = p.getLocation();
 
                        warpscfg.set(warp + ".name", args[1]);
                        warpscfg.set(warp + ".world", loc.getWorld().getName());
                        warpscfg.set(warp + ".x", loc.getX());
                        warpscfg.set(warp + ".y", loc.getY());
                        warpscfg.set(warp + ".z", loc.getZ());
                        warpscfg.set(warp + ".yaw", loc.getYaw());
                        warpscfg.set(warp + ".pitch", loc.getPitch());
 
                        try {
                            warpscfg.save(warpsFile);
                            p.sendMessage(Main.getInstance().getPrefix() + "§aWarp§7: §6" + args[1] + " §awurde erstellt.");
                        } catch (IOException e) {
                            p.sendMessage(Main.getInstance().getPrefix() + "§cFehler§7: §cbei einem Admin melden!");
                            e.printStackTrace();
                        }
                    }
                } else if (args[0].equalsIgnoreCase("del")) {
                    // /warp del <Warp>
 
					InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
					if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("SrDeveloper").getPermission()){
 
                        String warp = args[1].toLowerCase();
 
                        if (warpscfg.contains(warp + ".name")) {
 
                            for (String path : warpscfg.getConfigurationSection(warp).getKeys(true)) {
                                warpscfg.set(warp + "." + path, null);
                            }
 
                            try {
                                warpscfg.save(warpsFile);
                                p.sendMessage(Main.getInstance().getPrefix() + "§aWarp§7: §6" + args[1] + " §awurde gelöscht.");
                            } catch (IOException e) {
                                p.sendMessage(Main.getInstance().getPrefix() + "§cFehler§7: §cbei einem Admin melden!");
                                e.printStackTrace();
                            }
 
                        } else {
                            p.sendMessage(Main.getInstance().getPrefix() + "§cFehler§7: §cDer Warp §6" + args[0] + " §c existiert nicht!");
                        }
 
                    }
 
                } else {
                	
                	//TP Other Player to a Warp
                    String warp = args[0].toLowerCase();
					InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
					if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("SrDeveloper").getPermission()){
                        if (warpscfg.contains(warp + ".name")) {
                            World world = Bukkit.getWorld(warpscfg.getString(warp + ".world"));
                            double x = warpscfg.getDouble(warp + ".x");
                            double y = warpscfg.getDouble(warp + ".y");
                            double z = warpscfg.getDouble(warp + ".z");
                            double yaw = warpscfg.getDouble(warp + ".yaw");
                            double pitch = warpscfg.getDouble(warp + ".pitch");
 
                            if (world != null) {
                                Player t = Bukkit.getPlayer(args[1]);
                                if (t != null) {
                                    Location loc = new Location(world, x, y, z);
                                    loc.setPitch((float) pitch);
                                    loc.setYaw((float) yaw);
 
                                    t.teleport(loc);
                                    p.sendMessage(Main.getInstance().getPrefix() + "§aDu hast §6" + t.getName() + " §azum Warp§7: §6"
                                            + warpscfg.getString(warp + ".name") + " §ateleportiert.");
                                } else {
                                    p.sendMessage(Main.getInstance().getPrefix() + "§cFehler§7: §cDer Spieler §6" + args[1]
                                            + " §ckonnte nicht gefunden werden.");
                                }
                            } else {
                                p.sendMessage("§cFehler§7: §cDer Warp " + warpscfg.getString(warp + ".name")
                                        + " hat eine unbekannte Welt.");
                            }
                        } else {
                            p.sendMessage("§4Fehler§7: §cDer Warp §6" + args[0] + " §cexistiert nicht.");
                        }
                    }
                }
 
            }
 
        }
 
        return true;
    }
}
