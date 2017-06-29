package de.mertero.pvp.commands;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.mysql.MySQLStats;

public class CMD_top3 implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(cmd.getName().equalsIgnoreCase("top3")) {

						String name1=  Bukkit.getOfflinePlayer(MySQLStats.getTopNumber1().toString()).getName();
						String name2=  Bukkit.getOfflinePlayer(MySQLStats.getTopNumber2().toString()).getName();
						String name3=  Bukkit.getOfflinePlayer(MySQLStats.getTopNumber3().toString()).getName();
						
						p.sendMessage("§e-=- §bDie Top 3 §e-=-");
                        p.sendMessage("§aPlatz 1: §b" + name1);
                        p.sendMessage("§aPlatz 2: §b" + name2);
                        p.sendMessage("§aPlatz 3: §b" + name3);
                        p.sendMessage("§e-=-=-=-=-=-=-=-");

				
				}
			}
		return false;
	}

}
