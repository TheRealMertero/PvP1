package de.mertero.pvp.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import de.mertero.pvp.main.Main;

public class PlayerFlyDamageListener implements Listener {
	
	/*
	 * sek = ms/1000;
	 * min = ms/1000/60;
	 * std = ms/1000/60/60;
	 */
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		Entity e = event.getEntity();
		Entity damager = event.getDamager();
		if(e instanceof Player) {
			Player p = (Player)e;
			if(Main.getInstance().getFly().contains(p)) {
				Main.getInstance().getFly().remove(p);
				
				p.setAllowFlight(false);
				p.setFlying(false);
				p.sendMessage(Main.getInstance().getPrefix() + "Im Kampf wird nicht geflogen!");
				long jetzt = System.currentTimeMillis();
				Main.getInstance().getCooldown().put(p.getName(), jetzt);
			}
			
			
		}
		if(Main.getInstance().getVanish().containsKey(damager)){
			event.setCancelled(true);
		}
	}

}
