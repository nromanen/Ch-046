package com.ss.schedule;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

import com.ss.dao.DBConnector;
import com.ss.dao.TeacherDao;
import com.ss.teacher.Teacher;

public class TeacherDaoTest {
	List<Teacher> teacherList;
	TeacherDao td = new TeacherDao();
	Teacher teacher0 = new Teacher("Ivan", "Ivanov", 100);
	Teacher teacher1 = new Teacher("Ivan", "Ivanov",101);
	Teacher teacher2 = new Teacher("Ivan", "Ivanova",102);
	Teacher teacher3 = new Teacher("Ivan", "Ivanovo",103);

	@BeforeTest
	public void setUp() {
		try {
			PreparedStatement ps = DBConnector.getConnection().prepareStatement("INSERT INTO teachers (firstname,lastname) VALUES (Ivan, Ivanov)");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		td.add(teacher1);
		td.add(teacher2);
		td.add(teacher3);

	}

	@Test
	public void getAllTest() {
		teacherList = td.getAll();
		Assert.assertEquals(teacherList.size(), 9);
	}

	@Test
	public void addTest() {
		td.add(teacher0);
		Assert.assertEquals(teacherList.size(), 10);
	}
	
	@Test
	public void getByIdTest() {		
		Assert.assertEquals(td.getById(100), teacher0);
	}
	
	@Test
	public void updateTest() {
		td.update(100, "Ivan", "Lewandowski");
		Assert.assertEquals(td.getById(100).getLastName(), "Lewandowski");
	}

	@Test
	public void deleteTest() {
		td.delete(100);
		Assert.assertEquals(teacherList.size(), 9);
	}
	

	
	@AfterMethod
	public void afterTest() {
		td.delete(teacher1.getId());
		td.delete(teacher2.getId());
		td.delete(teacher3.getId());
	}

}
