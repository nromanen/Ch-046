package com.ss.schedule.dao.jdbc;

import com.ss.schedule.dao.AbstractDao;
import com.ss.schedule.dao.DBManager;
import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.Group;
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
public class JdbcGroupDaoTest {

	private Connection connection;
	private AbstractDao<Group> groupDao;

	@BeforeMethod
	public void setUp() throws Exception {
		DBManager.createTablesInDataBase();
		connection = DBConnector.getConnection();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		DBManager.dropAllTables();
		connection.close();
	}

	@Test
	public void testAddGroupIntoDB() throws SQLException {
		assertEquals(countTableRows(), 0);

		Group group1 = new Group("11", 22, new ArrayList<>());
		Group group2 = new Group("13", 7, new ArrayList<>());
		groupDao = new JdbcGroupDao();
		Group addedGroup1 = groupDao.add(group1);
		Group addedGroup2 = groupDao.add(group2);

		assertTrue(group1.equals(addedGroup1));
		assertTrue(group2.equals(addedGroup2));
		assertEquals(group1.getId(), 1);
		assertEquals(group2.getId(), 2);
		assertEquals(countTableRows(), 2);
	}

	private int countTableRows() throws SQLException {
		String request = "SELECT COUNT(*) FROM groups";
		connection = DBConnector.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(request);
		rs.next();
		return rs.getInt(1);
	}

	@Test
	public void testUpdateGroup() throws SQLException {
		Group group1 = new Group("11", 22, new ArrayList<>());
		Group group2 = new Group("13", 7, new ArrayList<>());
		groupDao = new JdbcGroupDao();
		Group addedGroup1 = groupDao.add(group1);
		Group addedGroup2 = groupDao.add(group2);

		addedGroup1.setName("18");
		addedGroup1.setCount(15);

		addedGroup2.setName("21");
		addedGroup2.setCount(13);

		Group updatedGroup1 = groupDao.update(addedGroup1);
		Group updatedGroup2 = groupDao.update(addedGroup2);

		assertTrue(addedGroup1.equals(updatedGroup1));
		assertTrue(addedGroup2.equals(updatedGroup2));
		assertEquals(countTableRows(), 2);
	}

	@Test
	public void testDeleteGroup() throws SQLException {
		Group group1 = new Group("11", 22, new ArrayList<>());
		Group group2 = new Group("13", 7, new ArrayList<>());
		groupDao = new JdbcGroupDao();
		Group addedGroup1 = groupDao.add(group1);
		groupDao.add(group2);

		assertTrue(groupDao.delete(addedGroup1.getId()));
		assertEquals(countTableRows(), 1);
	}

	@Test
	public void testGetGroupById() throws SQLException {
		Group group1 = new Group("11", 22, new ArrayList<>());
		Group group2 = new Group("13", 7, new ArrayList<>());
		groupDao = new JdbcGroupDao();
		Group addedGroup1 = groupDao.add(group1);
		Group addedGroup2 = groupDao.add(group2);

		group1 = groupDao.getById(addedGroup1.getId());
		group2 = groupDao.getById(addedGroup2.getId());

		assertTrue(group1.equals(addedGroup1));
		assertTrue(group2.equals(addedGroup2));

		assertEquals(group1.getId(), addedGroup1.getId());
		assertEquals(group2.getId(), addedGroup2.getId());
	}

	@Test
	public void testGetAllGroups() throws SQLException {
		Group group1 = new Group("11", 22, new ArrayList<>());
		Group group2 = new Group("13", 7, new ArrayList<>());
		groupDao = new JdbcGroupDao();
		List<Group> groups = new ArrayList<>();
		groups.add(groupDao.add(group1));
		groups.add(groupDao.add(group2));

		List<Group> groupsFromDB = groupDao.getAll();

		assertEquals(groupsFromDB.size(), 2);
		assertTrue(groups.equals(groupsFromDB));
	}
}