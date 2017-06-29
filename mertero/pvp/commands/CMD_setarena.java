package de.mertero.pvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class CMD_setarena implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(cmd.getName().equalsIgnoreCase("setarena")) {
					if(p.isOp()) {
						if(args.length == 0) {
							
							
							try{
							InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
							if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("SrDeveloper").getPermission()){
							
							Main.getInstance().getLm().setlocation("arena", p.getLocation());
							p.sendMessage(Main.getInstance().getPrefix() + "Du hast die Arena gesetzt!");
							
							
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
