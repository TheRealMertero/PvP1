package de.mertero.pvp.events;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;
import de.mertero.pvp.manager.ScoreboardManager;

public class JoinLeaveListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Main.setTab(e.getPlayer());
		Player p = (Player) e.getPlayer();

		InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));

		ScoreboardManager.createBoard(e.getPlayer(), ip);
		
		if(Main.getInstance().getVanish().containsKey(p)) {
			for(Player all : Bukkit.getOnlinePlayers()){
				all.hidePlayer(p);
			}
		} else {
			for(Player all : Bukkit.getOnlinePlayers()){
				all.showPlayer(p);
			}
		}
		
		/*
		 * InfinityPlayer ip =
		 * Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.
		 * getRealName(p)); if (ip.getRang().getPermission() >=
		 * Main.getInstance().getAPI().getRangAPI().getRankByName("Streamer")
		 * .getPermission()) { if
		 * (Main.getInstance().getAPI().getPlayerAPI().getPlayer(p.getName()).
		 * getNick()) { NickAPI.nick(p, NickAPI.getRandomNameOld());
		 * p.sendTitle("§6Nick", "§aDu bist genickt als: §6"
		 * +p.getDisplayName()); p.sendMessage(
		 * "§7[§5Nick§7] §aDu bist genickt als§7: §6" + p.getDisplayName());
		 * Main.setNickTab(p); } else { Main.setTab(p); }
		 * 
		 * } else { Main.setTab(p); }
		 */
		
		if(e.getPlayer().hasPlayedBefore()){
			  
		 }else {
			 ItemStack sword = new ItemStack(new ItemStack(Material.WOOD_SWORD));
				ItemStack bread = new ItemStack(new ItemStack(Material.BREAD));
				
				
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
				
				p.sendMessage(Main.getInstance().getPrefix() + "Du hast das §6Starterkit §aerhalten!");
		}
		
		
		
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		ScoreboardManager.removeBoard(e.getPlayer());
		  
	}

}
