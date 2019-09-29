package com.vamsi.Utilities;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class DBConnectionUtil {

	//Properties Of db
	private static final String URL = "jdbc:mysql://localhost:3306/employeedirectory";
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private static final String USERNAME = "root";
	
	private static final String PASSWORD = "vamsi123";
	
	private static Connection connection = null;
	
	public static Connection openConnection() {
		if(connection != null) {
			return connection;
		}
	    try {
				Class.forName(DRIVER);
				connection = (Connection) DriverManager.getConnection(URL,USERNAME,PASSWORD);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	     return connection;
	}
	
}
