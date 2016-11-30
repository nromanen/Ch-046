package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBConnector;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.testng.Assert.assertEquals;

/**
 * Created by vyach on 29.11.2016.
 */
public class DBManagerTest {

	@BeforeMethod
	public void setUp() throws SQLException {
		DBManager.dropAllTables();
	}

	@AfterMethod
	public void tearDown() throws SQLException {
		DBManager.dropAllTables();
	}

	@Test
	public void testCreateTablesInDataBase() throws Exception {
		assertEquals(countTables(), 0);

		DBManager.createTablesInDataBase();

		assertEquals(countTables(), 4);
	}

	private int countTables() throws SQLException {
		String request = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public'";
		Connection connection = DBConnector.getConnection();
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = statement.executeQuery(request);
		rs.next();
		return rs.getInt(1);
	}

	@Test
	public void testDropAllTables() throws Exception {
		assertEquals(countTables(), 0);

		DBManager.createTablesInDataBase();
		assertEquals(countTables(), 4);

		DBManager.dropAllTables();
		assertEquals(countTables(), 0);
	}

}