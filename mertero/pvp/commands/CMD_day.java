package de.mertero.pvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;



public class CMD_day implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(cmd.getName().equalsIgnoreCase("day")){
					
					try{
					InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
					if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Premium").getPermission()){
					
						if(args.length == 0){
							p.getWorld().setTime(0L);
							p.getWorld().setThundering(false);
							p.getWorld().setStorm(false);
							p.sendMessage(Main.getInstance().getPrefix() + "Es ist nun hell!");
						}
					
				}
					
					
					}catch(Exception e){
						p.sendMessage(Main.getInstance().getPrefix() + "Erst ab §6Premium §7verfügbar!");
					}
					
					
					
				}
			} else {
				sender.sendMessage("Nur ein Spieler kann das! Du Idiot!");
			}
		return false;
	}

}
