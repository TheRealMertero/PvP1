package de.mertero.pvp.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI;
import de.mertero.pvp.mysql.MySQLStats;

public class AsyncStuff implements Listener {

	@EventHandler
	public void onRegister(AsyncPlayerPreLoginEvent e) {
		if (!MySQLStats.isRegistered(e.getName())) {
			MySQLStats.register(e.getName());

		}
		Main.getInstance().getAPI().getPlayerAPI().loadPlayer(e.getName());
	}

	

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		Main.getInstance().getAPI().getPlayerAPI().removePlayer(p.getName());
	}

}
