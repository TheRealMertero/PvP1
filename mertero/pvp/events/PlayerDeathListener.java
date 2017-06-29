package de.mertero.pvp.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;
import de.mertero.pvp.manager.ScoreboardManager;
import de.mertero.pvp.mysql.MySQLStats;


public class PlayerDeathListener implements Listener {
	
	int killstreak = 1;
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player k = p.getKiller();
		
		e.setDeathMessage("");
		InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
		ScoreboardManager.updateBoard(p, ip);
		MySQLStats.setKills(k.getName(), MySQLStats.getKills(k.getName()) +1);
		ScoreboardManager.updateBoard(k, ip);
		MySQLStats.setDeaths(p.getName(), MySQLStats.getDeaths(p.getName()) +1);
		ScoreboardManager.updateBoard(p, ip);
		
		k.playSound(k.getLocation(), Sound.LEVEL_UP, 2, 2);
		k.getInventory().addItem(new ItemStack(Material.EMERALD));
		
		
		if(!Main.getInstance().getKillstrike().containsKey(k)) {
			
			Main.getInstance().getKillstrike().put(k, 1);
			k.sendMessage(Main.getInstance().getPrefix() + "Deine Killstreak startet!");
			
		} else {
			
			killstreak++;
			ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
			k.sendMessage(Main.getInstance().getPrefix() + "Deine Killstreak liegt bei " + killstreak + " Kills");
			if(killstreak == 7) {
				
				k.getInventory().addItem(diamond);
				k.sendMessage(Main.getInstance().getPrefix() + "Für 7 Kills erhältst du 1 Diamanten");
				Bukkit.broadcastMessage(Main.getInstance().getPrefix() + k.getName() + " hat eine Killstreak von §a7 §7erreicht!");
				
			} else if(killstreak == 15) {
				
				ItemStack diamond2 = new ItemStack(Material.EMERALD, 3);
				k.getInventory().addItem(diamond2);
				k.sendMessage(Main.getInstance().getPrefix() + "FÜr 15 Kills erhältst du 3 Diamanten");
				Bukkit.broadcastMessage(Main.getInstance().getPrefix() + k.getName() + " hat eine Killstreak von §a15 §7erreicht!");
				
			} else if(killstreak == 30) {
				
				ItemStack diamond3 = new ItemStack(Material.EMERALD, 6);
				ItemStack apfel = new ItemStack(Material.GOLDEN_APPLE, 3);
				k.getInventory().addItem(diamond3);
				k.getInventory().addItem(apfel);
				k.sendMessage(Main.getInstance().getPrefix() + "Für 30 Kills erhältst du 6 Emeralds und einen Gold Apfel");
				Bukkit.broadcastMessage(Main.getInstance().getPrefix() + k.getName() + " hat eine Killstreak von §a30 §7erreicht!");
				
			}
		}
		if(Main.getInstance().getKillstrike().containsKey(p)) {
			p.sendMessage(Main.getInstance().getPrefix() + "Deine Killstreak ist vorbei. Erreichte Kills: §a" + killstreak);
			Main.getInstance().getKillstrike().remove(p);
			killstreak = 1;
		}
		
		if(Main.combat.contains(p)){
			Main.combat.remove(p);
		}
		p.spigot().respawn();

	}
	

}
