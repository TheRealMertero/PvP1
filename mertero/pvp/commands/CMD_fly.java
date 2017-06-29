package de.mertero.pvp.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class CMD_fly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
				if(sender instanceof Player) {
					Player p = (Player)sender;
					if(cmd.getName().equalsIgnoreCase("fly")) {
						
						try{
						InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
						if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Premium").getPermission()){
							if(args.length == 1) {
								if(args[0].equalsIgnoreCase("on")) {
									if(!Main.combat.contains(p)){
									if(!Main.getInstance().getFly().contains(p)) {
										if(!Main.getInstance().getCooldown().containsKey(p.getName())){
										p.setAllowFlight(true);
										Main.getInstance().getFly().add(p);
										p.sendMessage(Main.getInstance().getPrefix() +"Du kannst nun fliegen!");
										p.sendMessage(Main.getInstance().getPrefix() +"§cNicht im Kampf fliegen!");
										} else {
											long jetzt = System.currentTimeMillis();
											
											if(Main.getInstance().getCooldown().containsKey(p.getName())) {
												long benutzt = Main.getInstance().getCooldown().get(p.getName());
												
												int restzeit = (int) ((benutzt + (5 * 1000) ) -jetzt);
												
												if(restzeit >= 0) {
													int sekunden = restzeit/1000;
													
													p.sendMessage(Main.getInstance().getPrefix() + "Du musst noch §4 " +sekunden+(sekunden == 1 ? " Sekunde" : " Sekunden") + " §7 warten!");
													return true;
												} else {
													Main.getInstance().getCooldown().remove(p.getName());
												}
												
											}
											
											
										}
									} else {
										p.sendMessage(Main.getInstance().getPrefix() +"Du kannst schon fliegen!");
									}
									
									
									
									}
									
									
									
									
									
								} else if(args[0].equalsIgnoreCase("off")) {
									if(!Main.getInstance().getFly().contains(p)) {
										p.sendMessage(Main.getInstance().getPrefix() +"Du hast kein fly aktiviert!");
									} else {
										Main.getInstance().getFly().remove(p);
										p.setFlying(false);
										p.setAllowFlight(false);
										p.setFallDistance(0);
										p.sendMessage(Main.getInstance().getPrefix() +"Du kannst nun nicht mehr fliegen!");
									}
									
								} else {
									p.sendMessage(Main.getInstance().getPrefix() +"Benutze /fly on|off");
								}
							} else {
								p.sendMessage(Main.getInstance().getPrefix() +"Benutze /fly on|off");
							}
						} 
							
						
						
						
						}catch(Exception ex){
							p.sendMessage(Main.getInstance().getPrefix() +"Keine Rechte für /fly");
						}
						
						
						
					}
				}
		
		return false;
	}

}
