package com.ss.schedule.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.jdbc.JdbcGroupDao;
import com.ss.schedule.dao.jdbc.JdbcSubjectDao;
import com.ss.schedule.dao.jdbc.JdbcSubjectTypeDao;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vyach on 01.12.2016.
 */
public class DataBaseTestData {

	private static String propertiesFilePath;
	private static final String groupsJsonFilePath = "src/test/resources/testfiles/groups.json";
	private static final String subjectsJsonFilePath = "src/test/resources/testfiles/subjects.json";

	public static void fillDataBaseWithTestData(String propertiesFilePath) throws SQLException {
		DataBaseTestData.propertiesFilePath = propertiesFilePath;
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
}
