// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Controller;

import java.sql.ResultSet;
import java.sql.Statement;

import DB_Assets.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

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
			
			
		} catch (SQLException e) {
			System.out.println("Error 2B: DB disconnected");
			e.printStackTrace();
			return null;
		}
		return rSet;
	}
	
	public boolean checkLogin(ResultSet rSet, String user, String pass) {
		
		try {
			while(rSet.next()) {
				
				if(rSet.getString("name").equals(user) && rSet.getString("pass").equals(pass)) {
					
					User.getInstance(user, rSet.getString("email"), 
							rSet.getString("prefPath"), pass, rSet.getBoolean("newsLetter"), rSet.getBoolean("remindMe"));
					
					return true;
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Error 2B: DB disconnected");
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
	public void addUser(String name, String email, String path, String pass, boolean news, boolean remind) {
		
	}
	
}
