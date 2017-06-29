package de.mertero.pvp.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class MySQL {

	  private String HOST = "";
	  private String DATABASE = "";
	  private String USER = "";
	  private String PASSWORD = "";
	  private Connection con; 
	  private String host, user, password, database;

	  public MySQL(String host, String database, String user, String password) {

	    this.HOST = host;
	    this.DATABASE = database;
	    this.USER = user;
	    this.PASSWORD = password;

	  }

	  public void connect() {
	    try {
	      this.con = ((Connection) DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":3306/" + this.DATABASE + "?autoReconnect=true", this.USER, this.PASSWORD));
	      System.out.println("[MySQL] verbindung erfolgreich aufgebaut!");
	    } catch (Exception localException) {
	    }
	  }

	  public void close() {
	    try {
	      if (this.con != null) {
	        this.con.close();
	      }
	    } catch (Exception localException) {
	    }
	  }

	  public void update(String qry) {
	    try {
	      Statement st = (Statement) this.con.createStatement();
	      st.executeUpdate(qry);
	      st.close();
	    } catch (Exception localException) {
	    }
	  }

	  public void queryUpdateString(String query) {
         checkConnection();
         try (PreparedStatement statement = (PreparedStatement) con.prepareStatement(query)) {
            queryUpdateState(statement);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      
      public void queryUpdateState(PreparedStatement statement) {
         checkConnection();
         try {
            statement.executeUpdate();
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {
               statement.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
      
      public ResultSet query(String query) {
         checkConnection();
         try {
            return query(con.prepareStatement(query));
         } catch (Exception e) {
            e.printStackTrace();
         }
         return null;
      }
      
      public ResultSet query(PreparedStatement statement) {
         checkConnection();
         try {
            return statement.executeQuery();
         } catch (Exception e) {
            e.printStackTrace();
         }
         return null;
      }
      
      public Connection getConnection() {
         return this.con;
      }
      
      private void checkConnection() {
         try {
            if (this.con == null || !this.con.isValid(10) || this.con.isClosed()) openConnection();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      
      public Connection openConnection() throws Exception {
         Class.forName("com.mysql.jdbc.Driver");
         return this.con = ((Connection) DriverManager.getConnection("jdbc:mysql://" + this.host + ":3306/" + this.database + "?autoReconnect=true", this.user, this.password));

      }
      
      public void closeConnection() {
         try {
            this.con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            this.con = null;
        
         }
      }
}
