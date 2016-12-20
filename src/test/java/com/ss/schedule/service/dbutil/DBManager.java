package com.ss.schedule.service.dbutil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;

import java.sql.*;
import java.util.List;

/**
 * Created by vyach on 28.11.2016.
 */
public class DBManager {

	private static String propertiesFilePath = "test_db_connection.properties";
	private static final String groupsJsonFilePath = "src/test/resources/testfiles/groups.json";
	private static final String subjectsJsonFilePath = "src/test/resources/testfiles/subjects.json";

	private static Connection connection;

	public static void createTablesInDataBase() throws SQLException {
		connection = DBConnector.getConnection(propertiesFilePath);
		createGroupTable();
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

	private static void createSubjectTable() throws SQLException {
		String request = "CREATE TABLE subjects (" +
				"id BIGSERIAL PRIMARY KEY," +
				"name VARCHAR(25) NOT NULL," +
				"type VARCHAR(50) NOT NULL," +
				"course INT" +
				")";
		Statement statement = connection.createStatement();
		statement.execute(request);
		statement.close();
	}


	private static void createGroupSubjectManyToManyTable() throws SQLException {
		String request = "CREATE TABLE group_subject (" +
				"group_id BIGINT REFERENCES groups(id) ON DELETE CASCADE," +
				"subject_id BIGINT REFERENCES subjects(id)" +
				")";
		Statement statement = connection.createStatement();
		statement.execute(request);
		statement.close();
	}

	public static void dropAllTables() throws SQLException {
		connection = DBConnector.getConnection(propertiesFilePath);
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

	public static void fillDataBaseWithData() throws SQLException {
		connection = DBConnector.getConnection(propertiesFilePath);
		fillSubjectsTable();
		fillGroupsTable();
		connection.close();
	}

	private static void fillSubjectsTable() throws SQLException {
		InputOutputJson<List<Subject>> readWriteSubject = new InputOutputJson<>(new TypeReference<List<Subject>>() {
		});
		List<Subject> subjects = readWriteSubject.readFromFile(subjectsJsonFilePath);
		PreparedStatement preparedStatement = null;

		String request = "INSERT INTO subjects VALUES (?, ?, ?, ?)";
		for (Subject subject : subjects) {
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setLong(1, subject.getId());
			preparedStatement.setString(2, subject.getName());
			preparedStatement.setString(3, subject.getType().toString());
			preparedStatement.setInt(4, subject.getCourse());
			preparedStatement.executeUpdate();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
	}

	private static void fillGroupsTable() throws SQLException {
		InputOutputJson<List<Group>> readWriteGroup = new InputOutputJson<>(new TypeReference<List<Group>>() {
		});
		List<Group> groups = readWriteGroup.readFromFile(groupsJsonFilePath);
		PreparedStatement preparedStatement = null;

		String request = "INSERT INTO groups VALUES (?, ?, ?)";
		for (Group group : groups) {
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setLong(1, group.getId());
			preparedStatement.setString(2, group.getName());
			preparedStatement.setInt(3, group.getCount());
			preparedStatement.executeUpdate();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		fillGroupSubjectMap(groups);
	}

	private static void fillGroupSubjectMap(List<Group> groups) throws SQLException {
		PreparedStatement preparedStatement = null;
		String request = "INSERT INTO group_subject VALUES (?, ?)";

		for (Group group : groups) {
			for (Subject subject : group.getSubjects()) {
				preparedStatement = connection.prepareStatement(request);
				preparedStatement.setLong(1, group.getId());
				preparedStatement.setLong(2, subject.getId());
				preparedStatement.executeUpdate();
			}
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
	}

	public static void main(String[] args) throws SQLException {
		DBManager.dropAllTables();
		DBManager.createTablesInDataBase();
		DBManager.fillDataBaseWithData();
	}
}
