package com.newsagent.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Defaults for running in VS Code: change these to your own MySQL username and password.
    // When running the JAR they can be given on the command line instead, e.g.
    // java -Ddb.user=root -Ddb.password=secret -jar NewsagentSystem.jar
    private static final String URL =
    System.getProperty("db.url", "jdbc:mysql://127.0.0.1:3306/newsagentdb");
    private static final String USERNAME = System.getProperty("db.user", "root");
    private static final String PASSWORD = System.getProperty("db.password", "");

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USERNAME,
                PASSWORD);
    }
}
