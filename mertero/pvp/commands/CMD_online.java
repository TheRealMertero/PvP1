package de.mertero.pvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import de.mertero.pvp.main.Main;

public class CMD_online implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
				if(sender instanceof Player){
					Player p = (Player)sender;
					if(cmd.getName().equalsIgnoreCase("online")){
						if(args.length == 0){
							
							Inventory inv = Bukkit.createInventory(null, 5*9, "§bOnline Spieler");

							for(Player alive : Bukkit.getOnlinePlayers())
							{
								ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
								SkullMeta skullmeta = (SkullMeta)  head.getItemMeta();
								skullmeta.setOwner(alive.getName());
								skullmeta.setDisplayName(alive.getName());
								head.setItemMeta(skullmeta);
								inv.addItem(head);
							}
							p.openInventory(inv);
							
						} else {
							p.sendMessage(Main.getInstance().getPrefix() + "Benutze /online");
						}
					}
				}
		
		
		return false;
	}

}
