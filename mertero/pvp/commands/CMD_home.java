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

public class CMD_home implements CommandExecutor {
	
	public static File homefile = new File("plugins//PvP", "homes.yml");
    public static YamlConfiguration homecfg = YamlConfiguration.loadConfiguration(homefile);
    
    int move;
	int number = Main.getInstance().onmoveint;

    
	public void saveCfg(){
		try {
			homecfg.save(homefile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(cmd.getName().equalsIgnoreCase("home")){
					if(args.length == 0){
						
						try{
						InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
		                if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Moderator").getPermission()) {
		                	p.sendMessage(Main.getInstance().getPrefix() + "/home [Name]");
							p.sendMessage(Main.getInstance().getPrefix() + "/home list");
							p.sendMessage(Main.getInstance().getPrefix() + "/home set [Name]");
							p.sendMessage(Main.getInstance().getPrefix() + "/home del [Name]");
							p.sendMessage(Main.getInstance().getPrefix() + "§a/home list [Name]");
							p.sendMessage(Main.getInstance().getPrefix() + "§a/home tp [Name] [Home]");
		                } else if(ip.getRang().getPermission() <= Main.getInstance().getAPI().getRangAPI().getRankByName("Supporter").getPermission() && ip.getRang().getPermission() <= Main.getInstance().getAPI().getRangAPI().getRankByName("Supporter").getPermission()){
		                	p.sendMessage(Main.getInstance().getPrefix() + "/home [Name]");
							p.sendMessage(Main.getInstance().getPrefix() + "/home list");
							p.sendMessage(Main.getInstance().getPrefix() + "/home set [Name]");
							p.sendMessage(Main.getInstance().getPrefix() + "/home del [Name]");
		                }
		                
					}catch(Exception ex){
						
		                
					}
					}
						
					if(args.length == 2){
						String args1playername = args[1];
						if(args[0].equals("list")){
							if(args[1].equals(args1playername)){
								try {
							InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
			                if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Moderator").getPermission()) {
			                	String name1 = args1playername;
								String homename1 = homecfg.getString(name1+".homename");
								
								if(homecfg.contains(name1) && homename1 != null){
									p.sendMessage(Main.getInstance().getPrefix() + "§7Homes:§a " + homename1);
									} else {
										p.sendMessage(Main.getInstance().getPrefix() + "Home wurde noch nicht gesetzt!");
									}
							}
			                
			                }catch(Exception e){
			                }
								
							} 
							
						
						
							
						} else if(args[0].equalsIgnoreCase("set")){
									//home set NAME
									
								String name = p.getName();
								String home = args[1];
								Location loc = p.getLocation();
								String homename = homecfg.getString(name+".homename");

								//ABFRAGE OB SPIELERNAME SCHON IN CFG
								//NAME = COMMAND AUSFÜHRER = HOMEERSTELLER
								//homeersteller = CFG EINTRAG FÜR HOME BESITZER
								
								
								if(!homecfg.contains(name)){
								//ERSTE HOME WIRD GESETZT, WENN USER SICH NICHT IN DER CFG BEFINDET
								homecfg.set(name+".name", name);
								homecfg.set(name+".homename", home);
								//HOME ANZAHL EINTRAG = WICHTIG
								homecfg.set(home+".world", loc.getWorld().getName());
								homecfg.set(home+".x",loc.getX());
								homecfg.set(home+".y", loc.getY());
								homecfg.set(home+".z", loc.getZ());
								homecfg.set(home+".yaw", loc.getYaw());
								homecfg.set(home+".pitch", loc.getPitch());
								try {
									homecfg.save(homefile);
									String homenamee = homecfg.getString(name+".homename");
									p.sendMessage(Main.getInstance().getPrefix() + "Du hast dein erstes Home gesetzt! Name:§a " + homenamee);
									p.sendMessage(Main.getInstance().getPrefix() + "Benutze /home list um deine Homes zusehen!");
								} catch (IOException e) {
									e.printStackTrace();
								}
								}else if(homecfg.contains(name) && homename == null){
									//ERSTE HOME WIRD GESETZT, WENN USER SICH NICHT IN DER CFG BEFINDET
									homecfg.set(name+".name", name);
									homecfg.set(name+".homename", home);
									//HOME ANZAHL EINTRAG = WICHTIG
									homecfg.set(home+".world", loc.getWorld().getName());
									homecfg.set(home+".x",loc.getX());
									homecfg.set(home+".y", loc.getY());
									homecfg.set(home+".z", loc.getZ());
									homecfg.set(home+".yaw", loc.getYaw());
									homecfg.set(home+".pitch", loc.getPitch());
									try {
										homecfg.save(homefile);
										String homenamee = homecfg.getString(name+".homename");
										p.sendMessage(Main.getInstance().getPrefix() + "Du hast ein Home gesetzt! Name:§a " + homenamee);
									} catch (IOException e) {
										e.printStackTrace();
									}
								} else {
									p.sendMessage(Main.getInstance().getPrefix() + "Du besitzt schon ein home! Name: §a" + homename);
								}
									
								
					
					
					// /home del NAME
					}else if(args[0].equalsIgnoreCase("del")){
						String name = p.getName();
						String home = args[1];
						String homename = homecfg.getString(name+".homename");
						
						
						 if(homename.equals(home)){
								for (String path : homecfg.getConfigurationSection(name).getKeys(true)) {
	                                homecfg.set(name + "." + path, null);
	                                for(String path2 : homecfg.getConfigurationSection(homename).getKeys(true)){
		                                homecfg.set(homename + "." + path2, null);
	                                }
	                             }
								
								try {
	                                homecfg.save(homefile);
	                                p.sendMessage(Main.getInstance().getPrefix() + "§aHome§7: §6" + homename + " §awurde gelöscht.");
	                               
	                            } catch (IOException e) {
	                                p.sendMessage(Main.getInstance().getPrefix() + "§cFehler§7: §cbei einem Admin melden!");
	                                e.printStackTrace();
	                            }
							}
						 
						 if(!homecfg.contains(name)){
							
								p.sendMessage(Main.getInstance().getPrefix() + "Du hast keine homes gesetzt!");
							
						}
						}
					
					
					// /home NAME
					
					} else if(args.length == 1){
						if(args[0].equals("list")){
							String name1 = p.getName();
							String homename1 = homecfg.getString(name1+".homename");
							
							
							if(homecfg.contains(name1) && homename1 != null){
								p.sendMessage(Main.getInstance().getPrefix() + "§7Homes:§a " + homename1);
								} else {
									p.sendMessage(Main.getInstance().getPrefix() + "Home wurde noch nicht gesetzt!");
								}

							
							
						}
						
						
						
						String home = args[0];
						String name = p.getName();
						String homename = homecfg.getString(name+".homename");
						try {
							
							if(!Main.combat.contains(p)){
								if(home.equals(homename)){
									World world = Bukkit.getWorld(homecfg.getString(homename + ".world"));
			                        double x = homecfg.getDouble(homename + ".x");
			                        double y = homecfg.getDouble(homename + ".y");
			                        double z = homecfg.getDouble(homename + ".z");
			                        double yaw = homecfg.getDouble(homename + ".yaw");
			                        double pitch = homecfg.getDouble(homename + ".pitch");
			                        Location loc = new Location(world, x, y, z);
		                            loc.setPitch((float) pitch);
		                            loc.setYaw((float) yaw);
		                            
		                            if(!Main.onMove.contains(p)){
		    							
		    							Main.onMove.add(p);
		    							
		    							p.sendMessage(Main.getInstance().getPrefix() + "Für 3 Sekunden nicht bewegen!");
		    							if(Main.onMove.contains(p)){
		    								
		    							move = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
		    							public int drei = number;
		    							@Override
		    							public void run() {
		    								if(Main.onMove.contains(p)){
		    								if(drei == 3){
		    								} else if(drei == 2){
		    								} else if(drei == 1){
		    								} else if(drei == 0){
		    										Main.onMove.remove(p);
		    										p.teleport(loc);
		    			                            p.sendMessage(Main.getInstance().getPrefix() + "§aErfolgreich zu Home§7: §6"
		    			                                    + homename + " §ateleportiert.");
		    										Bukkit.getScheduler().cancelTask(move);
		    									
		    								}
		    								drei--;
		    								} else {
		    									drei = 3;
		    								}
		    								
		    							}
		    						},0, 20);
		    							}
		    						} else {
		    							p.sendMessage(Main.getInstance().getPrefix() + "Du bist gerade in einer Teleportationsphase!");
		    						}
		                            
		                            
									}
							} else {
								p.sendMessage(Main.getInstance().getPrefix() + "Im Kampf nicht möglich!");
							}
								
								if(!homecfg.contains(name)){
									p.sendMessage(Main.getInstance().getPrefix() + "Du hast noch kein Home gesetzt!");
								}
							} catch(Exception e){
						e.printStackTrace();
						p.sendMessage(Main.getInstance().getPrefix() + "Du hast kein Home mit diesem Namen!");
					}
						
						
					} else if(args.length == 3){
						if(args[0].equals("tp")){
							
							try{
							InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
			                if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Moderator").getPermission()) {
							
							String target = args[1];
							String targethome = args[2];
							String homename = homecfg.getString(target+".homename");
							if(targethome.equals(homename)){
								World world = Bukkit.getWorld(homecfg.getString(homename + ".world"));
		                        double x = homecfg.getDouble(homename + ".x");
		                        double y = homecfg.getDouble(homename + ".y");
		                        double z = homecfg.getDouble(homename + ".z");
		                        double yaw = homecfg.getDouble(homename + ".yaw");
		                        double pitch = homecfg.getDouble(homename + ".pitch");
		                        Location loc = new Location(world, x, y, z);
	                            loc.setPitch((float) pitch);
	                            loc.setYaw((float) yaw);
	 
	                            p.teleport(loc);
	                            p.sendMessage(Main.getInstance().getPrefix() + "§aErfolgreich zu Home§7: §6"
	                                    + homename + " §ateleportiert.");
							}
							
							
			                }
							
							}catch(Exception ex){
								
							}
							
							
							
						}
					}
				}
			}
		return false;
	}
	
	

}
