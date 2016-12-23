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
public class SubjectServiceTest {

	private final String hibernateConfigFilePath = "test_hibernate.cfg.xml";


	private SubjectService subjectService;
	private GroupService groupService;

	@BeforeMethod
	public void setUp() throws SQLException {
		DBManager.dropAllTables();
		DBManager.createTablesInDataBase();
		DBManager.fillDataBaseWithData();
		subjectService = new SubjectService(hibernateConfigFilePath);
		groupService = new GroupService(hibernateConfigFilePath);
	}

	@Test
	public void testAddSubject() throws SQLException {
		assertEquals(subjectService.getAllSubjects().size(), 20);

		Subject subject1 = new Subject("RoR", SubjectType.LECTURE, 3);
		Subject subject2 = new Subject("RoR", SubjectType.LAB, 3);

		subjectService.addSubject(subject1);
		subjectService.addSubject(subject2);

		assertEquals(subjectService.getAllSubjects().size(), 22);
	}

	@Test
	public void testUpdateSubject() throws SQLException {
		assertEquals(subjectService.getAllSubjects().size(), 20);

		Subject subject = subjectService.getSubjectById(1);
		subject.setName("RoR");
		subject.setType(SubjectType.SEMINAR);
		subjectService.updateSubject(subject);

		assertEquals(subject, subjectService.getSubjectById(1));
	}

	@Test(expectedExceptions = PersistenceException.class)
	public void testDeleteSubject() throws SQLException {
		assertEquals(subjectService.getAllSubjects().size(), 20);

		Subject subject1 = subjectService.getSubjectById(1);
		Subject subject2 = subjectService.getSubjectById(2);

		subjectService.deleteSubject(subject1);
		subjectService.deleteSubject(subject2);

		assertEquals(subjectService.getAllSubjects().size(), 18);
	}

	@Test
	public void testGetSubjectById() throws SQLException {
		Subject subject = subjectService.getSubjectById(1);
		assertNotNull(subject);
		assertEquals(subject.getName(), "Algebra");
		assertEquals(subject.getType(), SubjectType.LECTURE);
		assertEquals(subject.getCourse(), 2);
	}

	@Test
	public void testGetAllSubjects() throws SQLException {
		assertEquals(subjectService.getAllSubjects().size(), 20);
	}

	@Test
	public void testGetSubjectsByCourse() throws SQLException {
		assertEquals(subjectService.getSubjectsByCourse(1).size(), 9);
		assertEquals(subjectService.getSubjectsByCourse(2).size(), 7);
		assertEquals(subjectService.getSubjectsByCourse(3).size(), 4);
	}

	@Test
	public void testGetSubject() throws SQLException {
		Subject subject = subjectService.getSubject("Algebra", SubjectType.LECTURE, 2);
		assertNotNull(subject);
		assertEquals(subject.getName(), "Algebra");
		assertEquals(subject.getType(), SubjectType.LECTURE);
		assertEquals(subject.getCourse(), 2);
	}

	@Test
	public void testGetUnusedSubjects() throws SQLException {
		List<Subject> subjects = subjectService.getUnusedSubjects();
		assertEquals(subjects.size(), 7);

		Group group = groupService.getGroupById(1);
		group.addSubject(subjects.get(0));
		group.addSubject(subjects.get(1));
		groupService.updateGroup(group);

		assertEquals(subjectService.getUnusedSubjects().size(), 5);
	}

	@Test
	public void testGetSubjectsByGroupId() throws SQLException {
		assertEquals(subjectService.getSubjectsByGroupId(1).size(), 4);
	}
}