package de.mertero.pvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;

public class CMD_arena implements CommandExecutor {
	
	int move;
	int number = Main.getInstance().onmoveint;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("arena")) {
				if (args.length == 0) {
					if(!Main.combat.contains(p)){
						
					if(!Main.onMove.contains(p)){
						
						Main.onMove.add(p);
						
						p.sendMessage(Main.getInstance().getPrefix() + "Für 3 Sekunden nicht bewegen!");
						if(Main.onMove.contains(p)){
							
						move = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
						public int drei = number;
						@Override
						public void run() {
							if(Main.onMove.contains(p)){
							if(drei == 3){
							} else if(drei == 2){
							} else if(drei == 1){
							} else if(drei == 0){
								
									Main.onMove.remove(p);
									p.teleport(Main.getInstance().getLm().getLocation("arena"));
									p.sendMessage(Main.getInstance().getPrefix() + "Teleportieren...");
									p.sendMessage(Main.getInstance().getPrefix() + "Du wurdest zur Arena teleportiert!");
									
									
									Bukkit.getScheduler().cancelTask(move);
								
							}
							drei--;
							} else {
								drei = 3;
							}
							
						}
					},0, 20);
						}
					} else {
						p.sendMessage(Main.getInstance().getPrefix() + "Du bist gerade in einer Teleportationsphase!");
					}
					
					} else {
						p.sendMessage(Main.getInstance().getPrefix() + "Im Kampf nicht möglich!");
					}
				}

			}
		}

		return false;
	}

}
