package de.mertero.pvp.events;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.mertero.pvp.commands.CMD_home;
import de.mertero.pvp.commands.CMD_warp;
import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;
import de.mertero.pvp.manager.EnchantingTable;
import de.mertero.pvp.manager.EnchantingTable.EnchantingSlot;
import de.mertero.pvp.mysql.MySQLStats;

public class InventoryClickListener implements Listener {

	public static File homefile = CMD_home.homefile;
	public static YamlConfiguration homecfg = CMD_home.homecfg;

	public static File warpsfile = CMD_warp.warpsFile;
	public static YamlConfiguration warpscfg = CMD_warp.warpscfg;

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClickInv(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getClickedInventory().getName() == "§bMenu") {
			if (e.getClick().isLeftClick() || e.getClick().isRightClick()) {
				if (e.getCurrentItem().getType() == Material.BOOK) {
					p.closeInventory();
					int deaths = MySQLStats.getDeaths(p.getName());
					int kills = MySQLStats.getKills(p.getName());
					if (kills == 0 && deaths == 0) {
						// p.sendMessage("§e-=-§aStats von §6" +
						// NickAPI.getRealName(p) + " §e-=-");
						p.sendMessage("§e-=-§a Stats von §6" + p.getName() + " §e-=-");
						p.sendMessage("§aKills: §60");
						p.sendMessage("§aDeaths: §60");
						p.sendMessage("§aK/D: §60");
						p.sendMessage("§e-=-=-=-=-=-=-=-=-=-=-=-");
					} else if (deaths == 0) {
						// p.sendMessage("§e-=-§aStats von §6" +
						// NickAPI.getRealName(p) + " §e-=-");
						p.sendMessage("§e-=-§a Stats von §6" + p.getName() + " §e-=-");
						p.sendMessage("§aKills: §6" + kills);
						p.sendMessage("§aDeaths: §60");
						p.sendMessage("§aK/D: §6" + kills);
						p.sendMessage("§e-=-=-=-=-=-=-=-=-=-=-=-");

					} else {
						double KD = (double) kills / (double) deaths;
						KD = ((double) ((int) (KD * 100))) / 100;
						// p.sendMessage("§e-=-§aStats von §6" +
						// NickAPI.getRealName(p) + " §e-=-");
						p.sendMessage("§e-=-§a Stats von §6" + p.getName() + " §e-=-");
						p.sendMessage("§aKills: §6" + kills);
						p.sendMessage("§aDeaths: §6" + deaths);
						p.sendMessage("§aK/D: §6" + KD);
						p.sendMessage("§e-=-=-=-=-=-=-=-=-=-=-=-");
					}

				} else if (e.getCurrentItem().getType() == Material.PAPER) {
					String name1 = Bukkit.getOfflinePlayer(MySQLStats.getTopNumber1().toString()).getName();
					String name2 = Bukkit.getOfflinePlayer(MySQLStats.getTopNumber2().toString()).getName();
					String name3 = Bukkit.getOfflinePlayer(MySQLStats.getTopNumber3().toString()).getName();
					p.closeInventory();
					p.sendMessage("§e-=- §bDie Top 3 §e-=-");
					p.sendMessage("§aPlatz 1: §b" + name1);
					p.sendMessage("§aPlatz 2: §b" + name2);
					p.sendMessage("§aPlatz 3: §b" + name3);
					p.sendMessage("§e-=-=-=-=-=-=-=-");
				} else if (e.getCurrentItem().getType() == Material.BED) {
					p.closeInventory();
					String name1 = p.getName();
					String homename1 = homecfg.getString(name1 + ".homename");
					if (homecfg.contains(name1) && homename1 != null) {
						p.sendMessage(Main.getInstance().getPrefix() + "§7Homes:§a " + homename1);
					} else {
						p.sendMessage(Main.getInstance().getPrefix() + "Home wurde noch nicht gesetzt!");
					}
				} else if (e.getCurrentItem().getType() == Material.DIAMOND_AXE) {
					p.closeInventory();
					p.sendMessage(Main.getInstance().getPrefix() + "/kits");
				} else if (e.getCurrentItem().getType() == Material.COMPASS) {
					p.closeInventory();
					if (!Main.combat.contains(p)) {
						p.teleport(Main.getInstance().getLm().getLocation("spawn"));
						p.sendMessage(Main.getInstance().getPrefix() + "Teleportieren...");
						p.sendMessage(Main.getInstance().getPrefix() + "Du wurdest zum Spawn teleportiert!");
					} else {
						p.sendMessage(Main.getInstance().getPrefix() + "Im Kampf nicht möglich!");
					}

				} else if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
					p.closeInventory();
					if (!Main.combat.contains(p)) {
						p.teleport(Main.getInstance().getLm().getLocation("arena"));
						p.sendMessage(Main.getInstance().getPrefix() + "Teleportieren...");
						p.sendMessage(Main.getInstance().getPrefix() + "Du wurdest zur Arena teleportiert!");
					} else {
						p.sendMessage(Main.getInstance().getPrefix() + "Im Kampf nicht möglich!");
					}

				} else if (e.getCurrentItem().getType() == Material.ENCHANTMENT_TABLE) {
					p.closeInventory();
					try {
						InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI()
								.getPlayer(NickAPI.getRealName(p));
						if (ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI()
								.getRankByName("Spieler").getPermission()) {
							EnchantingTable ench = new EnchantingTable(p);
							// Title setzen bevor ihr das inventar öffnet!
							// Title kann eeeewig sein --> keine begrenzung -->
							// namen bis zum bildschirmrand:D
							ench.setTitle("Enchanter");
							// Öffnen
							// In Ench. Inv Item setzen (EnchantingSlot.ENCHANT
							// oder EnchantingSlot.LAPIS) NUR ÄNDERBAR FALLS INV
							// DEFINITIV OFFEN ANSONSTEN CODE ÄNDERN
							ench.addItem(EnchantingSlot.LAPIS, null);
							// Das wichtigste::D
							// Siehe:
							// http://minecraft-de.gamepedia.com/Zaubertisch
							// dort scrollt ihr runter und seht ab wie viel
							// Bookshelfs wie eure LevelVorschläge ändern:)
							// 1231 ist natürlich unmöglich und zuhoch --> hier
							// wird nur das max. 15 genommen(ist also ok)
							// Immer editierbar, egal zuwelcher Zeit!:D
							ench.setNotRealBookshelfAmount(1231);
							ench.open();
						}
					} catch (Exception exception) {
						p.sendMessage(Main.getInstance().getPrefix() + "Erst ab §6Premium§7 verfügbar!");
					}

				} else if (e.getCurrentItem().getType() == Material.WORKBENCH) {
					p.closeInventory();
					try {
						InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI()
								.getPlayer(NickAPI.getRealName(p));
						if (ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI()
								.getRankByName("Premium").getPermission()) {
							p.sendMessage(Main.getInstance().getPrefix() + "Öffne Workbench...");
							p.openWorkbench(null, true);

						}
					} catch (Exception exception) {
						p.sendMessage(Main.getInstance().getPrefix() + "Erst ab §6Premium§7 verfügbar!");
					}

				} else if (e.getCurrentItem().getType() == Material.EMERALD) {
					p.closeInventory();

					StringBuilder sb = new StringBuilder();
					sb.append(Main.getInstance().getPrefix() + "§aWarps: §6");

					int i = 0;
					for (String warp : warpscfg.getKeys(true)) {
						if (warpscfg.contains(warp + ".name")) {
							if (i == 1) {
								sb.append("§7, §6");
							} else {
								i = 1;
							}
							sb.append(warpscfg.getString(warp + ".name"));
						}
					}
					p.sendMessage(sb.toString());

				} else if (e.getCurrentItem().getType() == Material.SIGN) {
					p.closeInventory();
					p.sendMessage(" §e-=-§a HELP §e-=-");
					p.sendMessage("   §a/help ");
					p.sendMessage("   §a/stats [Name]");
					p.sendMessage("   §a/top3 ");
					p.sendMessage("   §a/spawn ");
					p.sendMessage("   §a/warp");
					p.sendMessage("   §a/home ");
					p.sendMessage("   §a/tpa ");
					p.sendMessage("   §a/online ");
					p.sendMessage("   §a/kits ");
					p.sendMessage(" §e-=- -=- -=-");

				} else if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
					e.setCancelled(true);
				}
			}
		}

	}


}
