package de.mertero.pvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class CMD_wb implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
				if(sender instanceof Player){
					Player p = (Player)sender;
					if(cmd.getName().equalsIgnoreCase("wb")){
						
						try{
					InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
					if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Premium").getPermission()){
						if(args.length == 0){
							p.sendMessage(Main.getInstance().getPrefix() + "Öffne Workbench...");
							p.openWorkbench(null, true);
						} else {
							p.sendMessage(Main.getInstance().getPrefix() + "Benutze §a/wb §7um die Workbench zu öffnen!");
						}
					} else {
						p.sendMessage(Main.getInstance().getPrefix() + "Erst ab Premium verfügbar!");
					}
					}catch(Exception exception){
						p.sendMessage(Main.getInstance().getPrefix() + "Erst ab Premium verfügbar!");
					}
						
						
					}
				}
		
		return false;
	}

}
