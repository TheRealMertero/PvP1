package de.mertero.pvp.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;



public class CMD_tpa implements CommandExecutor {
	
	private HashMap<Player, Location> tp = new HashMap<Player, Location>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
				if(sender instanceof Player) {
					Player p = (Player)sender;
					for(Player gesendet : Bukkit.getOnlinePlayers()){
					try{
						if(cmd.getName().equalsIgnoreCase("tpa")){
							if(args.length == 0){
								p.sendMessage(Main.getInstance().getPrefix() + "/tpa [Name]");
								p.sendMessage(Main.getInstance().getPrefix() + "/tpa remove [name]");
							}
							if(gesendet.getName() != p.getName()){
							if(args[0].equals(gesendet.getName())){
								if(!tp.containsKey(gesendet)){
									tp.put(gesendet, gesendet.getLocation());
									gesendet.sendMessage(Main.getInstance().getPrefix() + "Du hast eine TP Anfrage von " +  p.getName() + " erhalten");
									gesendet.sendMessage(Main.getInstance().getPrefix() + "Benutze /tpa accept [Name] um sie anzunehmen oder /tpa deny [Name] um sie abzulehnen!");

									p.sendMessage(Main.getInstance().getPrefix() + "Du hast " + gesendet.getName() + " eine TP Anfrage gesendet!");
									p.sendMessage(Main.getInstance().getPrefix() + "Mit /tpa remove [Name] kannst du deine Anfrage zurücknehmen!");
									
								} else {
									p.sendMessage(Main.getInstance().getPrefix() + "Du hast diesem Spieler schon eine Anfrage gesendet!");
								}
							} else if(args[0].equalsIgnoreCase("accept")){
								if(tp.containsKey(p)){
									if(args[1].equals(gesendet.getName())){
										if(!Main.combat.contains(gesendet)){
											gesendet.sendMessage(Main.getInstance().getPrefix() + "Du wirst teleportiert!");
											gesendet.teleport(p.getLocation());
											tp.remove(p);
										} else {
											gesendet.sendMessage(Main.getInstance().getPrefix() + "Im Kampf nicht möglich!");
										}
										
									} 
								}
							} else if(args[0].equalsIgnoreCase("remove")){
								if(tp.containsKey(gesendet)){
									if(args[1].equals(gesendet.getName())){
										tp.remove(gesendet);
										gesendet.sendMessage(Main.getInstance().getPrefix() + "Anfrage wurde zurückgezogen!");
										p.sendMessage(Main.getInstance().getPrefix() + "Du hast die Anfrage zurückgenommen!");

									}
								} else {
									gesendet.sendMessage(Main.getInstance().getPrefix() + "Keine TP Anfrage von diesem Spieler erhalten!");
								}
							}  else if(args[0].equalsIgnoreCase("deny")){
								if(tp.containsKey(p)){
									if(args[1].equals(gesendet.getName())){
										tp.remove(p);
										gesendet.sendMessage(Main.getInstance().getPrefix() + "TP Anfrage wurde von "+ p.getName() + " abgelehnt!");
										p.sendMessage(Main.getInstance().getPrefix() + "Du hast die TP Anfrage abgelehnt!");

									}
								}
							}
							
							
						}
						}
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					
						}catch(Exception e){
							
						}
					}
				}
		
		return false;
	}

}
