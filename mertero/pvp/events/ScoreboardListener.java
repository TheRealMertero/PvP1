package de.mertero.pvp.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;
import de.mertero.pvp.manager.ScoreboardManager;

public class ScoreboardListener implements Listener {

	int update;

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
		if (ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Streamer")
				.getPermission()) {
			if (Main.getInstance().getAPI().getPlayerAPI().getPlayer(p.getName()).getNick()) {
				NickAPI.nick(p, NickAPI.getRandomNameOld());
				p.sendTitle("§6Nick", "§aDu bist genickt als: §6" + p.getDisplayName());
				p.sendMessage("§7[§5Nick§7] §aDu bist genickt als§7: §6" + p.getDisplayName());
				Main.setNickTab(p);
			} else {
				Main.setTab(p);
			}

		} else {
			Main.setTab(p);
		}
		
		ScoreboardManager.createBoard(e.getPlayer(), ip);
	}
	

}
