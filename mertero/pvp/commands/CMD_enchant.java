package de.mertero.pvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;
import de.mertero.pvp.manager.EnchantingTable;
import de.mertero.pvp.manager.EnchantingTable.EnchantingSlot;

public class CMD_enchant implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
					if(sender instanceof Player){
						Player p = (Player)sender;
						if(cmd.getName().equalsIgnoreCase("enchant")){
							if(args.length == 0){
								try{
								InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
								if(ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Premium").getPermission()){
						EnchantingTable ench = new EnchantingTable(p);
						//Title setzen bevor ihr das inventar öffnet!
						//Title kann eeeewig sein --> keine begrenzung --> namen bis zum bildschirmrand:D
						ench.setTitle("§5Enchanter");
						//Öffnen
						ench.open();
						//In Ench. Inv Item setzen (EnchantingSlot.ENCHANT oder EnchantingSlot.LAPIS) NUR ÄNDERBAR FALLS INV DEFINITIV OFFEN ANSONSTEN CODE ÄNDERN
						ench.addItem(EnchantingSlot.LAPIS, null);
						//Das wichtigste::D
						//Siehe: http://minecraft-de.gamepedia.com/Zaubertisch dort scrollt ihr runter und seht ab wie viel Bookshelfs wie eure LevelVorschläge ändern:)
						//1231 ist natürlich unmöglich und zuhoch --> hier wird nur das max. 15 genommen(ist also ok)
						//Immer editierbar, egal zuwelcher Zeit!:D
						ench.setNotRealBookshelfAmount(1231);
								}
							}catch(Exception exception){
								p.sendMessage(Main.getInstance().getPrefix() + "Erst ab Premium verfügbar!");
							}
						
						
							}
						}
					}
		
		
		
		return false;
	}

}
