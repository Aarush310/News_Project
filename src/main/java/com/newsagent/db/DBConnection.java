package com.newsagent.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Shared MySQL connection for all entities (Customer, Publication, ...).
 * Change PASSWORD to the root password you set when installing MySQL Server.
 */
public class DBConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/newsagent";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
