
package com.ss.schedule.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by rmochetc on 27.11.16.
 */
public class DBConnector {

    private static final String DBURL = "jdbc:postgresql://127.0.0.1:5432/institute";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1123581321";
    private static final String DRIVER = "org.postgresql.Driver";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {


                Class.forName(DRIVER);
                connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}