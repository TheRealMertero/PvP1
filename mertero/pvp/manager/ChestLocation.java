package de.mertero.pvp.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.mertero.pvp.events.PlayerInteractListener;
import de.mertero.pvp.main.Main;




public class ChestLocation {
	
			int time = Main.getInstance().time;
			ArrayList<Integer> EndNumber = new ArrayList<>();
	

	
	public File Chestfile = new File("plugins/PvP/ChestLocations.yml");
	public FileConfiguration cfg = YamlConfiguration.loadConfiguration(Chestfile);
	
	public void setChestNumber(int number, Location loc){
		String name = "Spawn";
		cfg.set(name+"."+number+".world", loc.getWorld().getName());
		cfg.set(name+"."+number+".x",loc.getX());
		cfg.set(name+"."+number+".y", loc.getY());
		cfg.set(name+"."+number+".z", loc.getZ());
		cfg.set(name+"."+number+".yaw", loc.getYaw());
		cfg.set(name+"."+number+".pitch", loc.getPitch());
		saveCfg();
		}
	public Location getChestNumber(int number){
		String name = "Spawn";
		World w = Bukkit.getWorld(cfg.getString(name+"."+number+".world"));
		double x = cfg.getDouble(name+"."+number+".x");
		double y = cfg.getDouble(name+"."+number+".y");
		double z = cfg.getDouble(name+"."+number+".z");
		Location loc = new Location(w, x, y, z);
		loc.setYaw(cfg.getInt(name+"."+number+".yaw"));
		loc.setPitch(cfg.getInt(name+"."+number+".pitch"));
		return loc;
	}
	
	public void saveCfg(){
		try {
			cfg.save(Chestfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int rndInt(int min, int max){
		Random r = new Random();
		int i = r.nextInt((max-min) +1 ) +min;
		return i;
	}
	
	public void newRandomNumber(){
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				
				 int number2 = rndInt(1, 10);
				 EndNumber.add(number2);
			}
		}, 20*20);
	}
	public ArrayList<Integer> abc = new ArrayList<>();
	int kk;
	public void PvPChest(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				int number = rndInt(1, 10);
				Location spawn = getChestNumber(number);
				if(time == 14){
					spawn.getBlock().setType(Material.DRAGON_EGG);
					
					Bukkit.broadcastMessage(Main.getInstance().getPrefix() + "Die PvP Chest wurde an Position " + number + " gesichtet!");
					
					for(Player all : Bukkit.getOnlinePlayers()){
						all.sendTitle("§6PvP Chest", "gesichtet an Position §5" + number);
					}
					
					abc.add(number);
				}
				
				if(time == 2){
					for(Player all : Bukkit.getOnlinePlayers()){
						all.sendTitle("§6PvP Chest", "verschwunden");
					}
					
					if(!PlayerInteractListener.isUsed.isEmpty()){
						
					}
					Location spawn2 = getChestNumber(abc.get(kk));
					spawn2.getBlock().setType(Material.AIR);
					abc.remove(kk);
				}
				
				if(time == 0){
					time = 20;
				}
				
				time--;
				
			}
		}, 0, 20);
		
	}
	
	
	
	

}
