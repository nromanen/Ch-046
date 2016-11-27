package com.ss.schedule.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by rmochetc on 27.11.16.
 */
public class DBConnector {
    private static Connection connection = null;

    private static final String url = "jdbc:postgresql://127.0.0.1:5432/schedule";
    private static final String user = "postgres";
    private static final String password = "1123581321";

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Success!");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
