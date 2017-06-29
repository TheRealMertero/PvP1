package de.mertero.pvp.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.mertero.pvp.main.Main;

public class MoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event){
		Player p = event.getPlayer();
		if(Main.onMove.contains(p)){
		Location from = event.getFrom();
		Location to = event.getTo();
		double x = Math.floor(from.getX());
		double z = Math.floor(from.getZ());
		if(Math.floor(to.getX())!=x||Math.floor(to.getZ())!=z){
			Main.onMove.remove(p);
			p.sendMessage(Main.getInstance().getPrefix() + "Teleportierung abgebrochen!");
			Main.getInstance().onmoveint = 3;
			}
		}
		
		
		

		
	}

}
