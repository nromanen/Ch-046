package com.ss.schedule.service;

import com.ss.schedule.dao.DBManager;
import com.ss.schedule.dao.DataBaseTestData;
import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by vyach on 01.12.2016.
 */
public class GroupServiceTest {

	private GroupService groupService;
	private Connection connection;
	private final String propertiesFilePath = "test_db_connection.properties";

	@BeforeClass
	public void setUpClass() throws SQLException {
		groupService = new GroupService(propertiesFilePath);
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
	public void testUpdateGroup() throws SQLException {
		Group group = groupService.getGroupById(1);
		List<Subject> subjects = group.getSubjects();
		assertEquals(subjects.size(), 4);

		subjects.remove(0);
		subjects.remove(0);

		group.setSubjects(subjects);
		group = groupService.updateGroup(group);

		assertEquals(group.getSubjects().size(), 2);
		assertTrue(group.getSubjects().containsAll(subjects));
	}

	@Test
	public void testGetGroupsBySubjectId() throws SQLException {
		List<Group> groups = groupService.getGroupsBySubjectId(7);
		assertEquals(groups.size(), 4);
	}

}