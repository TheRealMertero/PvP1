package de.mertero.pvp.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;


import de.mertero.pvp.main.Main;


public class MySQLStats {
	

	public static boolean isRegistered(String name) {

		ResultSet rs = Main.mysql.query("SELECT * FROM pvp WHERE name = '" + name + "' LIMIT 1");
		System.out.println(rs == null);
		try {
			return rs.next();
		} catch (SQLException e) {
		}
		return false;
	}

	public static void register(String name) {
		Main.mysql.update("INSERT INTO pvp (name, kills, deaths) VALUES ('" + name + "', 0, 0)");
		
	}

	public static int getKills(String name) {
		ResultSet rs = Main.mysql.query("SELECT kills FROM pvp WHERE name = '" + name + "' LIMIT 1");
		
		try {
			while (rs.next()){
			return rs.getInt("kills"); }
		} catch (SQLException e) {
		}
		return -1;
	}

	public static int getDeaths(String name) {
		ResultSet rs = Main.mysql.query("SELECT deaths FROM pvp WHERE name = '" + name + "' LIMIT 1");
		
		try {
			while(rs.next()){
			return rs.getInt("deaths");}
		} catch (SQLException e) {
		}
		return -1;
	}

	public static void setKills(String name, int amount) {
		Main.mysql.update("UPDATE pvp SET kills = " + amount + " WHERE name = '" + name + "'");
	}

	public static void setDeaths(String name, int amount) {
		Main.mysql.update("UPDATE pvp SET deaths = " + amount + " WHERE name = '" + name + "'");
	}
	
	public static String getTopNumber3() {
		ResultSet rs = Main.mysql.query("SELECT name, kills FROM pvp ORDER BY kills DESC LIMIT 3");
		
		String array = new String();
		try {
			while(rs.next()) {
				array = rs.getString("name") + "  Kills: " + rs.getInt("kills");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return array;
		}
		return array;
	}
	
	
	public static String getTopNumber2() {
		ResultSet rs = Main.mysql.query("SELECT name, kills FROM pvp ORDER BY kills DESC LIMIT 2");
		
		String array = new String();
		try {
			while(rs.next()) {
				array = rs.getString("name") + "  Kills: " + rs.getInt("kills");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return array;
		}
		return array;
	}
	
	public static String getTopNumber1() {
		ResultSet rs = Main.mysql.query("SELECT name, kills FROM pvp ORDER BY kills DESC LIMIT 1");
		
		String array = new String();
		try {
			while(rs.next()) {
				array = rs.getString("name") + " Kills: " + rs.getInt("kills");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return array;
		}
		return array;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}