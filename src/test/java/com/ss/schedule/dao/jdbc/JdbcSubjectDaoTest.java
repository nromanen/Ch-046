package com.ss.schedule.dao.jdbc;

import com.ss.schedule.dao.AbstractDao;
import com.ss.schedule.dao.DBManager;
import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by vyach on 30.11.2016.
 */
public class JdbcSubjectDaoTest {

	private Connection connection;
	private AbstractDao<Subject> subjectDao;

	@BeforeMethod
	public void setUp() throws Exception {
		DBManager.dropAllTables();
		DBManager.createTablesInDataBase();
		fillSubjectTypesTable();
		connection = DBConnector.getConnection();
	}

	private void fillSubjectTypesTable() throws SQLException {
		AbstractDao<SubjectType> subjectTypeDao = new JdbcSubjectTypeDao();
		subjectTypeDao.add(SubjectType.LAB);
		subjectTypeDao.add(SubjectType.PRACTICE);
		subjectTypeDao.add(SubjectType.LECTURE);
		subjectTypeDao.add(SubjectType.SEMINAR);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		DBManager.dropAllTables();
		connection.close();
	}

	@Test
	public void testAddSubjectIntoDB() throws SQLException {
		assertEquals(countTableRows(), 0);

		Subject subject1 = new Subject("C++", SubjectType.LECTURE, 2);
		Subject subject2 = new Subject(".Net", SubjectType.LAB, 3);
		subjectDao = new JdbcSubjectDao();
		Subject addedSubject1 = subjectDao.add(subject1);
		Subject addedSubject2 = subjectDao.add(subject2);

		assertTrue(subject1.equals(addedSubject1));
		assertTrue(subject2.equals(addedSubject2));
		assertEquals(subject1.getId(), 1);
		assertEquals(subject2.getId(), 2);
		assertEquals(countTableRows(), 2);
	}

	private int countTableRows() throws SQLException {
		String request = "SELECT COUNT(*) FROM subjects";
		connection = DBConnector.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(request);
		rs.next();
		return rs.getInt(1);
	}

	@Test
	public void testUpdateSubject() throws SQLException {
		Subject subject1 = new Subject("C++", SubjectType.LECTURE, 2);
		Subject subject2 = new Subject(".Net", SubjectType.LAB, 3);
		subjectDao = new JdbcSubjectDao();
		Subject addedSubject1 = subjectDao.add(subject1);
		Subject addedSubject2 = subjectDao.add(subject2);

		addedSubject1.setName("Algebra");
		addedSubject1.setType(SubjectType.SEMINAR);
		addedSubject1.setCourse(1);

		addedSubject2.setName("Python");
		addedSubject2.setType(SubjectType.PRACTICE);
		addedSubject2.setCourse(5);

		Subject updatedSubject1 = subjectDao.update(addedSubject1);
		Subject updatedSubject2 = subjectDao.update(addedSubject2);

		assertTrue(addedSubject1.equals(updatedSubject1));
		assertTrue(addedSubject2.equals(updatedSubject2));
		assertEquals(countTableRows(), 2);
	}

	@Test
	public void testDeleteSubject() throws SQLException {
		Subject subject1 = new Subject("C++", SubjectType.LECTURE, 2);
		Subject subject2 = new Subject(".Net", SubjectType.LAB, 3);
		subjectDao = new JdbcSubjectDao();
		subjectDao.add(subject1);
		Subject addedSubject2 = subjectDao.add(subject2);

		assertTrue(subjectDao.delete(addedSubject2.getId()));
		assertEquals(countTableRows(), 1);
	}

	@Test
	public void testGetSubjectById() throws SQLException {
		Subject subject1 = new Subject("C++", SubjectType.LECTURE, 2);
		Subject subject2 = new Subject(".Net", SubjectType.LAB, 3);
		subjectDao = new JdbcSubjectDao();
		Subject addedSubject1 = subjectDao.add(subject1);
		Subject addedSubject2 = subjectDao.add(subject2);

		subject1 = subjectDao.getById(addedSubject1.getId());
		subject2 = subjectDao.getById(addedSubject2.getId());

		assertTrue(subject1.equals(addedSubject1));
		assertTrue(subject2.equals(addedSubject2));

		assertEquals(subject1.getId(), addedSubject1.getId());
		assertEquals(subject2.getId(), addedSubject2.getId());
	}

	@Test
	public void testGetAll() throws SQLException {
		Subject subject1 = new Subject("C++", SubjectType.LECTURE, 2);
		Subject subject2 = new Subject(".Net", SubjectType.LAB, 3);
		subjectDao = new JdbcSubjectDao();
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subjectDao.add(subject1));
		subjects.add(subjectDao.add(subject2));

		List<Subject> subjectsFromDB = subjectDao.getAll();

		assertEquals(subjectsFromDB.size(), 2);
		assertTrue(subjects.equals(subjectsFromDB));
	}

}