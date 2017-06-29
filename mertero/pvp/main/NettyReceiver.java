package de.mertero.pvp.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NettyReceiver {

    public static void receive(String[] args) {
        if (args[0].equalsIgnoreCase("player")) {
            if (args[1].equalsIgnoreCase("update")) {
                Player t = Bukkit.getPlayer(args[2]);
                if (t != null) {
                    Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
                        Main.getInstance().getAPI().getPlayerAPI().loadPlayer(t.getName());
                        Main.setTab(t);
                    });
                }
            }
        }
    }
}