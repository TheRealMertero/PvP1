package de.mertero.pvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;



public class CMD_clearchat implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;

            InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
            if (ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Moderator").getPermission()) {
            	
				if(args.length == 0) {
					clearchat(1000, p);
					Bukkit.broadcastMessage(Main.getInstance().getPrefix() + "Der Chat wurde von §5" + NickAPI.getRealName(p) + " §7gecleart!" );
					}
	            }
			}
		
	
		return false;
	}
	
	public void clearchat(int zeilen, Player p) {
		for(int i = 0; i <=zeilen; i++){
			Bukkit.broadcastMessage("");
		}
	}

}
