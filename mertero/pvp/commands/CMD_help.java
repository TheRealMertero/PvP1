package de.mertero.pvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CMD_help implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
				if(sender instanceof Player){
					Player p = (Player)sender;
					if(cmd.getName().equalsIgnoreCase("help")){
						if(args.length == 0){
							p.sendMessage(" §e-=-§a HELP [1] §e-=-");
							p.sendMessage("   §a/menu ");
							p.sendMessage("   §a/stats [Name] ");
							p.sendMessage("   §a/top3 ");
							p.sendMessage("   §a/night ");
							p.sendMessage("   §a/help 2 ");
							p.sendMessage(" §e-=- -=- -=-");
						} else {
							if(args[0].equals("2")){
								p.sendMessage(" §e-=-§a HELP [2] §e-=-");
								p.sendMessage("   §a/arena ");
								p.sendMessage("   §a/warp");
								p.sendMessage("   §a/home ");
								p.sendMessage("   §a/tpa ");
								p.sendMessage("  §a/help 3");
								p.sendMessage(" §e-=- -=- -=-");
							} else if(args[0].equals("3")){
								p.sendMessage(" §e-=-§a HELP [3] §e-=-");
								p.sendMessage("   §a/day ");
								p.sendMessage("   §a/enchant ");
								p.sendMessage("   §a/wb ");
								p.sendMessage("   §a/kits ");
								p.sendMessage("   §a/help 4 ");
								p.sendMessage(" §e-=- -=- -=-");
							} else if(args[0].equals("4")){
								p.sendMessage(" §e-=-§a HELP [4] §e-=-");
								p.sendMessage("   §a/online ");
								p.sendMessage("   §a/heal ");
								p.sendMessage("   §a/ec ");
								p.sendMessage("   §a/fly on | off");
								p.sendMessage("   §a/pvp");
								p.sendMessage(" §e-=- -=- -=-");
							}
						}
					}
				}
		
		
		return false;
	}

}
