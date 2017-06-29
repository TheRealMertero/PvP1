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
						//Title setzen bevor ihr das inventar �ffnet!
						//Title kann eeeewig sein --> keine begrenzung --> namen bis zum bildschirmrand:D
						ench.setTitle("�5Enchanter");
						//�ffnen
						ench.open();
						//In Ench. Inv Item setzen (EnchantingSlot.ENCHANT oder EnchantingSlot.LAPIS) NUR �NDERBAR FALLS INV DEFINITIV OFFEN ANSONSTEN CODE �NDERN
						ench.addItem(EnchantingSlot.LAPIS, null);
						//Das wichtigste::D
						//Siehe: http://minecraft-de.gamepedia.com/Zaubertisch dort scrollt ihr runter und seht ab wie viel Bookshelfs wie eure LevelVorschl�ge �ndern:)
						//1231 ist nat�rlich unm�glich und zuhoch --> hier wird nur das max. 15 genommen(ist also ok)
						//Immer editierbar, egal zuwelcher Zeit!:D
						ench.setNotRealBookshelfAmount(1231);
								}
							}catch(Exception exception){
								p.sendMessage(Main.getInstance().getPrefix() + "Erst ab Premium verf�gbar!");
							}
						
						
							}
						}
					}
		
		
		
		return false;
	}

}
