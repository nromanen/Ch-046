package com.ss.schedule.institute;

import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import com.ss.schedule.model.Teacher;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TeacherManagerTest {
	Teacher teacher0;
	TeacherManager manage;
	Subject algebra;
	Subject algebra2;
	@BeforeTest
	public void initializeTeacherSubject() {
		teacher0 = new Teacher();
		manage = new TeacherManager(teacher0);
		algebra2 = new Subject("Algebra", SubjectType.LAB, 2);
		algebra = new Subject("Algebra", SubjectType.LAB, 0);
		manage.addSubject(new Subject("Algebra", SubjectType.LECTURE));
		manage.addSubject(new Subject("Geometry", SubjectType.LECTURE));
		manage.addSubject(new Subject("Programming", SubjectType.LECTURE, 2));
		manage.addSubject(new Subject("Programming", SubjectType.PRACTICE, 2));
		manage.addSubject(new Subject("Geometry", SubjectType.LECTURE, 3));
		manage.addSubject(new Subject("Geometry", SubjectType.PRACTICE, 3));
		manage.addSubject(new Subject("Geometry", SubjectType.LECTURE, 4));
	}

	@Test
	public void addSubjectTest() {

		manage.addSubject(algebra);
		Assert.assertTrue(teacher0.getList().contains(algebra));
		manage.addSubject(new Subject("Programming", SubjectType.LAB, 3));
		Assert.assertEquals(teacher0.getList().size(), 10);
		manage.addSubject(new Subject("Programming", SubjectType.LAB, 5));
		Assert.assertEquals(teacher0.getList().size(), 11);

	}

	@Test
	public void addDublicatedSubjectTest() {
		manage.addSubject(new Subject("Programming", SubjectType.SEMINAR, 2));
		Assert.assertEquals(teacher0.getList().size(), 8);
		manage.addSubject(new Subject("Programming", SubjectType.SEMINAR, 2));
		Assert.assertEquals(teacher0.getList().size(), 8);
	}

	@Test
	public void removeSubjectTest() {
		int length = teacher0.getList().size();
		manage.removeSubject(algebra);
		Assert.assertTrue(!teacher0.getList().contains(algebra));
		manage.removeSubject(algebra2);
		 Assert.assertEquals(teacher0.getList().size(), length-1);
	}

	@Test
	public void getSubjectsByTypeTest() {
		ArrayList<Subject> expectedList = new ArrayList<>();
		expectedList.add(new Subject("Programming", SubjectType.PRACTICE, 2));
		expectedList.add(new Subject("Geometry", SubjectType.PRACTICE, 3));
		
		ArrayList<Subject> actualList = manage.getSubjectsByType(SubjectType.PRACTICE);
		Assert.assertEquals(actualList, expectedList);
	}

	@Test
	public void getOnlyLecturesTest() {
		Assert.assertEquals(manage.getOnlyLectures(teacher0).size(), 2);
		ArrayList<Subject> expectedList = new ArrayList<>();
		expectedList.add(new Subject("Geometry", SubjectType.LECTURE));
		expectedList.add(new Subject("Geometry", SubjectType.LECTURE, 4));
		Assert.assertEquals(manage.getOnlyLectures(teacher0), expectedList);
	}
}
