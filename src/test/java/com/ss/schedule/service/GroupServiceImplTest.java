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
public class GroupServiceImplTest {

	private final String hibernateConfigFilePath = "test_hibernate.cfg.xml";

	private GroupServiceImpl groupServiceImpl;

	@BeforeMethod
	public void setUp() throws SQLException {
		DBManager.dropAllTables();
		DBManager.createTablesInDataBase();
		DBManager.fillDataBaseWithData();
		groupServiceImpl = new GroupServiceImpl();
	}

	@Test
	public void testAddGroup() {
		assertEquals(groupServiceImpl.getAllGroups().size(), 12);

		Group group = new Group("29", 22, new ArrayList<>());

		groupServiceImpl.addGroup(group);
		assertEquals(groupServiceImpl.getAllGroups().size(), 13);
	}

	@Test
	public void testUpdateGroup() {
		assertEquals(groupServiceImpl.getAllGroups().size(), 12);

		Group group = groupServiceImpl.getGroupById(3L);
		assertEquals(group.getName(), "13");
		assertEquals(group.getCount(), 5);

		group.setName("19");
		group.setCount(23);
		groupServiceImpl.updateGroup(group);

		Group updatedGroup = groupServiceImpl.getGroupById(3L);
		assertEquals(updatedGroup.getName(), group.getName());
		assertEquals(updatedGroup.getCount(), group.getCount());
		assertEquals(groupServiceImpl.getAllGroups().size(), 12);
	}

	@Test
	public void testDeleteGroup() {
		assertEquals(groupServiceImpl.getAllGroups().size(), 12);

		Group group1 = groupServiceImpl.getGroupById(1);
		Group group2 = groupServiceImpl.getGroupById(2);

		groupServiceImpl.deleteGroup(group1);
		groupServiceImpl.deleteGroup(group2);

		assertEquals(groupServiceImpl.getAllGroups().size(), 10);
	}

	@Test
	public void testGetGroupById() {
		Group group11 = groupServiceImpl.getGroupById(1);
		Group group12 = groupServiceImpl.getGroupById(2);

		assertEquals(group11.getName(), "11");
		assertEquals(group12.getName(), "12");
		assertEquals(group11.getCount(), 20);
		assertEquals(group12.getCount(), 22);
	}

	@Test
	public void getAllGroups() {
		assertEquals(groupServiceImpl.getAllGroups().size(), 12);
	}

	@Test
	public void getGroupsByCourse() {
		assertEquals(groupServiceImpl.getGroupsByCourse(1).size(), 4);
		assertEquals(groupServiceImpl.getGroupsByCourse(2).size(), 4);
		assertEquals(groupServiceImpl.getGroupsByCourse(3).size(), 4);
	}

	@Test
	public void testGetGroupsBySubjectId() {
		assertEquals(groupServiceImpl.getGroupsBySubjectId(7).size(), 4);
	}
}