// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {

	private String dburl = "jdbc:mysql://localhost:3306/easyfx";
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
			System.out.println("Error 2A: No DB connection");
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
