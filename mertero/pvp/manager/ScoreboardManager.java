package de.mertero.pvp.manager;

import org.bukkit.entity.Player;

import de.mertero.pvp.events.InfinityScoreboard;
import de.mertero.pvp.main.PlayInfinityAPI;

import java.util.HashMap;

public class ScoreboardManager {

    private static HashMap<Player, InfinityScoreboard> scoreboards = new HashMap<>();

    public static void createBoard(Player p, PlayInfinityAPI.PlayerAPI.InfinityPlayer ip) {
        scoreboards.put(p, new InfinityScoreboard(p, ip));
    }

    public static void removeBoard(Player p) {
        if (hasPlayerScoreboard(p)) {
            scoreboards.get(p).scoreboard.remove();
            scoreboards.remove(p);
        }
    }

    public static void updateBoard(Player p, PlayInfinityAPI.PlayerAPI.InfinityPlayer ip) {
        if(hasPlayerScoreboard(p)) {
            scoreboards.get(p).update(ip);
        }
    }

    public static boolean hasPlayerScoreboard(Player p) {
        return scoreboards.containsKey(p);
    }
}
