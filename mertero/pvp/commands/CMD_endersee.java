package de.mertero.pvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.mertero.pvp.main.Main;
import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI.PlayerAPI.InfinityPlayer;

public class CMD_endersee implements CommandExecutor {

	private Inventory inv = null;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("endersee")) {
            if (sender instanceof Player) {
 
                Player p = (Player) sender;
                try{
                InfinityPlayer ip = Main.getInstance().getAPI().getPlayerAPI().getPlayer(NickAPI.getRealName(p));
                if (ip.getRang().getPermission() >= Main.getInstance().getAPI().getRangAPI().getRankByName("Moderator").getPermission()) {
                   
                    if (args.length == 0) {
                        p.sendMessage(Main.getInstance().getPrefix() + "§cSyntax§7: /§6endersee §7<§6Name§7>");
                       
                    } else if(args.length == 1){
                        try{
                            Player target = Bukkit.getPlayer(args[0]);
                           
                            p.openInventory(target.getEnderChest());
                        } catch(NullPointerException e){
                            p.sendMessage(Main.getInstance()  + "§cDer Spieler ist nicht online.");
                        }
                    } else {
                        p.sendMessage(Main.getInstance() + "§cZu viele Argumente");
                    }
                }
                
            }catch(Exception ex){
            }
                
            }
        }
        return false;
    }

}
