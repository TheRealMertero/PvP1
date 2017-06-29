package de.mertero.pvp.events;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerQuitEvent;

import de.mertero.pvp.main.Main;


public class CombatListener implements Listener {
	
	
	
	
	@EventHandler
	public void onCombat(EntityDamageByEntityEvent e) {
		final Player p = (Player) e.getEntity();
		if(!e.getCause().equals(DamageCause.FALL)){
				if(!Main.combat.contains(p)){
					Main.combat.add(p);
					int time = 5*20;
					p.sendMessage(Main.getInstance().getPrefix() + "Du bist im Kampf!");
					
					Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							
							Main.combat.remove(p);
							p.sendMessage(Main.getInstance().getPrefix() + "Du bist nicht mehr im Kampf");
							
						}
					}, time);
				} 
		}
		
		}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(Main.combat.contains(p)){
			p.setHealth(0.0);
			Main.combat.remove(p);
		}
	}

}
