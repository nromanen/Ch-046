package com.ss.schedule.institute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FacultyTest {

	private final File groupJson = new File("src/test/resources/testfiles/groups.json");
	private final File subjectJson = new File("src/test/resources/testfiles/subjects.json");

	@Test
	public void testCreateSubgroupsAndStreams() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Group> groups = mapper.readValue(groupJson, new TypeReference<List<Group>>() {
		});
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {
		});

		Faculty faculty = new Faculty("Programmers", groups, subjects, new ArrayList<>());
		HashMap<Subject, List<Group>> subgroupsStreams = faculty.getGroupsSubgroupsStreamsBySubject();

		assertEquals(subgroupsStreams.size(), 20);

		Subject subjectPythonLec = new Subject("Python", SubjectType.LECTURE);
		Subject subjectAlgebraPractice = new Subject("Algebra", SubjectType.PRACTICE, 1);
		Subject subjectMaLac = new Subject("MA", SubjectType.LECTURE, 1);
		Subject subjectMaPractice = new Subject("MA", SubjectType.PRACTICE, 1);

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
	public void testAddListOfSubjects() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {
		});
		Faculty faculty = new Faculty("Faculty", new ArrayList<>(), new ArrayList<>(subjects), new ArrayList<>());

		assertEquals(faculty.getSubjects().size(), 20);

		subjects.add(new Subject("Admin", SubjectType.LECTURE, 1));
		subjects.add(new Subject("Admin", SubjectType.LAB, 1));
		subjects.add(new Subject("Admin", SubjectType.LECTURE, 1));
		subjects.add(new Subject("Admin", SubjectType.LAB, 1));
		subjects.add(new Subject("Admin", SubjectType.PRACTICE, 1));
		subjects.add(new Subject("QA", SubjectType.LECTURE, 2));
		subjects.add(new Subject("QA", SubjectType.PRACTICE, 2));
		subjects.add(new Subject("QA", SubjectType.LECTURE, 2));

		int actualAddedSubjectsAmount = faculty.addSubjects(subjects);

		assertEquals(actualAddedSubjectsAmount, 5);
		assertEquals(faculty.getSubjects().size(), 25);
	}

	@Test
	public void testAddNewSubject() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {
		});
		Faculty faculty = new Faculty("Faculty", new ArrayList<>(), new ArrayList<>(subjects), new ArrayList<>());

		assertEquals(faculty.getSubjects().size(), 20);

		Subject subject1 = new Subject("Admin", SubjectType.LECTURE, 1);
		Subject subject2 = new Subject("Admin", SubjectType.LAB, 1);
		Subject subject3 = new Subject("QA", SubjectType.PRACTICE, 2);

		faculty.addSubject(subject1);
		assertEquals(faculty.getSubjects().size(), 21);
		faculty.addSubject(subject2);
		assertEquals(faculty.getSubjects().size(), 22);
		faculty.addSubject(subject3);
		assertEquals(faculty.getSubjects().size(), 23);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testAddSubjectThatHasAlreadyExisted() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {
		});
		Faculty faculty = new Faculty("Faculty", new ArrayList<>(), new ArrayList<>(subjects), new ArrayList<>());

		assertEquals(faculty.getSubjects().size(), 20);

		Subject subject1 = new Subject("Admin", SubjectType.LECTURE, 1);
		Subject subject2 = new Subject("Admin", SubjectType.LAB, 1);
		Subject subject3 = new Subject("QA", SubjectType.PRACTICE, 2);
		Subject subject4 = new Subject("Admin", SubjectType.LAB, 1);

		faculty.addSubject(subject1);
		assertEquals(faculty.getSubjects().size(), 21);
		faculty.addSubject(subject2);
		assertEquals(faculty.getSubjects().size(), 22);
		faculty.addSubject(subject3);
		assertEquals(faculty.getSubjects().size(), 23);
		faculty.addSubject(subject4);
	}

	@Test
	public void testDeleteExistentSubject() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {
		});
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
	public void testDeleteDoesNotExistentSubject() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {
		});
		Faculty faculty = new Faculty("Faculty", new ArrayList<>(), new ArrayList<>(subjects), new ArrayList<>());

		assertEquals(faculty.getSubjects().size(), 20);

		Subject subject = new Subject("QA", SubjectType.LECTURE, 2);
		faculty.deleteSubject(subject);
	}
}