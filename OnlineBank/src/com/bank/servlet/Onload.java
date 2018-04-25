package com.bank.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Onload {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		Connection con = null;
		try {
			String url = "jdbc:mysql://localhost:3306/bankservice";
			String user = "root";
			String password = "123456";
			con = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}
}
