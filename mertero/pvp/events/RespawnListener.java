package de.mertero.pvp.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.mertero.pvp.main.Main;

public class RespawnListener implements Listener {
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				
				p.teleport(Main.getInstance().getLm().getLocation("spawn"));
				
			}
			
		}, 20);
		
		
		
	}

}
