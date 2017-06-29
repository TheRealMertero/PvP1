package de.mertero.pvp.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public class CMD_ec implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(cmd.getName().equalsIgnoreCase("ec")){
					if(args.length == 0){
						p.openInventory(p.getEnderChest());
					}
				}
			}
		return false;
	}
}
