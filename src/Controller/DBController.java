// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import DB_Assets.User;
import DB_Assets.passwordManager;

public class DBController {

	private String dburl = "jdbc:mysql://localhost/easyfx?autoReconnect=true&useSSL=false";
	private String user = "root";
	private String pass = "";
	
	private DBController() {
	}
	
	static DBController INSTANCE;
	public static DBController getInstance() {
		if(INSTANCE==null)INSTANCE = new DBController();
		return INSTANCE;
	}
	
	private passwordManager PM = passwordManager.getinstance();
	
	public Connection setupConnection() {
		
		try {
			return DriverManager.getConnection(dburl, user, pass);
		} catch (SQLException e) {
			System.out.println("Error 2A: No DB found");
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet select(Connection connection, String select, String from, String where) {
		
		ResultSet rSet;
		
		try {
			Statement statement = connection.createStatement();
			
			if(where != null)
			rSet = statement.executeQuery("SELECT " + select + " FROM " + from + " WHERE " + where);
			else
			rSet = statement.executeQuery("SELECT " + select + " FROM " + from);
			
			//connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error 2B: DB disconnected[1]");
			e.printStackTrace();
			return null;
		}
		return rSet;
	}
	
	public boolean checkLogin(ResultSet rSet, String user, String pass) {
		
		try {
			while(rSet.next()) {
				
				if(rSet.getString("name").equals(user) && rSet.getString("pass").equals(PM.encrypt(pass, user, user.length()))) {
					
					User.getInstance(user, rSet.getString("email"), 
							rSet.getString("prefPath"),
							PM.encrypt(pass, user, user.length()), rSet.getBoolean("newsLetter"), rSet.getBoolean("remindMe"));
					
					return true;
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Error 2B: DB disconnected[2]");
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
	public void addUser(Connection connection, String name, String email, String path, String pass, boolean news, boolean remind) {

		try {
		PreparedStatement preparedStmt = connection.prepareStatement(
				"INSERT INTO user "
				+ "(name, email, prefPath, newsLetter, remindMe, pass) "
				+ "VALUES (?, ?, ?, ?, ?, ?)");
			
		preparedStmt.setString (1, name);
		preparedStmt.setString (2, email);
		preparedStmt.setString (3, path);
		preparedStmt.setBoolean (4, news);
		preparedStmt.setBoolean (5, remind);
		preparedStmt.setString (6, pass);
		
		preparedStmt.execute();
		connection.close();

		} catch (SQLException e) {
			System.out.println("Error 2B: DB disconnected[3]");
			e.printStackTrace();
		}
	}
	
	public void setLocalUser(Connection connection, String name) {
		
		ResultSet rSet;
		
		try {
			Statement statement = connection.createStatement();
			
			rSet = statement.executeQuery("SELECT name, email, prefPath, newsLetter, remindMe, pass FROM user WHERE name = " + name);
			rSet.next();
			
			User.getInstance(rSet.getString("name"), rSet.getString("email"), rSet.getString("prefPath"), rSet.getString("pass"), rSet.getBoolean("newsLetter"), rSet.getBoolean("remindMe"));
		
			
		} catch (SQLException e) {
			System.out.println("Error 2B: DB disconnected[1]");
			e.printStackTrace();
			return;
		}
		
	}
	
}
