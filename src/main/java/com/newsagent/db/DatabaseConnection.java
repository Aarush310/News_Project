package com.newsagent.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
    "jdbc:mysql://127.0.0.1:3306/newsagentdb";

    // Change these to your own MySQL username and password
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USERNAME,
                PASSWORD);
    }
}
