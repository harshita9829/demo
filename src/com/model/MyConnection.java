package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyConnection {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		String driver = rb.getString("driver");
		String url = rb.getString("url");
		String username = rb.getString("username");
		String password = rb.getString("password");
		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}
}
