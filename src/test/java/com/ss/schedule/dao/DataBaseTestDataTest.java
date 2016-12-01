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
 * Created by vyach on 01.12.2016.
 */
public class DataBaseTestDataTest {

	private Connection connection;
	private final String propertiesFilePath = "test_db_connection.properties";

	@BeforeMethod
	public void setUp() throws SQLException {
		DBManager.dropAllTables(propertiesFilePath);
		DBManager.createTablesInDataBase(propertiesFilePath);
		connection = DBConnector.getConnection(propertiesFilePath);
	}

	@AfterMethod
	public void tearDown() throws SQLException {
		DBManager.dropAllTables(propertiesFilePath);
		connection.close();
	}

	@Test
	public void testFillDataBaseWithTestData() throws SQLException {
		DataBaseTestData.fillDataBaseWithTestData(propertiesFilePath);
		assertEquals(countTableRows("groups"), 12);
		assertEquals(countTableRows("subjects"), 20);
		assertEquals(countTableRows("subject_types"), 4);
		assertEquals(countTableRows("group_subject_map"), 32);
	}

	private int countTableRows(String tableName) throws SQLException {
		String request = "SELECT COUNT(*) FROM " + tableName;
		connection = DBConnector.getConnection(propertiesFilePath);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(request);
		rs.next();
		return rs.getInt(1);
	}

}