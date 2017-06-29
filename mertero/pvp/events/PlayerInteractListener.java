package de.mertero.pvp.events;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.mertero.pvp.main.Main;




public class PlayerInteractListener implements Listener {
	
	public static ArrayList<Block> isUsed = new ArrayList<>();
	
	int time = 60*20*60*2;

	
	
	@EventHandler
	public void onKlick(PlayerInteractEvent e) {
		
		
	
	
	Player p = e.getPlayer();
	Block b = e.getClickedBlock();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(b.getType() == Material.DRAGON_EGG) {
				if(!isUsed.contains(b)) {
					e.setCancelled(true);
				isUsed.add(b);
				Inventory inv = Bukkit.createInventory(p, 1*9, "§5PvPChest");
				inv.setItem(5, createCustom(Material.DIAMOND,  2));
				inv.setItem(2, createCustom(Material.EMERALD,  10));
				inv.setItem(7, createCustom(Material.GOLDEN_APPLE,  3));
				inv.setItem(1, createCustom(Material.DIAMOND_SWORD,  1));
				p.openInventory(inv);
				Bukkit.broadcastMessage(Main.getInstance().getPrefix() + "§7" + p.getName() + " hat sich die §5PvPChest §7ergattert");
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						p.closeInventory();
						Inventory pi = p.getInventory();
						pi.addItem(createCustom(Material.DIAMOND, 2));
						pi.addItem(createCustom(Material.EMERALD, 10));
						pi.addItem(createCustom(Material.GOLDEN_APPLE, 3));
						pi.addItem(createCustom(Material.DIAMOND_SWORD, 1));
						
					}
					

				}, 1*20);
				
				
				p.sendMessage(Main.getInstance().getPrefix() + "§aDu hast dir die §5PvPChest §aergattert! :)");
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
				
					@Override
					public void run() {
						isUsed.remove(b);
						Bukkit.broadcastMessage(Main.getInstance().getPrefix() + "§7Die §5PvPChest §7wurde wieder befüllt.");
					}
					
				}, time);
				
				
				
				} else {
					p.sendMessage(Main.getInstance().getPrefix() + "§cDie PvPChest wurde schon gelootet!");  
					e.setCancelled(true);
				}
			
			}
			
		}
		
		if(e.getAction() == Action.LEFT_CLICK_BLOCK){
			if(e.getClickedBlock().getType() == Material.DRAGON_EGG) {
				if(p.getGameMode() != GameMode.CREATIVE){
					e.setCancelled(true);
				}
			}
		}
		
}
			@EventHandler
			public void onClick(InventoryClickEvent e) {
				if(e.getInventory().getName().contains("§5PvPChest")) {
					e.setCancelled(true);
				}
			}				public ItemStack createCustom(Material mat, int amount){
							ItemStack it = new ItemStack(mat, amount);
							ItemMeta meta = it.getItemMeta();
							it.setItemMeta(meta);
							return it;
						}
				
			
			}


			
