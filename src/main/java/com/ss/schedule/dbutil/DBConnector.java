package com.ss.schedule.dbutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by rmochetc on 27.11.16.
 */
public class DBConnector {


    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Properties prop=new Properties();

                prop.load(DBConnector.class.getResourceAsStream("dbConnection.properties"));

                String drivers = prop.getProperty("jdbc.drivers");
                String connectionURL = prop.getProperty("jdbc.url");
                String username = prop.getProperty("jdbc.username");
                String password = prop.getProperty("jdbc.password");
                Class.forName(drivers);
                connection=DriverManager.getConnection(connectionURL,username,password);
                System.out.println("Connection Successful");



            } catch (ClassNotFoundException | SQLException | IOException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}