package com.ss.schedule.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vyach on 29.11.2016.
 */
public class TestDBConnector {

	private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/schedule_test";
	private static final String USER_NAME = "postgres";
	private static final String PASSWORD = "111";

	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			loadDriver();
			connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		}
		return connection;
	}

	private static void loadDriver() {
		try {
			Class.forName(POSTGRESQL_DRIVER);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
