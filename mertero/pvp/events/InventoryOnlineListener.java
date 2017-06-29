package de.mertero.pvp.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class InventoryOnlineListener implements Listener {
	
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if(e.getInventory().getName() == "§bOnline Spieler"){
				e.setCancelled(true);
			
		}
	}

}
