package com.ss.schedule.institute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FacultyTest {

	private final File groupJson = new File("src/test/resources/testfiles/groups.json");
	private final File subjectJson = new File("src/test/resources/testfiles/subjects.json");

	@Test
	public void testCreateSubgroupsAndStreams() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Group> groups = mapper.readValue(groupJson, new TypeReference<List<Group>>() {});
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {});

		Faculty faculty = new Faculty("Programmers", groups, subjects, new ArrayList<>());
		HashMap<Subject, List<Group>> subgroupsStreams = faculty.getGroupsSubgroupsStreams();

		assertEquals(subgroupsStreams.size(), 20);

		Subject subjectPythonLec = new Subject("Python", 0, SubjectType.LECTURE);
		Subject subjectAlgebraPractice = new Subject("Algebra", 1, SubjectType.PRACTICE);
		Subject subjectMaLac = new Subject("MA", 1, SubjectType.LECTURE);
		Subject subjectMaPractice = new Subject("MA", 1, SubjectType.PRACTICE);

		List<Group> expectedGroups = subgroupsStreams.get(subjectPythonLec);
		assertEquals(subgroupsStreams.get(subjectPythonLec).size(), 1);
		assertEquals(expectedGroups.get(0).getName(), "21_22");
		assertEquals(expectedGroups.get(0).getCount(), 19);
		assertEquals(expectedGroups.get(0).getSubjects().size(), 1);
		assertEquals(expectedGroups.get(0).getSubjects().get(0), subjectPythonLec);

		expectedGroups = subgroupsStreams.get(subjectAlgebraPractice);
		assertEquals(subgroupsStreams.get(subjectAlgebraPractice).size(), 6);
		assertEquals(expectedGroups.get(0).getName(), "11-1");
		assertEquals(expectedGroups.get(1).getName(), "11-2");
		assertEquals(expectedGroups.get(2).getName(), "12-1");
		assertEquals(expectedGroups.get(3).getName(), "12-2");
		assertEquals(expectedGroups.get(4).getName(), "13");
		assertEquals(expectedGroups.get(5).getName(), "14");
		assertEquals(expectedGroups.get(0).getCount(), 10);
		assertEquals(expectedGroups.get(1).getCount(), 10);
		assertEquals(expectedGroups.get(2).getCount(), 11);
		assertEquals(expectedGroups.get(3).getCount(), 11);
		assertEquals(expectedGroups.get(4).getCount(), 5);
		assertEquals(expectedGroups.get(5).getCount(), 7);

		expectedGroups = subgroupsStreams.get(subjectMaLac);
		assertEquals(expectedGroups.size(), 0);

		expectedGroups = subgroupsStreams.get(subjectMaPractice);
		assertEquals(expectedGroups.size(), 0);
	}

	@Test
	public void testAddListOfSubjects() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {});
		Faculty faculty = new Faculty("Faculty", new ArrayList<>(), new ArrayList<>(subjects), new ArrayList<>());

		assertEquals(faculty.getSubjects().size(), 20);

		subjects.add(new Subject("Admin", 1, SubjectType.LECTURE));
		subjects.add(new Subject("Admin", 1, SubjectType.LAB));
		subjects.add(new Subject("Admin", 1, SubjectType.LECTURE));
		subjects.add(new Subject("Admin", 1, SubjectType.LAB));
		subjects.add(new Subject("Admin", 1, SubjectType.PRACTICE));
		subjects.add(new Subject("QA", 2, SubjectType.LECTURE));
		subjects.add(new Subject("QA", 2, SubjectType.PRACTICE));
		subjects.add(new Subject("QA", 2, SubjectType.LECTURE));

		int actualAddedSubjectsAmount = faculty.addListOfSubjects(subjects);

		assertEquals(actualAddedSubjectsAmount, 5);
		assertEquals(faculty.getSubjects().size(), 25);
	}

	@Test
	public void testAddNewSubject() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {
		});
		Faculty faculty = new Faculty("Faculty", new ArrayList<>(), new ArrayList<>(subjects), new ArrayList<>());

		assertEquals(faculty.getSubjects().size(), 20);

		Subject subject1 = new Subject("Admin", 1, SubjectType.LECTURE);
		Subject subject2 = new Subject("Admin", 1, SubjectType.LAB);
		Subject subject3 = new Subject("QA", 2, SubjectType.PRACTICE);

		faculty.addSubject(subject1);
		assertEquals(faculty.getSubjects().size(), 21);
		faculty.addSubject(subject2);
		assertEquals(faculty.getSubjects().size(), 22);
		faculty.addSubject(subject3);
		assertEquals(faculty.getSubjects().size(), 23);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testAddSubjectThatHasAlreadyExisted() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {	});
		Faculty faculty = new Faculty("Faculty", new ArrayList<>(), new ArrayList<>(subjects), new ArrayList<>());

		assertEquals(faculty.getSubjects().size(), 20);

		Subject subject1 = new Subject("Admin", 1, SubjectType.LECTURE);
		Subject subject2 = new Subject("Admin", 1, SubjectType.LAB);
		Subject subject3 = new Subject("QA", 2, SubjectType.PRACTICE);
		Subject subject4 = new Subject("Admin", 1, SubjectType.LAB);

		faculty.addSubject(subject1);
		assertEquals(faculty.getSubjects().size(), 21);
		faculty.addSubject(subject2);
		assertEquals(faculty.getSubjects().size(), 22);
		faculty.addSubject(subject3);
		assertEquals(faculty.getSubjects().size(), 23);
		faculty.addSubject(subject4);
	}

	@Test
	public void testDeleteExistentSubject() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {	});
		Faculty faculty = new Faculty("Faculty", new ArrayList<>(), new ArrayList<>(subjects), new ArrayList<>());

		assertEquals(faculty.getSubjects().size(), 20);
		
		faculty.deleteSubject(subjects.get(0));
		assertEquals(faculty.getSubjects().size(), 19);
		faculty.deleteSubject(subjects.get(1));
		assertEquals(faculty.getSubjects().size(), 18);
		faculty.deleteSubject(subjects.get(2));
		assertEquals(faculty.getSubjects().size(), 17);
		faculty.deleteSubject(subjects.get(3));
		assertEquals(faculty.getSubjects().size(), 16);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testDeleteDoesNotExistentSubject() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {	});
		Faculty faculty = new Faculty("Faculty", new ArrayList<>(), new ArrayList<>(subjects), new ArrayList<>());

		assertEquals(faculty.getSubjects().size(), 20);

		Subject subject = new Subject("QA", 2, SubjectType.LECTURE);
		faculty.deleteSubject(subject);
	}

	@Test
	public void testGetUnusedSubjects() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Group> groups = mapper.readValue(groupJson, new TypeReference<List<Group>>() {});
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {});

		Faculty faculty = new Faculty("Faculty", new ArrayList<>(groups), new ArrayList<>(subjects), new ArrayList<>());

		List<Subject> unusedSubjects = faculty.getUnusedSubjects();

		assertEquals(unusedSubjects.size(), 7);
		assertTrue(unusedSubjects.contains(subjects.get(11)));
		assertTrue(unusedSubjects.contains(subjects.get(12)));
		assertTrue(unusedSubjects.contains(subjects.get(13)));
		assertTrue(unusedSubjects.contains(subjects.get(14)));
		assertTrue(unusedSubjects.contains(subjects.get(15)));
		assertTrue(unusedSubjects.contains(subjects.get(16)));
		assertTrue(unusedSubjects.contains(subjects.get(17)));
	}
}