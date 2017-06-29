package de.mertero.pvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.mysql.MySQLStats;

public class CMD_stats implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("stats")) {
				if(args.length == 1) {
							String allname = args[0];
							if(args[0].equals(allname)) {
								
								
								
							int deaths = MySQLStats.getDeaths(allname);
							int kills = MySQLStats.getKills(allname);

								
								
								if (kills == 0 && deaths == 0) {
									p.sendMessage("§e-=-§a Stats von §6" + allname + " §e-=-");
		                            p.sendMessage("§aKills: §60");
		                            p.sendMessage("§aDeaths: §60");
		                            p.sendMessage("§aK/D: §60");
		                            p.sendMessage("§e-=-=-=-=-=-=-=-=-=-=-=-");
		                        } else if (deaths == 0) {
		                        	p.sendMessage("§e-=-§a Stats von §6" + allname + " §e-=-");
		                            p.sendMessage("§aKills: §6" + kills);
		                            p.sendMessage("§aDeaths: §60");
		                            p.sendMessage("§aK/D: §6" + kills);
		                            p.sendMessage("§e-=-=-=-=-=-=-=-=-=-=-=-");
		 
		                        } else {
		                    		double KD = (double) kills / (double) deaths;
		                    		KD = ((double) ((int) (KD * 100))) / 100;
		                            p.sendMessage("§e-=-§a Stats von §6" + allname + " §e-=-");
		                            p.sendMessage("§aKills: §6" + kills);
		                            p.sendMessage("§aDeaths: §6" + deaths);
		                            p.sendMessage("§aK/D: §6" + KD);
		                            p.sendMessage("§e-=-=-=-=-=-=-=-=-=-=-=-");
		                        }
								
								
							}

					
				} else {
					p.sendMessage(Main.getInstance().getPrefix() + "Benutze /stats [Name]");
				}
			}
		} else {
			sender.sendMessage(Main.getInstance().getPrefix() +"Nur ein Spieler kann das!");
		}
		
		
		return false;
	}

}
