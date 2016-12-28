package com.ss.schedule.service;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import com.ss.schedule.service.dbutil.DBManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by vyach on 12.12.2016.
 */
public class SubjectServiceImplTest {

	private final String hibernateConfigFilePath = "test_hibernate.cfg.xml";


	private SubjectServiceImpl subjectServiceImpl;
	private GroupServiceImpl groupServiceImpl;

	@BeforeMethod
	public void setUp() throws SQLException {
		DBManager.dropAllTables();
		DBManager.createTablesInDataBase();
		DBManager.fillDataBaseWithData();
		subjectServiceImpl = new SubjectServiceImpl();
		groupServiceImpl = new GroupServiceImpl();
	}

	@Test
	public void testAddSubject() {
		assertEquals(subjectServiceImpl.getAllSubjects().size(), 20);

		Subject subject1 = new Subject("RoR", SubjectType.LECTURE, 3);
		Subject subject2 = new Subject("RoR", SubjectType.LAB, 3);

		subjectServiceImpl.addSubject(subject1);
		subjectServiceImpl.addSubject(subject2);

		assertEquals(subjectServiceImpl.getAllSubjects().size(), 22);
	}

	@Test
	public void testUpdateSubject() {
		assertEquals(subjectServiceImpl.getAllSubjects().size(), 20);

		Subject subject = subjectServiceImpl.getSubjectById(1);
		subject.setName("RoR");
		subject.setType(SubjectType.SEMINAR);
		subjectServiceImpl.updateSubject(subject);

		assertEquals(subject, subjectServiceImpl.getSubjectById(1));
	}

	@Test(expectedExceptions = PersistenceException.class)
	public void testDeleteSubject() {
		assertEquals(subjectServiceImpl.getAllSubjects().size(), 20);

		Subject subject1 = subjectServiceImpl.getSubjectById(1);
		Subject subject2 = subjectServiceImpl.getSubjectById(2);

		subjectServiceImpl.deleteSubject(subject1);
		subjectServiceImpl.deleteSubject(subject2);

		assertEquals(subjectServiceImpl.getAllSubjects().size(), 18);
	}

	@Test
	public void testGetSubjectById() {
		Subject subject = subjectServiceImpl.getSubjectById(1);
		assertNotNull(subject);
		assertEquals(subject.getName(), "Algebra");
		assertEquals(subject.getType(), SubjectType.LECTURE);
		assertEquals(subject.getCourse(), 2);
	}

	@Test
	public void testGetAllSubjects() {
		assertEquals(subjectServiceImpl.getAllSubjects().size(), 20);
	}

	@Test
	public void testGetSubjectsByCourse() {
		assertEquals(subjectServiceImpl.getSubjectsByCourse(1).size(), 9);
		assertEquals(subjectServiceImpl.getSubjectsByCourse(2).size(), 7);
		assertEquals(subjectServiceImpl.getSubjectsByCourse(3).size(), 4);
	}

	@Test
	public void testGetSubject() {
		Subject subject = subjectServiceImpl.getSubject("Algebra", SubjectType.LECTURE, 2);
		assertNotNull(subject);
		assertEquals(subject.getName(), "Algebra");
		assertEquals(subject.getType(), SubjectType.LECTURE);
		assertEquals(subject.getCourse(), 2);
	}

	@Test
	public void testGetUnusedSubjects() {
		List<Subject> subjects = subjectServiceImpl.getUnusedSubjects();
		assertEquals(subjects.size(), 7);

		Group group = groupServiceImpl.getGroupById(1);
		group.addSubject(subjects.get(0));
		group.addSubject(subjects.get(1));
		groupServiceImpl.updateGroup(group);

		assertEquals(subjectServiceImpl.getUnusedSubjects().size(), 5);
	}

	@Test
	public void testGetSubjectsByGroupId() {
		assertEquals(subjectServiceImpl.getSubjectsByGroupId(1).size(), 4);
	}
}