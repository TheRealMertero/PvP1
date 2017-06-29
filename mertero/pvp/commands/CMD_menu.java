package de.mertero.pvp.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CMD_menu implements CommandExecutor {
	


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
					if(sender instanceof Player){
						Player p = (Player)sender;
						if(cmd.getName().equalsIgnoreCase("menu")){
							Inventory menu = Bukkit.createInventory(p, 5*9, "§bMenu");
							
							ItemStack book = new ItemStack(Material.BOOK);
							ItemMeta bookmeta = book.getItemMeta();
							bookmeta.setDisplayName("§bStats");
							
							ArrayList<String> lorebook = new ArrayList<>();
							lorebook.add("§7Deine §bStats §7auf einem Blick!");
							bookmeta.setLore(lorebook);
							book.setItemMeta(bookmeta);
							
							ItemStack book1 = new ItemStack(Material.PAPER);
							ItemMeta bookmeta1 = book1.getItemMeta();
							bookmeta1.setDisplayName("§5Die Top 3");
							
							ArrayList<String> lorebook1 = new ArrayList<>();
							lorebook1.add("§7Zeigt dir die §53 besten Spieler§7 an!");
							bookmeta1.setLore(lorebook1);
							book1.setItemMeta(bookmeta1);
							
							ItemStack book2 = new ItemStack(Material.BED);
							ItemMeta bookmeta2 = book2.getItemMeta();
							bookmeta2.setDisplayName("§aHomes");
							
							ArrayList<String> lorebook2 = new ArrayList<>();
							lorebook2.add("§7Lass dir deine §aHomes§7 anzeigen!");
							bookmeta2.setLore(lorebook2);
							book2.setItemMeta(bookmeta2);
							
							ItemStack book3 = new ItemStack(Material.DIAMOND_AXE);
							ItemMeta bookmeta3 = book3.getItemMeta();
							bookmeta3.setDisplayName("§1Kits");
							
							ArrayList<String> lorebook3 = new ArrayList<>();
							lorebook3.add("§7Such dir ein §1Kit§7 aus!");
							bookmeta3.setLore(lorebook3);
							book3.setItemMeta(bookmeta3);
							
							ItemStack book4 = new ItemStack(Material.COMPASS);
							ItemMeta bookmeta4 = book4.getItemMeta();
							bookmeta4.setDisplayName("§dSpawn");
							
							ArrayList<String> lorebook4 = new ArrayList<>();
							lorebook4.add("§7Teleportiere dich zum §dSpawn");
							bookmeta4.setLore(lorebook4);
							book4.setItemMeta(bookmeta4);
							
							ItemStack book5 = new ItemStack(Material.DIAMOND_SWORD);
							ItemMeta bookmeta5 = book5.getItemMeta();
							bookmeta5.setDisplayName("§3Arena");
							
							ArrayList<String> lorebook5 = new ArrayList<>();
							lorebook5.add("§7Teleportiere dich zur §3Arena");
							bookmeta5.setLore(lorebook5);
							book5.setItemMeta(bookmeta5);
							
							ItemStack book6 = new ItemStack(Material.ENCHANTMENT_TABLE);
							ItemMeta bookmeta6 = book6.getItemMeta();
							bookmeta6.setDisplayName("§9Enchantment Table");
							
							ArrayList<String> lorebook6 = new ArrayList<>();
							lorebook6.add("§7Öffne einen §9Enchantment Table");
							bookmeta6.setLore(lorebook6);
							book6.setItemMeta(bookmeta6);
							
							ItemStack book7 = new ItemStack(Material.WORKBENCH);
							ItemMeta bookmeta7 = book7.getItemMeta();
							bookmeta7.setDisplayName("§6Workbench");
							
							ArrayList<String> lorebook7 = new ArrayList<>();
							lorebook7.add("§7Öffne eine §6Workbench");
							bookmeta7.setLore(lorebook7);
							book7.setItemMeta(bookmeta7);
							
							ItemStack book8 = new ItemStack(Material.EMERALD);
							ItemMeta bookmeta8 = book8.getItemMeta();
							bookmeta8.setDisplayName("§aWarps");
							
							ArrayList<String> lorebook8 = new ArrayList<>();
							lorebook8.add("§7Lass dir alle derzeitigen §aWarps §7anzeigen!");
							bookmeta8.setLore(lorebook8);
							book8.setItemMeta(bookmeta8);
							
							ItemStack book9 = new ItemStack(Material.SIGN);
							ItemMeta bookmeta9 = book9.getItemMeta();
							bookmeta9.setDisplayName("§2Help");
							
							ArrayList<String> lorebook9 = new ArrayList<>();
							lorebook9.add("§7Zeigt dir alle hilfreichen §2Commands §7an!");
							bookmeta9.setLore(lorebook9);
							book9.setItemMeta(bookmeta9);
							
							ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
							int i = -1;
							while(i != 11){
								i++;
							menu.setItem(i, glass);
							}
							//2,4,6
							menu.setItem(12, glass);
							menu.setItem(14, glass);
							menu.setItem(22, glass);
							menu.setItem(20, glass);
							menu.setItem(24, glass);
							menu.setItem(30, glass);
							menu.setItem(32, glass);
							
							
							int e = 15;
							while(e != 19){
								e++;
							menu.setItem(e, glass);
							}
							
							
							
							int f = 25;
							while(f != 29){
								f++;
							menu.setItem(f, glass);
							}
							
							menu.setItem(11, book4);
							menu.setItem(13, book5);
							menu.setItem(15, book8);
							menu.setItem(19, book3);
							
							menu.setItem(21, book2);
							menu.setItem(23, book1);
							menu.setItem(25, book);
							
							menu.setItem(29, book6);
							menu.setItem(31, book9);
							menu.setItem(33, book7);
							
							
							int d = 33;
							while(d != 44){
								d++;
							menu.setItem(d, glass);
							}
							
							p.openInventory(menu);
						}
					}
		return false;
	}

}
