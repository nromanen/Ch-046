package com.ss.schedule.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by rmochetc on 27.11.16.
 */
public class DBConnector {
    private static final String DBURL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String USER = "admin";
    private static final String PASSWORD = "root";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
                System.out.println("OK");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
