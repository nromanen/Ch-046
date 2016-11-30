package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBConnector;

import java.sql.*;

/**
 * Created by vyach on 28.11.2016.
 */
public class DBManager {

	static int a = 5;
	static int b;
	static {
		b = 5;
	}
	static int c;

	private static Connection connection;

	public static void createTablesInDataBase() throws SQLException {
		connection = DBConnector.getConnection();
		createGroupTable();
		createSubjectTypeTable();
		createSubjectTable();
		createGroupSubjectManyToManyTable();
		connection.close();
	}

	private static void createGroupTable() throws SQLException {
		String request = "CREATE TABLE groups (" +
				"id BIGSERIAL PRIMARY KEY," +
				"name VARCHAR(10) UNIQUE NOT NULL," +
				"count INT NOT NULL" +
				")";
		Statement statement = connection.createStatement();
		statement.execute(request);
		statement.close();
	}


	private static void createSubjectTypeTable() throws SQLException {
		String request = "CREATE TABLE subject_types (" +
				"id BIGSERIAL PRIMARY KEY," +
				"name VARCHAR(25) UNIQUE NOT NULL," +
				"amount INT" +
				")";
		Statement statement = connection.createStatement();
		statement.execute(request);
		statement.close();
	}

	private static void createSubjectTable() throws SQLException {
		String request = "CREATE TABLE subjects (" +
				"id BIGSERIAL PRIMARY KEY," +
				"name VARCHAR(25) NOT NULL," +
				"type_id BIGINT NOT NULL REFERENCES subject_types(id)," +
				"course INT" +
				")";
		Statement statement = connection.createStatement();
		statement.execute(request);
		statement.close();
	}


	private static void createGroupSubjectManyToManyTable() throws SQLException {
		String request = "CREATE TABLE group_subject_map (" +
				"group_id BIGINT REFERENCES groups(id) ON DELETE CASCADE," +
				"subject_id BIGINT REFERENCES Subjects(id) ON DELETE CASCADE " +
				")";
		Statement statement = connection.createStatement();
		statement.execute(request);
		statement.close();
	}

	public static void dropAllTables() throws SQLException {
		connection = DBConnector.getConnection();
		String request = "SELECT table_name FROM information_schema.tables WHERE table_schema='public'";
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = statement.executeQuery(request);
		while (rs.next()) {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			request = String.format("DROP TABLE %s CASCADE", rs.getString(1));
			statement.execute(request);
		}
		statement.close();
		connection.close();
	}
}
