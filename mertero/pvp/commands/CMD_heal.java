package de.mertero.pvp.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class CMD_heal implements CommandExecutor {
	
	private HashMap<String, Long> heal = new HashMap<>(); 

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(cmd.getName().equalsIgnoreCase("heal")){
					if(args.length == 0){
						
						if(!Main.combat.contains(p)){
						try{
						InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
						if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Premium").getPermission()){
						
						long jetzt = System.currentTimeMillis();
						
						if(heal.containsKey(p.getName())) {
							long benutzt = heal.get(p.getName());
							
							int restzeit = (int) ((benutzt + (60 * 1000) ) -jetzt);
							
							if(restzeit > 0) {
								int sekunden = restzeit/1000;
								
								p.sendMessage(Main.getInstance().getPrefix() + "Du musst noch §5" +sekunden+(sekunden == 1 ? " Sekunde" : " Sekunden") + "§7 warten!");
								return true;
							}
						
					
					} else {
						p.setHealth(20);
						p.setFoodLevel(20);
						
						p.sendMessage(Main.getInstance().getPrefix() + "Du wurdest geheilt");
						heal.put(p.getName(), jetzt);
					}
				}
						
						
						}catch(Exception ex){
							p.sendMessage(Main.getInstance().getPrefix() + "Erst ab §6Premium §7möglich!");
						}
						
						
					} else {
						p.sendMessage(Main.getInstance().getPrefix() + "Im Kamf nicht möglich!");
					}
						
						
			}
	}

			}
			return false;
			
	}
}
