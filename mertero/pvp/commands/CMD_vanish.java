package de.mertero.pvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class CMD_vanish implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
				if(sender instanceof Player) {
					Player p = (Player)sender;
					if(cmd.getName().equalsIgnoreCase("vanish")) {
						try{
						InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
						if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Supporter").getPermission()){
						if(Main.getInstance().getVanish().containsKey(p)) {
							
							Main.getInstance().getVanish().remove(p);
						
							for(Player all :  Bukkit.getOnlinePlayers()) if(all != p) all.showPlayer(p);
							p.sendMessage(Main.getInstance().getPrefix() + "Vanish deaktiviert, andere Spieler können dich wieder sehen!");
							
							
						} else {
							Main.getInstance().getVanish().put(p, p.getLocation());
							
							for(Player all :  Bukkit.getOnlinePlayers()) if(all != p) all.hidePlayer(p);
							p.sendMessage(Main.getInstance().getPrefix() + "Vanish aktiviert, du bist jetzt unsichtbar!");
						}
					} else {

						
					}
					}catch(Exception exception){
						
					}
						
						
						
					}
				}
		
		
		return true;
	}

	
	
}
