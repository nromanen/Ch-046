package com.ss.schedule.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.jdbc.JdbcGroupDao;
import com.ss.schedule.dao.jdbc.JdbcSubjectDao;
import com.ss.schedule.dao.jdbc.JdbcSubjectTypeDao;
import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vyach on 28.11.2016.
 */
public class DBManager {

	private static String propertiesFilePath = "db_connection.properties";
	private static final String groupsJsonFilePath = "src/main/resources/entities/groups.json";
	private static final String subjectsJsonFilePath = "src/main/resources/entities/subjects.json";

	private static Connection connection;

	public static void createTablesInDataBase(String propertiesFilePath) throws SQLException {
		connection = DBConnector.getConnection(propertiesFilePath);
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

	public static void dropAllTables(String propertiesFilePath) throws SQLException {
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

	public static void fillDataBaseWithData(String propertiesFilePath) throws SQLException {
		List<Group> groups = fillGroupsTable();
		fillSubjectTypesTable();
		List<Subject> subjects = fillSubjectsTable();
		replaceSubjectsInGroups(groups, subjects);
		fillGroupSubjectMap(groups);
	}

	private static List<Group> fillGroupsTable() throws SQLException {
		InputOutputJson<List<Group>> readWriteGroup = new InputOutputJson<>(new TypeReference<List<Group>>() {
		});
		List<Group> groups = readWriteGroup.readFromFile(groupsJsonFilePath);
		List<Group> groupsFromDB = new ArrayList<>();
		JdbcGroupDao groupDao = new JdbcGroupDao(propertiesFilePath);
		for (Group group : groups) {
			groupsFromDB.add(groupDao.add(group));
		}
		return groupsFromDB;
	}

	private static void fillSubjectTypesTable() throws SQLException {
		JdbcSubjectTypeDao subjectTypes = new JdbcSubjectTypeDao(propertiesFilePath);
		subjectTypes.add(SubjectType.LAB);
		subjectTypes.add(SubjectType.LECTURE);
		subjectTypes.add(SubjectType.PRACTICE);
		subjectTypes.add(SubjectType.SEMINAR);
	}

	private static List<Subject> fillSubjectsTable() throws SQLException {
		InputOutputJson<List<Subject>> readWriteSubject = new InputOutputJson<>(new TypeReference<List<Subject>>() {
		});
		List<Subject> subjects = readWriteSubject.readFromFile(subjectsJsonFilePath);
		List<Subject> subjectsFromDB = new ArrayList<>();
		JdbcSubjectDao subjectDao = new JdbcSubjectDao(propertiesFilePath);
		for (Subject subject : subjects) {
			subjectsFromDB.add(subjectDao.add(subject));
		}

		return subjectsFromDB;
	}

	private static void fillGroupSubjectMap(List<Group> groups) throws SQLException {
		JdbcGroupDao groupDao = new JdbcGroupDao(propertiesFilePath);
		for (Group group : groups) {
			groupDao.update(group);
		}
	}

	private static void replaceSubjectsInGroups(List<Group> groups, List<Subject> subjects) {
		for (Group group : groups) {
			List<Subject> subjectsWithId = new ArrayList<>();
			for (Subject groupSubject : group.getSubjects()) {
				for (Subject subject : subjects) {
					if (groupSubject.equals(subject)) {
						subjectsWithId.add(subject);
						break;
					}
				}
			}
			group.setSubjects(subjectsWithId);
		}
	}

	public static void main(String[] args) throws SQLException {
		DBManager.createTablesInDataBase(propertiesFilePath);
		DBManager.fillDataBaseWithData(propertiesFilePath);
	}
}
