package de.mertero.pvp.events;

import org.bukkit.entity.Player;

import de.mertero.pvp.main.NickAPI;
import de.mertero.pvp.main.PlayInfinityAPI;
import de.mertero.pvp.mysql.MySQLStats;

public class InfinityScoreboard {

	int z = 0;
	private Player p;
	public PacketScoreboard scoreboard;

	public InfinityScoreboard(Player p, PlayInfinityAPI.PlayerAPI.InfinityPlayer ip) {

		this.p = p;
		this.scoreboard = new PacketScoreboard(p);

		int kills = MySQLStats.getKills(NickAPI.getRealName(p));
		int deaths = MySQLStats.getDeaths(NickAPI.getRealName(p));
		double KD = (double) kills / (double) deaths;
		KD = ((double) ((int) (KD * 100))) / 100;
		if (kills == 0 && deaths == 0) {
			scoreboard.remove();
			scoreboard.sendSidebar("§b§lPlayInfinity");
			scoreboard.setLine(11, "");
			scoreboard.setLine(10, "§6Spielmodus§7:");
			scoreboard.setLine(9, "§3PvP");
			scoreboard.setLine(8, " ");
			scoreboard.setLine(7, "§6Kills§7:");
			scoreboard.setLine(6, "§30");
			scoreboard.setLine(5, "  ");
			scoreboard.setLine(4, "§6Deaths§7: ");
			scoreboard.setLine(3, "§30");
			scoreboard.setLine(2, "   ");
			scoreboard.setLine(1, "§6KD§7: ");
			scoreboard.setLine(0, "§30");
		} else if (deaths == 0) {
			scoreboard.remove();
			scoreboard.sendSidebar("§b§lPlayInfinity");
			scoreboard.setLine(11, "");
			scoreboard.setLine(10, "§6Spielmodus§7:");
			scoreboard.setLine(9,  "§3PvP");
			scoreboard.setLine(8, " ");
			scoreboard.setLine(7, "§6Kills§7:");
			scoreboard.setLine(6, "§3" + MySQLStats.getKills(p.getName()));
			scoreboard.setLine(5, "  ");
			scoreboard.setLine(4, "§6Deaths§7: ");
			scoreboard.setLine(3, "§30");
			scoreboard.setLine(2, "   ");
			scoreboard.setLine(1, "§6KD§7: ");
			scoreboard.setLine(0, "§3" + MySQLStats.getKills(p.getName()));
		} else {
			scoreboard.remove();
			scoreboard.sendSidebar("§b§lPlayInfinity");
			scoreboard.setLine(11, "");
			scoreboard.setLine(10, "§6Spielmodus§7:");
			scoreboard.setLine(9,  "§3PvP");
			scoreboard.setLine(8, " ");
			scoreboard.setLine(7, "§6Kills§7:");
			scoreboard.setLine(6, "§3" + MySQLStats.getKills(p.getName()));
			scoreboard.setLine(5, "  ");
			scoreboard.setLine(4, "§6Deaths§7: ");
			scoreboard.setLine(3, "§3" + MySQLStats.getDeaths(p.getName()));
			scoreboard.setLine(2, "   ");
			scoreboard.setLine(1, "§6KD§7: ");
			scoreboard.setLine(0, "§3" + KD);
		}

	}

	public void update(PlayInfinityAPI.PlayerAPI.InfinityPlayer ip) {

		scoreboard.removeLine(6);
		scoreboard.removeLine(3);
		scoreboard.removeLine(0);

		int kills = MySQLStats.getKills(NickAPI.getRealName(p));
		int deaths = MySQLStats.getDeaths(NickAPI.getRealName(p));
		double KD = (double) kills / (double) deaths;
		KD = ((double) ((int) (KD * 100))) / 100;

		scoreboard.setLine(6, "§3" + MySQLStats.getKills(p.getName()));
		scoreboard.setLine(3, "§3" + MySQLStats.getDeaths(p.getName()));
		scoreboard.setLine(0, "§3" + KD);

	}
}
