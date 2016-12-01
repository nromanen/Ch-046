package com.ss.schedule.dao.jdbc;

import com.ss.schedule.dao.DBManager;
import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.SubjectType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
public class JdbcSubjectTypeDaoTest {

	private Connection connection;
	private JdbcSubjectTypeDao subjectTypeDao;
	private final String propertiesFilePath = "test_db_connection.properties";

	@BeforeClass
	public void setUpClass() throws SQLException {
		subjectTypeDao = new JdbcSubjectTypeDao(propertiesFilePath);
	}

	@BeforeMethod
	public void setUp() throws SQLException {
		DBManager.createTablesInDataBase(propertiesFilePath);
		connection = DBConnector.getConnection(propertiesFilePath);
	}

	@AfterMethod
	public void tearDown() throws SQLException {
		DBManager.dropAllTables(propertiesFilePath);
		connection.close();
	}

	@Test
	public void testAddSubjectType() throws SQLException {
		assertEquals(countTableRows(), 0);

		assertTrue(SubjectType.LAB.equals(subjectTypeDao.add(SubjectType.LAB)));
		assertTrue(SubjectType.LECTURE.equals(subjectTypeDao.add(SubjectType.LECTURE)));

		assertEquals(countTableRows(), 2);
	}

	private int countTableRows() throws SQLException {
		String request = "SELECT COUNT(*) FROM subject_types";
		connection = DBConnector.getConnection(propertiesFilePath);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(request);
		rs.next();
		return rs.getInt(1);
	}

	@Test
	public void testUpdateSubjectType() throws SQLException {
		SubjectType type1 = subjectTypeDao.add(SubjectType.LAB);
		SubjectType type2 = subjectTypeDao.add(SubjectType.SEMINAR);

		type1.setMaxStudentAmount(38);
		type2.setMaxStudentAmount(45);

		SubjectType updatedType1 = subjectTypeDao.update(type1);
		SubjectType updatedType2 = subjectTypeDao.update(type2);

		assertTrue(type1.equals(updatedType1));
		assertTrue(type2.equals(updatedType2));
		assertEquals(countTableRows(), 2);
	}

	@Test
	public void testDeleteSubjectType() throws SQLException {
		SubjectType subjectType1 = SubjectType.LAB;
		SubjectType subjectType2 = SubjectType.PRACTICE;

		SubjectType addedSubjectType1 = subjectTypeDao.add(subjectType1);
		subjectTypeDao.add(subjectType2);

		assertTrue(subjectTypeDao.delete(addedSubjectType1.getId()));
		assertEquals(countTableRows(), 1);
	}

	@Test
	public void testGetSubjectTypeById() throws SQLException {
		SubjectType subjectType1 = SubjectType.LAB;
		SubjectType subjectType2 = SubjectType.LECTURE;
		SubjectType addedSubjectType1 = subjectTypeDao.add(subjectType1);
		SubjectType addedSubjectType2 = subjectTypeDao.add(subjectType2);

		subjectType1 = subjectTypeDao.getById(addedSubjectType1.getId());
		subjectType2 = subjectTypeDao.getById(addedSubjectType2.getId());

		assertTrue(subjectType1.equals(addedSubjectType1));
		assertTrue(subjectType2.equals(addedSubjectType2));

		assertEquals(subjectType1.getId(), addedSubjectType1.getId());
		assertEquals(subjectType2.getId(), addedSubjectType2.getId());
	}

	@Test
	public void testGetAllSubjectTypes() throws SQLException {
		SubjectType subjectType1 = SubjectType.LAB;
		SubjectType subjectType2 = SubjectType.LECTURE;
		List<SubjectType> subjectTypes = new ArrayList<>();
		subjectTypes.add(subjectTypeDao.add(subjectType1));
		subjectTypes.add(subjectTypeDao.add(subjectType2));

		List<SubjectType> subjectTypesFromDB = subjectTypeDao.getAll();

		assertEquals(subjectTypesFromDB.size(), 2);
		assertTrue(subjectTypes.equals(subjectTypesFromDB));
	}

}