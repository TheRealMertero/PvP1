package de.mertero.pvp.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;


public class CMD_kits implements CommandExecutor {

	public static HashMap<String, Long> cooldown = new HashMap<>(); 
	public static HashMap<String, Long> cooldownpremium = new HashMap<>(); 
	
	
	
	/*
	 * sek = ms/1000;
	 * min = ms/1000/60;
	 * std = ms/1000/60/60;
	 */


@Override
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
		Player p = (Player)sender;
		if(args.length == 0) {
			if(cmd.getName().equalsIgnoreCase("kits")) {
				Inventory kits = Bukkit.createInventory(p, 3*9, "§bKits");
				
				ItemStack book1 = new ItemStack(Material.WOOD_SWORD);
				ItemMeta bookmeta1 = book1.getItemMeta();
				bookmeta1.setDisplayName("§7Kit §8Starter");
				
				ArrayList<String> lorebook1 = new ArrayList<>();
				lorebook1.add("§7Hol dir das §8Starterkit§7!");
				lorebook1.add("§7Wartezeit liegt bei §b1 Minute");
				lorebook1.add("§7+ Holzschwert");
				lorebook1.add("§7+ 3 Brot");
				lorebook1.add("§7+ Lederrüstung");
				bookmeta1.setLore(lorebook1);
				book1.setItemMeta(bookmeta1);
				
				ItemStack book2 = new ItemStack(Material.STONE_PICKAXE);
				ItemMeta bookmeta2 = book2.getItemMeta();
				bookmeta2.setDisplayName("§7Kit §7Miner");
				
				ArrayList<String> lorebook2 = new ArrayList<>();
				lorebook2.add("§7Hol dir das §7Minerkit!");
				lorebook2.add("§7Wartezeit liegt bei §b60 Minuten");
				lorebook2.add("§7+ Stein Spitzhacke");
				lorebook2.add("§7+ 2 Steaks");
				lorebook2.add("§7+ 16 Fackeln");
				bookmeta2.setLore(lorebook2);
				book2.setItemMeta(bookmeta2);
				
				ItemStack book3 = new ItemStack(Material.IRON_SPADE);
				ItemMeta bookmeta3 = book3.getItemMeta();
				bookmeta3.setDisplayName("§7Kit §2Bauen");
				
				ArrayList<String> lorebook3 = new ArrayList<>();
				lorebook3.add("§7Hol dir das §2Bauenkit§7!");
				lorebook3.add("§7Wartezeit liegt bei §b120 Minuten");
				lorebook3.add("§7+ 64 Erdblöcke");
				lorebook3.add("§7+ 32 Pflastersteine");
				lorebook3.add("§7+ 16 Eichenholz");
				lorebook3.add("§7+ 16 Birkenholz");
				bookmeta3.setLore(lorebook3);
				book3.setItemMeta(bookmeta3);
				
				ItemStack book4 = new ItemStack(Material.IRON_INGOT);
				ItemMeta bookmeta4 = book4.getItemMeta();
				bookmeta4.setDisplayName("§7Kit §dErze");
				
				ArrayList<String> lorebook4 = new ArrayList<>();
				lorebook4.add("§7Hol dir das §dErzekit§7!");
				lorebook4.add("§7Wartezeit liegt bei §b24 Stunden!");
				lorebook4.add("§7+ 3 Diamanten");
				lorebook4.add("§7+ 16 Iron Ore");
				lorebook4.add("§7+ 1 Lapisblock");
				lorebook4.add("§7+ 16 Kohle");
				bookmeta4.setLore(lorebook4);
				book4.setItemMeta(bookmeta4);
				
				ItemStack book5 = new ItemStack(Material.GOLD_SWORD);
				ItemMeta bookmeta5 = book5.getItemMeta();
				bookmeta5.setDisplayName("§7Kit §6Premium");
				
				ArrayList<String> lorebook5 = new ArrayList<>();
				lorebook5.add("§7Hol dir das §6Premiumkit");
				lorebook5.add("§7Wartezeit liegt bei §b180 Minuten!");
				lorebook5.add("§7+ Eisen Rüstung");
				lorebook5.add("§7+ Diamant Schwert");
				lorebook5.add("§7+ 1 Goldener Apfel");
				bookmeta5.setLore(lorebook5);
				book5.setItemMeta(bookmeta5);
				
				ItemStack book6 = new ItemStack(Material.DIAMOND_SWORD);
				ItemMeta bookmeta6 = book6.getItemMeta();
				bookmeta6.setDisplayName("§7Kit §5Youtuber");
				
				ArrayList<String> lorebook6 = new ArrayList<>();
				lorebook6.add("§7Hol dir das §5Youtuberkit");
				lorebook6.add("§7Wartezeit liegt bei §b30 Minuten");
				lorebook6.add("§7+ Eisen Rüstung");
				lorebook6.add("§7+ Verzaubertes Diamant Schwert");
				lorebook6.add("§7+ 3 Goldene Apfel");
				lorebook6.add("§7+ 3 Zauberhafte Tränke");
				bookmeta6.setLore(lorebook6);
				book6.setItemMeta(bookmeta6);
				
				ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
				
				//2,4,6
				try{
				InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
                if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Streamer").getPermission()) {
                	int i = -1;
    				while(i != 9){
    					i++;
    				kits.setItem(i, glass);
    				}
    				
    				kits.setItem(2, book6);
    				kits.setItem(6, book5);
    				kits.setItem(10, book1);
    				kits.setItem(11, glass);
    				kits.setItem(12, book2);
    				kits.setItem(13, glass);
    				kits.setItem(14, book3);
    				kits.setItem(15, glass);
    				kits.setItem(16, book4);
    				
    				int d = 16;
    				while(d != 26){
    					d++;
    				kits.setItem(d, glass);
    				}
    				
    				p.openInventory(kits);
                } else if(ip.getRang().getPermission() == Main.getInstance().getAPI().getRangAPI().getRankByName("Premium").getPermission()) {
                	int i = -1;
    				while(i != 9){
    					i++;
    				kits.setItem(i, glass);
    				}
    				
    				kits.setItem(6, book5);
    				kits.setItem(10, book1);
    				kits.setItem(11, glass);
    				kits.setItem(12, book2);
    				kits.setItem(13, glass);
    				kits.setItem(14, book3);
    				kits.setItem(15, glass);
    				kits.setItem(16, book4);
    				
    				int d = 16;
    				while(d != 26){
    					d++;
    				kits.setItem(d, glass);
    				}
    				
    				p.openInventory(kits);
                } else {
                	 int i = -1;
     				while(i != 9){
     					i++;
     				kits.setItem(i, glass);
     				}
     				
     				kits.setItem(10, book1);
     				kits.setItem(11, glass);
     				kits.setItem(12, book2);
     				kits.setItem(13, glass);
     				kits.setItem(14, book3);
     				kits.setItem(15, glass);
     				kits.setItem(16, book4);
     				
     				int d = 16;
     				while(d != 26){
     					d++;
     				kits.setItem(d, glass);
     				}
     				
     				p.openInventory(kits);
                }
                
				}catch(Exception ex){
				}
				
				
			}
				
		}
	
	}
		return false;
}

}


