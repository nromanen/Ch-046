package com.ss.schedule.service.dbutil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by vyach on 29.11.2016.
 */
public class DBConnector {

	private static String POSTGRESQL_DRIVER;
	private static String DB_URL;
	private static String USER_NAME;
	private static String PASSWORD;

	private static Connection connection;

	public static Connection getConnection(String propertiesFilePath) throws SQLException {
		setProperties(propertiesFilePath);
		if (connection == null || connection.isClosed()) {
			loadDriver();
			connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		}
		return connection;
	}

	private static void setProperties(String propertiesFilePath) {
		Properties properties = new Properties();
		InputStream input = null;

		try {
			input = DBConnector.class.getClassLoader().getResourceAsStream(propertiesFilePath);
			properties.load(input);

			POSTGRESQL_DRIVER = properties.getProperty("db_driver");
			DB_URL = properties.getProperty("db_url");
			USER_NAME = properties.getProperty("user");
			PASSWORD = properties.getProperty("password");
		} catch (IOException ex) {
			//todo
			ex.printStackTrace();
		}
	}

	private static void loadDriver() {
		try {
			Class.forName(POSTGRESQL_DRIVER);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
