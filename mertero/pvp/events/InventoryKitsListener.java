package de.mertero.pvp.events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class InventoryKitsListener implements Listener {
	
	public static Map<String, Long> cooldown = new HashMap<>(); 
	public static Map<String, Long> cooldownpremium = new HashMap<>(); 
	public static Map<String, Long> cooldownerze = new HashMap<>(); 
	public static Map<String, Long> cooldownbauen = new HashMap<>(); 
	public static Map<String, Long> cooldownminer = new HashMap<>(); 
	public static Map<String, Long> cooldownyt = new HashMap<>(); 

	
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
	Player p = (Player) e.getWhoClicked();
	if(e.getClickedInventory().getName() == "§bKits"){
		if(e.getClick().isLeftClick() || e.getClick().isRightClick()){
			if(e.getCurrentItem().getType() == Material.WOOD_SWORD){
				p.closeInventory();
				long jetzt = System.currentTimeMillis();
				
				if(cooldown.containsKey(p.getName())) {
					long benutzt = cooldown.get(p.getName());
					
					int restzeit = (int) ((benutzt + (60 * 1000) ) -jetzt);
					
					if(restzeit > 0) {
						int sekunden = restzeit/1000;
						p.sendMessage(Main.getInstance().getPrefix() + "Du musst noch §6" +sekunden+(sekunden == 1 ? " Sekunde" : " Sekunden") + "§7 warten!");
					} 
					if(restzeit < 1){
						p.sendMessage(Main.getInstance().getPrefix() + "Kit §8Starter§7 wieder verfügbar!");
						cooldown.remove(p.getName());
					}
					
				} else {
				
				ItemStack sword = new ItemStack(new ItemStack(Material.WOOD_SWORD));
				ItemStack bread = new ItemStack(new ItemStack(Material.BREAD, 3));
				
				
				ItemStack Helm = new ItemStack(new ItemStack(Material.LEATHER_HELMET));
				
				ItemStack Brust = new ItemStack(new ItemStack(Material.LEATHER_CHESTPLATE));
				
				ItemStack Hose = new ItemStack(new ItemStack(Material.LEATHER_LEGGINGS));
				
				ItemStack Schuh = new ItemStack(new ItemStack(Material.LEATHER_BOOTS));
				
				p.getInventory().addItem(sword);
				p.getInventory().addItem(bread);
				
				if(p.getInventory().contains(Helm)){
				}else {
					p.getInventory().addItem(Helm);
				}
				
				if(p.getInventory().contains(Brust)){
				}else {
					p.getInventory().addItem(Brust);
				}
				
				if(p.getInventory().contains(Hose)){
				}else {
					p.getInventory().addItem(Hose);
				}

				if(p.getInventory().contains(Schuh)){
				}else {
					p.getInventory().addItem(Schuh);
				}
				
				p.sendMessage(Main.getInstance().getPrefix() + "Du hast das §6Starterkit §7erhalten!");
				cooldown.put(p.getName(), jetzt);
				}
				} else if(e.getCurrentItem().getType() == Material.STONE_PICKAXE){
					p.closeInventory();
					long jetzt = System.currentTimeMillis();
					
					if(cooldownminer.containsKey(p.getName())) {
						long benutzt = cooldownminer.get(p.getName());
						
						int restzeit = (int) ((benutzt + (60 * 1000 * 60) ) -jetzt);
						
						if(restzeit > 0) {
							int sekunden = restzeit/1000/60;
							p.sendMessage(Main.getInstance().getPrefix() + "Du musst noch §6" +sekunden+(sekunden == 1 ? " Minute" : " Minuten") + "§7 warten!");
						} 
						if(restzeit < 1){
							p.sendMessage(Main.getInstance().getPrefix() + "Kit Miner wieder verfügbar!");
							cooldownminer.remove(p.getName());
						}
						
					} else {
					ItemStack pickaxe = new ItemStack(new ItemStack(Material.STONE_PICKAXE));
					ItemStack steak = new ItemStack(new ItemStack(Material.COOKED_BEEF, 2));
					ItemStack torch = new ItemStack(new ItemStack(Material.TORCH, 16));
					p.getInventory().addItem(pickaxe);
					p.getInventory().addItem(steak);
					p.getInventory().addItem(torch);
					p.sendMessage(Main.getInstance().getPrefix() + "Du hast das Miner Kit erhalten!");
					cooldownminer.put(p.getName(), jetzt);
					}
					
				} else if(e.getCurrentItem().getType() == Material.IRON_SPADE){
					p.closeInventory();
					long jetzt = System.currentTimeMillis();
					
					if(cooldownbauen.containsKey(p.getName())) {
						long benutzt = cooldownbauen.get(p.getName());
						
						int restzeit = (int) ((benutzt + (60 * 1000 * 60 * 2) ) -jetzt);
						
						if(restzeit > 0) {
							int sekunden = restzeit/1000/60;
							p.sendMessage(Main.getInstance().getPrefix() + "Du musst noch §6" +sekunden+(sekunden == 1 ? " Minute" : " Minuten") + "§7 warten!");
						}
						if(restzeit < 1){
							p.sendMessage(Main.getInstance().getPrefix() + "Kit §2Bauen §7wieder verfügbar!");
							cooldownbauen.remove(p.getName());
						}
						
					} else {
					ItemStack pickaxe = new ItemStack(new ItemStack(Material.DIRT, 64));
					ItemStack steak = new ItemStack(new ItemStack(Material.COBBLESTONE, 32));
					ItemStack torch = new ItemStack(new ItemStack(Material.LOG, 16));
					ItemStack mm = new ItemStack(new ItemStack(Material.LOG, 16, (short)2));
					p.getInventory().addItem(pickaxe);
					p.getInventory().addItem(steak);
					p.getInventory().addItem(torch);
					p.getInventory().addItem(mm);
					p.sendMessage(Main.getInstance().getPrefix() + "Du hast das Kit §2Bauen §7erhalten!");
					cooldownbauen.put(p.getName(), jetzt);
					}
					
				} else if(e.getCurrentItem().getType() == Material.IRON_INGOT){
					p.closeInventory();
					long jetzt = System.currentTimeMillis();
					
					if(cooldownerze.containsKey(p.getName())) {
						long benutzt = cooldownerze.get(p.getName());
						
						int restzeit = (int) ((benutzt + (60 * 1000 * 60 * 24) ) -jetzt);
						
						if(restzeit > 0) {
							int sekunden = restzeit/1000/60/60;
							p.sendMessage(Main.getInstance().getPrefix() + "Du musst noch §6" +sekunden+(sekunden == 1 ? " Stunde" : " Stunden") + "§7 warten!");
						}
						if(restzeit < 1){
							p.sendMessage(Main.getInstance().getPrefix() + "Kit §dErze §7wieder verfügbar!");
							cooldownerze.remove(p.getName());
						}
						
					} else {
						ItemStack iron1 = new ItemStack(new ItemStack(Material.IRON_INGOT, 16));
						ItemStack dia1 = new ItemStack(new ItemStack(Material.DIAMOND, 3));
						ItemStack m = new ItemStack(new ItemStack(Material.COAL, 16));
						
						
						
						p.getInventory().addItem(iron1);
						p.getInventory().addItem(dia1);
						p.getInventory().addItem(m);
						p.getInventory().addItem(new ItemStack(Material.LAPIS_BLOCK));
						p.sendMessage(Main.getInstance().getPrefix() + "Du hast das §dErzekit §7erhalten!");
						cooldownerze.put(p.getName(), jetzt);
					}
					
				} else if(e.getCurrentItem().getType() == Material.GOLD_SWORD){
					p.closeInventory();
					try{
					InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
	                if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Spieler").getPermission()) {
					long jetzt = System.currentTimeMillis();
					
					if(cooldownpremium.containsKey(p.getName())) {
						long benutzt = cooldownpremium.get(p.getName());
						
						int restzeit = (int) ((benutzt + (60 * 1000 * 60 * 3) ) -jetzt);
						
						if(restzeit > 0) {
							int sekunden = restzeit/1000/60;
							p.sendMessage(Main.getInstance().getPrefix() + "Du musst noch §6" +sekunden+(sekunden == 1 ? " Minute" : " Minuten") + "§7 warten!");
						}
						
						if(restzeit < 1){
							p.sendMessage(Main.getInstance().getPrefix() + "Kit §6Premium §7wieder verfügbar!");
							cooldownerze.remove(p.getName());
						}
						
					} else {
					ItemStack sword = new ItemStack(new ItemStack(Material.DIAMOND_SWORD));
					ItemStack apple = new ItemStack(new ItemStack(Material.GOLDEN_APPLE, 1));
					ItemStack helm = new ItemStack(new ItemStack(Material.IRON_HELMET));
					ItemStack brust = new ItemStack(new ItemStack(Material.IRON_CHESTPLATE));
					ItemStack hose = new ItemStack(new ItemStack(Material.IRON_LEGGINGS));
					ItemStack schuhe = new ItemStack(new ItemStack(Material.IRON_BOOTS));
					p.getInventory().addItem(sword);
					p.getInventory().addItem(apple);
					p.getInventory().addItem(helm);
					p.getInventory().addItem(brust);
					p.getInventory().addItem(hose);
					p.getInventory().addItem(schuhe);
					p.sendMessage(Main.getInstance().getPrefix() + "Du hast das §6Premiumkit §7erhalten!");
					cooldownpremium.put(p.getName(), jetzt);
					}
					
	                } else {
	                	e.setCancelled(true);
	                }
	                
				}catch(Exception exception){
					p.sendMessage(Main.getInstance().getPrefix() + "Du bist kein §6Premium §7Spieler");
				}
					
					
					
				} else if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD){
					p.closeInventory();
					try{
					InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
	                if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Spieler").getPermission()) {
					long jetzt = System.currentTimeMillis();
					
					if(cooldownyt.containsKey(p.getName())) {
						long benutzt = cooldownyt.get(p.getName());
						
						int restzeit = (int) ((benutzt + (60 * 1000 * 30) ) -jetzt);
						
						if(restzeit > 0) {
							int sekunden = restzeit/1000/60;
							p.sendMessage(Main.getInstance().getPrefix() + "Du musst noch §6" +sekunden+(sekunden == 1 ? " Minute" : " Minuten") + "§7 warten!");
						}
						
						if(restzeit < 1){
							p.sendMessage(Main.getInstance().getPrefix() + "Kit §5Youtuber §7wieder verfügbar!");
							cooldownyt.remove(p.getName());
						}
						
					} else {
					ItemStack sword = new ItemStack(new ItemStack(Material.DIAMOND_SWORD));
					ItemMeta smeta = sword.getItemMeta();
					smeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
					sword.setItemMeta(smeta);
					ItemStack apple = new ItemStack(new ItemStack(Material.GOLDEN_APPLE, 3));
					ItemStack helm = new ItemStack(new ItemStack(Material.IRON_HELMET));
					ItemStack brust = new ItemStack(new ItemStack(Material.IRON_CHESTPLATE));
					ItemStack hose = new ItemStack(new ItemStack(Material.IRON_LEGGINGS));
					ItemStack schuhe = new ItemStack(new ItemStack(Material.IRON_BOOTS));
					p.getInventory().addItem(sword);
					p.getInventory().addItem(apple);
					p.getInventory().addItem(helm);
					p.getInventory().addItem(brust);
					p.getInventory().addItem(hose);
					p.getInventory().addItem(schuhe);
					p.getInventory().addItem(new ItemStack(Material.POTION, 3, (short) 16453));
					p.sendMessage(Main.getInstance().getPrefix() + "Du hast das §5Youtuberkit §7erhalten!");
					cooldownyt.put(p.getName(), jetzt);
					}
					
	                }
					
				}catch(Exception exception){
					p.sendMessage(Main.getInstance().getPrefix() + "Nur für §5Youtuber§7!");
				}
					
				}  else if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
					e.setCancelled(true);
				}
			}
		}

	
	}
	
	


}
