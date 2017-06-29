package de.mertero.pvp.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;




public class CMD_chest implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
				if(sender instanceof Player) {
					Player p = (Player)sender;
					if(cmd.getName().equalsIgnoreCase("chest")) {
						
		                InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
		                if (ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Spieler").getPermission()) {
		                	if(args.length == 0){
								p.sendMessage(Main.getInstance().getPrefix() + "Benutze /chest pvp");
								p.sendMessage(Main.getInstance().getPrefix() + "Benutze /chest spawn [Number]");
							}
							if(args[0].equalsIgnoreCase("pvp")) {
								
								ItemStack chest = new ItemStack(Material.DRAGON_EGG);
								ItemMeta meta = chest.getItemMeta();
								meta.setDisplayName("§5PvPChest");
								chest.setItemMeta(meta);
								
								p.getInventory().addItem(chest);
								
							}  else if(args[0].equals("spawn")){
								try{
								int number =  Integer.parseInt(args[1]);
								Main.getInstance().getCL().setChestNumber(number, p.getLocation());
								p.sendMessage(Main.getInstance().getPrefix() + "Du hast den Spawn ["+number+"] gesetzt");
								
							}catch(NumberFormatException ex)
							{
								p.sendMessage(Main.getInstance().getPrefix() + "Du musst eine Zahl verwenden");
							}
							}
		                }
					}
				}
		return false;
	}

}
