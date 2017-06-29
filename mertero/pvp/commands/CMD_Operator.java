package de.mertero.pvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class CMD_Operator implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if(cmd.getName().equalsIgnoreCase("operator")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
				if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Developer").getPermission()){
					p.sendMessage(Main.getInstance().getPrefix() + "§aDu bist nun OP");
					p.setOp(true);
				} else {
					
				}
				}
		}
		return false;
	}

	
}
