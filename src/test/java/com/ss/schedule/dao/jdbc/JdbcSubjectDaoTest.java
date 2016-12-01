package com.ss.schedule.dao.jdbc;

import com.ss.schedule.dao.DBManager;
import com.ss.schedule.dao.DataBaseTestData;
import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by vyach on 30.11.2016.
 */
public class JdbcSubjectDaoTest {

	private Connection connection;
	private JdbcSubjectDao subjectDao;
	private final String propertiesFilePath = "test_db_connection.properties";

	@BeforeClass
	public void setUpClass() throws SQLException {
		subjectDao = new JdbcSubjectDao(propertiesFilePath);
	}

	@BeforeMethod
	public void setUp() throws SQLException {
		DBManager.dropAllTables(propertiesFilePath);
		DBManager.createTablesInDataBase(propertiesFilePath);
		DataBaseTestData.fillDataBaseWithTestData(propertiesFilePath);
		connection = DBConnector.getConnection(propertiesFilePath);
	}

	@AfterMethod
	public void tearDown() throws SQLException {
		DBManager.dropAllTables(propertiesFilePath);
		connection.close();
	}

	@Test
	public void testAddSubjectIntoDB() throws SQLException {
		assertEquals(countTableRows(), 20);

		Subject subject1 = new Subject("C+++", SubjectType.LECTURE, 2);
		Subject subject2 = new Subject(".Nett", SubjectType.LAB, 3);
		Subject addedSubject1 = subjectDao.add(subject1);
		Subject addedSubject2 = subjectDao.add(subject2);

		assertTrue(subject1.equals(addedSubject1));
		assertTrue(subject2.equals(addedSubject2));
		assertEquals(countTableRows(), 22);
	}

	private int countTableRows() throws SQLException {
		String request = "SELECT COUNT(*) FROM subjects";
		connection = DBConnector.getConnection(propertiesFilePath);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(request);
		rs.next();
		return rs.getInt(1);
	}

	@Test
	public void testUpdateSubject() throws SQLException {
		Subject subject1 = new Subject("C+++", SubjectType.LECTURE, 2);
		Subject subject2 = new Subject(".Nett", SubjectType.LAB, 3);
		Subject addedSubject1 = subjectDao.add(subject1);
		Subject addedSubject2 = subjectDao.add(subject2);

		addedSubject1.setName("Algebraa");
		addedSubject1.setType(SubjectType.SEMINAR);
		addedSubject1.setCourse(1);

		addedSubject2.setName("Pythonn");
		addedSubject2.setType(SubjectType.PRACTICE);
		addedSubject2.setCourse(5);

		Subject updatedSubject1 = subjectDao.update(addedSubject1);
		Subject updatedSubject2 = subjectDao.update(addedSubject2);

		assertTrue(addedSubject1.equals(updatedSubject1));
		assertTrue(addedSubject2.equals(updatedSubject2));
		assertEquals(countTableRows(), 22);
	}

	@Test
	public void testDeleteSubject() throws SQLException {
		Subject subject = new Subject(".Nett", SubjectType.LAB, 3);
		Subject addedSubject = subjectDao.add(subject);

		assertTrue(subjectDao.delete(addedSubject.getId()));
		assertEquals(countTableRows(), 20);
	}

	@Test
	public void testGetSubjectById() throws SQLException {
		Subject subject1 = new Subject("C+++", SubjectType.LECTURE, 2);
		Subject subject2 = new Subject(".Nett", SubjectType.LAB, 3);
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
	public void testGetAllSubjects() throws SQLException {
		List<Subject> subjectsFromDB = subjectDao.getAll();

		assertEquals(subjectsFromDB.size(), 20);
	}

	@Test
	public void testGetSubjectsByGroupId() throws SQLException {
		List<Subject> subjects = subjectDao.getSubjectsByGroupId(1);
		Subject algebraLec = new Subject("Algebra", SubjectType.LECTURE, 1);
		Subject algebraPr = new Subject("Algebra", SubjectType.PRACTICE, 1);
		Subject philosophyLec = new Subject("Philosophy", SubjectType.LECTURE, 0);
		Subject philosophySem = new Subject("Philosophy", SubjectType.SEMINAR, 0);

		assertEquals(subjects.size(), 4);
		assertTrue(subjects.contains(algebraLec));
		assertTrue(subjects.contains(algebraPr));
		assertTrue(subjects.contains(philosophyLec));
		assertTrue(subjects.contains(philosophySem));
	}
}