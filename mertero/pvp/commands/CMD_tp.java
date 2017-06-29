package de.mertero.pvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class CMD_tp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
				if(sender instanceof Player){
					Player p = (Player)sender;
					try{
	                InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
	               if (ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Moderator").getPermission()) {
						if(cmd.getName().equalsIgnoreCase("tp")){
							if(args.length == 0){
								p.sendMessage(Main.getInstance().getPrefix() + " Benutze /tp [Name] um dich zu einem Spieler zu teleportieren!");
								p.sendMessage(Main.getInstance().getPrefix() + " Benutze /tp tome [Name] um jemanden zu dir zu teleportieren!");
							}
							for(Player all : Bukkit.getOnlinePlayers()){
								if(all.getName() != p.getName()){
									if(args[0].equals(all.getName())){
										if(args.length == 1){
											if(!Main.combat.contains(p)){
							                	p.teleport(all.getLocation());
							                	p.sendMessage(Main.getInstance().getPrefix() + "Du wurdest zu " + all.getName() + " teleportiert!");
											} else {
												p.sendMessage(Main.getInstance().getPrefix() + "Im Kampf nicht möglich!");
											}
										} 
							                
									} else if(args[0].equals("tome")){
										if(args[1].equals(all.getName())){
											all.teleport(p.getLocation());
											all.sendMessage(Main.getInstance().getPrefix() + "Du wurdest von §a" + p.getName() + "§7 zu §a" + p.getName() + "§7 teleportiert!");
											p.sendMessage(Main.getInstance().getPrefix() + "Du hast §a" + all.getName() + "§7 zu §a" + p.getName() + "§7 teleportiert!");
										}
									} 
								} 
							}
						}
	                }
				} catch(Exception exception){
					
				}
		
			}
		return false;
	}

}
