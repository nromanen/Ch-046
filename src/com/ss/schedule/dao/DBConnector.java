package com.ss.schedule.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {
    private static final String DBURL = "jdbc:postgresql://127.0.0.1:5432/schedule";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
