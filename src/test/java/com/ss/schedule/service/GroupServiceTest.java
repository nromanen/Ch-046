package com.ss.schedule.service;

import com.ss.schedule.model.Group;
import com.ss.schedule.service.dbutil.DBManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

/**
 * Created by vyach on 01.12.2016.
 */
public class GroupServiceTest {

	private final String hibernateConfigFilePath = "test_hibernate.cfg.xml";
	private final String propertiesFilePath = "test_db_connection.properties";

	private GroupService groupService;

	@BeforeMethod
	public void setUp() throws SQLException {
		DBManager.dropAllTables();
		DBManager.createTablesInDataBase();
		DBManager.fillDataBaseWithData();
		groupService = new GroupService(hibernateConfigFilePath);
	}

	@Test
	public void testAddGroup() throws SQLException {
		assertEquals(groupService.getAllGroups().size(), 12);

		Group group = new Group("29", 22, new ArrayList<>());

		groupService.addGroup(group);
		assertEquals(groupService.getAllGroups().size(), 13);
	}

	@Test
	public void testUpdateGroup() throws SQLException {
		assertEquals(groupService.getAllGroups().size(), 12);

		Group group = groupService.getGroupById(3L);
		assertEquals(group.getName(), "13");
		assertEquals(group.getCount(), 5);

		group.setName("19");
		group.setCount(23);
		groupService.updateGroup(group);

		Group updatedGroup = groupService.getGroupById(3L);
		assertEquals(updatedGroup.getName(), group.getName());
		assertEquals(updatedGroup.getCount(), group.getCount());
		assertEquals(groupService.getAllGroups().size(), 12);
	}

	@Test
	public void testDeleteGroup() throws SQLException {
		assertEquals(groupService.getAllGroups().size(), 12);

		Group group1 = groupService.getGroupById(1);
		Group group2 = groupService.getGroupById(2);

		groupService.deleteGroup(group1);
		groupService.deleteGroup(group2);

		assertEquals(groupService.getAllGroups().size(), 10);
	}

	@Test
	public void testGetGroupById() throws SQLException {
		Group group11 = groupService.getGroupById(1);
		Group group12 = groupService.getGroupById(2);

		assertEquals(group11.getName(), "11");
		assertEquals(group12.getName(), "12");
		assertEquals(group11.getCount(), 20);
		assertEquals(group12.getCount(), 22);
	}

	@Test
	public void getAllGroups() throws SQLException {
		assertEquals(groupService.getAllGroups().size(), 12);
	}

	@Test
	public void getGroupsByCourse() throws SQLException {
		assertEquals(groupService.getGroupsByCourse(1).size(), 4);
		assertEquals(groupService.getGroupsByCourse(2).size(), 4);
		assertEquals(groupService.getGroupsByCourse(3).size(), 4);
	}

	@Test
	public void testGetGroupsBySubjectId() throws SQLException {
		assertEquals(groupService.getGroupsBySubjectId(7).size(), 4);
	}
}