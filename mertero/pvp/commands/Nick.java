package de.mertero.pvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class Nick implements CommandExecutor{

		@SuppressWarnings("deprecation")
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (cmd.getName().equalsIgnoreCase("nick")) {
				if (sender instanceof Player) {
					
					Player p = (Player) sender;
					InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
					if (NickAPI.isNicked(p)) {
						NickAPI.unnick(p);
						p.sendTitle("§6Nick", " §5Du wurdest entnickt!");
						p.sendMessage("§7[§5Nick§7] §5Du wurdest entnickt!");
					} else if (ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI()
							.getRankByName("Streamer").getPermission()) {
						if (NickAPI.isNicked(p)) {
							NickAPI.unnick(p);
							p.sendTitle("§6Nick", " §5Du wurdest entnickt!");
							p.sendMessage("§7[§5Nick§7] §5Du wurdest entnickt!");
							for (Player all : Bukkit.getOnlinePlayers()) {
								all.showPlayer(p);
							}
							Main.setTab(p);
						} else {
							NickAPI.nick(p, NickAPI.getRandomNameOld());
							p.sendTitle("§6Nick", "§5Du wurdest genickt!");
							p.sendMessage("§7[§5Nick§7] §5Du wurdest genickt, dein neuer Name§7: §6" + p.getDisplayName());

							Main.setNickTab(p);

						
							
							
							
						
						}
					}
				}
			}
		return false;
	}
	
	

}
