package com.ss.schedule.institute;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.*;

import com.ss.schedule.model.DayOfWeek;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.OddnessOfWeek;
import com.ss.schedule.model.Pair;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.Teacher;
import com.ss.schedule.model.TimeTable;
import com.ss.schedule.model.SubjectType;
import com.ss.schedule.institute.TimeTableBuilder;
import com.ss.schedule.institute.TimeTableManager;

public class TimeTableManagerTest {
	TimeTableManager manager = new TimeTableManager();
	List<Pair> restriction = new ArrayList<>();
	ArrayList<TimeTable> list = new ArrayList<>();
	ArrayList<TimeTable> listWednesdayEvenWeek = new ArrayList<>();
	ArrayList<TimeTable> lessonsByTeacher5 = new ArrayList<>();
	List<Pair> freePairs1 = new ArrayList<>();
	List<Pair> freePairs2 = new ArrayList<>();
	List<TimeTable> sortedPairsByDayAndWeek = new ArrayList<>();
	List<TimeTable> windowsInDay = new ArrayList<>();
	Subject[] subjectActual = new Subject[4];
	LinkedHashMap<Subject, List<Group>> groupsSubgroupsStreams = new LinkedHashMap<>();
	Subject subject1 = new Subject();
	Subject subject2 = new Subject();
	TimeTable timeTable = new TimeTable();
	
	
	@BeforeTest
	public void setRestriction() {
		restriction.add(Pair.FIRST);
		restriction.add(Pair.SECOND);
		restriction.add(Pair.THIRD);
		restriction.add(Pair.FORTH);
		restriction.add(Pair.FIFTH);
		restriction.add(Pair.SIXTH);
		restriction.add(Pair.SEVENTH);
	}

	/* before method for tests: getLessonsByWeekTest(); getLessonsByDayAndWeekTest(); getFreeDaylyPairsTest();
	 * getLessonsByTeacherTest(); getlessonByGroupTest(); sortTimeTablesTest(); getWindowsInDayTest()  
	 */  
	@BeforeTest
	public void setTimeTableLists() {
		Teacher teacher1 = new Teacher();
		teacher1.setFirstName("Ann");
		teacher1.setLastName("Parkinson");

		Teacher teacher2 = new Teacher();
		teacher2.setFirstName("Ben");
		teacher2.setLastName("Alington");

		Teacher teacher3 = new Teacher();
		teacher3.setFirstName("Peter");
		teacher3.setLastName("Jason");

		Teacher teacher4 = new Teacher();
		teacher4.setFirstName("Kate");
		teacher4.setLastName("Peterson");

		Teacher teacher5 = new Teacher();
		teacher5.setFirstName("Poll");
		teacher5.setLastName("Anderson");

		Subject subject1 = new Subject();
		subject1.setName("Algebra");
		subject1.setType(SubjectType.PRACTICE);
		subject1.setCourse(1);

		Subject subject2 = new Subject();
		subject2.setName("Geometria");
		subject2.setType(SubjectType.SEMINAR);
		subject2.setCourse(1);

		Subject subject3 = new Subject();
		subject3.setName("Physics");
		subject3.setType(SubjectType.LAB);
		subject3.setCourse(1);

		Subject subject4 = new Subject();
		subject4.setName("Philosophy");
		subject4.setType(SubjectType.LECTURE);
		subject4.setCourse(1);

		Subject subject5 = new Subject();
		subject5.setName("English");
		subject5.setType(SubjectType.PRACTICE);
		subject5.setCourse(1);
		
    	Group group1 = new Group();
		group1.setName("101");
		group1.setCount(21);

		TimeTable table1 = new TimeTableBuilder(restriction).buildSubject(subject1).buildPair(Pair.FIRST)
				.buildTeacher(teacher1).buildGroup(group1).buildDayOfWeek(DayOfWeek.MONDAY).build();

		TimeTable table2 = new TimeTableBuilder(restriction).buildSubject(subject2).buildPair(Pair.FIFTH)
				.buildTeacher(teacher2).buildGroup(group1).buildDayOfWeek(DayOfWeek.MONDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();

		TimeTable table3 = new TimeTableBuilder(restriction).buildSubject(subject3).buildPair(Pair.THIRD)
				.buildTeacher(teacher3).buildGroup(group1).buildDayOfWeek(DayOfWeek.MONDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();

		TimeTable table4 = new TimeTableBuilder(restriction).buildSubject(subject4).buildPair(Pair.SEVENTH)
				.buildTeacher(teacher4).buildGroup(group1).buildDayOfWeek(DayOfWeek.TUESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();

		TimeTable table5 = new TimeTableBuilder(restriction).buildSubject(subject5).buildPair(Pair.FORTH)
				.buildTeacher(teacher5).buildGroup(group1).buildDayOfWeek(DayOfWeek.TUESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();

		TimeTable table6 = new TimeTableBuilder(restriction).buildSubject(subject1).buildPair(Pair.SECOND)
				.buildTeacher(teacher1).buildGroup(group1).buildDayOfWeek(DayOfWeek.TUESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();
		TimeTable table7 = new TimeTableBuilder(restriction).buildSubject(subject1).buildPair(Pair.SEVENTH)
				.buildTeacher(teacher1).buildGroup(group1).buildDayOfWeek(DayOfWeek.WEDNESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.EVEN).build();
		list.add(table1);
		list.add(table2);
		list.add(table3);
		list.add(table4);
		list.add(table5);
		list.add(table6);
		list.add(table7);
		listWednesdayEvenWeek.add(table7);
		lessonsByTeacher5.add(table5);
		
		//for sortTimeTablesTest()
		TimeTable table8 = new TimeTableBuilder(restriction).buildSubject(subject3).buildPair(Pair.SECOND)
				.buildTeacher(teacher3).buildGroup(group1).buildDayOfWeek(DayOfWeek.MONDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();

		TimeTable table9 = new TimeTableBuilder(restriction).buildSubject(subject4).buildPair(Pair.FIFTH)
				.buildTeacher(teacher4).buildGroup(group1).buildDayOfWeek(DayOfWeek.TUESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();

		TimeTable table10 = new TimeTableBuilder(restriction).buildSubject(subject5).buildPair(Pair.SEVENTH)
				.buildTeacher(teacher5).buildGroup(group1).buildDayOfWeek(DayOfWeek.TUESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();
		
		sortedPairsByDayAndWeek.add(table8);
		sortedPairsByDayAndWeek.add(table9);
		sortedPairsByDayAndWeek.add(table10);
		
		//for getWindowsInDayTest()
		TimeTable table11 = new TimeTableBuilder(restriction).buildSubject(subject3).buildPair(Pair.THIRD)
				.buildTeacher(teacher3).buildGroup(group1).buildDayOfWeek(DayOfWeek.MONDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();
		
		TimeTable table12 = new TimeTableBuilder(restriction).buildSubject(subject4).buildPair(Pair.FORTH)
				.buildTeacher(teacher4).buildGroup(group1).buildDayOfWeek(DayOfWeek.TUESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();
		
		TimeTable table13 = new TimeTableBuilder(restriction).buildSubject(subject5).buildPair(Pair.SIXTH)
				.buildTeacher(teacher5).buildGroup(group1).buildDayOfWeek(DayOfWeek.TUESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD).build();
		windowsInDay.add(table11);
		windowsInDay.add(table12);
		windowsInDay.add(table13);
	}

	@BeforeTest
	public void setTimeTableManager() {
		manager.setTimeTable(list);
	}

	/* before method for getFreeDaylyPairsTest()
	 */
	@BeforeTest
	public void setFreeDaylyPairs(){
		freePairs1.add(Pair.SECOND);
		freePairs1.add(Pair.FORTH);
		freePairs1.add(Pair.SIXTH);
		freePairs1.add(Pair.SEVENTH);
		freePairs1.add(Pair.EIGHTS);
		freePairs1.add(Pair.NINTH);
		freePairs1.add(Pair.TENTH);
		
		freePairs2.add(Pair.SECOND);
		freePairs2.add(Pair.FORTH);
		freePairs2.add(Pair.SIXTH);
		freePairs2.add(Pair.SEVENTH);
		freePairs2.add(Pair.EIGHTS);
		freePairs2.add(Pair.NINTH);
	}
	
	/* before method for subjectsSetToArrayTest(), findSubjectInKeySetTest(); 
	 */
	@BeforeTest
	public void setSubjects(){
		
		
		Subject subject3 = new Subject();
		Subject subject4 = new Subject();
		Subject subject5 = new Subject();
		subject1.setName("Algebra");
		subject1.setType(SubjectType.LAB);
		subject1.setCourse(1);

		subject2.setName("Mathematic");
		subject2.setType(SubjectType.LECTURE);
		subject2.setCourse(1);
		
		subject3.setName("Geometria");
		subject3.setType(SubjectType.PRACTICE);
		subject3.setCourse(1);
		
		subject4.setName("English");
		subject4.setType(SubjectType.SEMINAR);
		subject4.setCourse(1);
		
		subject5.setName("Algebra");
		subject5.setType(SubjectType.LAB);
		subject5.setCourse(1);
		
		List<Subject> subjects1= new ArrayList<>();
		subjects1.add(subject1);
		subjects1.add(subject2);
		subjects1.add(subject3);
		
		List<Subject> subjects2= new ArrayList<>();
		subjects2.add(subject4);
		subjects2.add(subject5);
		
		Group group1 = new Group();
		Group group2 = new Group();
		group1.setName("101");
		group1.setCount(30);
		group1.setSubjects(subjects1);
		
		group2.setName("102");
		group2.setCount(30);
		group2.setSubjects(subjects2);
		List<Group> groups = new ArrayList<>();
		groups.add(group1);
		groups.add(group2);
		
		groupsSubgroupsStreams.put(subject1, groups);
		groupsSubgroupsStreams.put(subject2, groups);
		groupsSubgroupsStreams.put(subject3, groups);
		groupsSubgroupsStreams.put(subject4, groups);
		groupsSubgroupsStreams.put(subject5, groups);
		
		subjectActual[0] = subject1;
		subjectActual[1] = subject2;
		subjectActual[2] = subject3;
		subjectActual[3] = subject4;
		
		// findSubjectInKeySetTest()
		Teacher teacher = new Teacher();
		teacher.setFirstName("Ann");
		teacher.setLastName("Parkinson");
		timeTable = new TimeTableBuilder(restriction).buildSubject(subject1).buildPair(Pair.FIRST)
				.buildTeacher(teacher).buildGroup(group1).buildDayOfWeek(DayOfWeek.MONDAY).build();
	}
	
	@Test
	public void getLessonsByWeekTest() {
		assertTrue(manager.getLessonsByWeek(OddnessOfWeek.EVEN).containsAll(listWednesdayEvenWeek));
		assertEquals(listWednesdayEvenWeek, manager.getLessonsByWeek(OddnessOfWeek.EVEN));
		assertNotEquals(list, manager.getLessonsByWeek(OddnessOfWeek.EVEN));
	}
	
	@Test
	public void getLessonsByDayAndWeekTest() {
		assertEquals(listWednesdayEvenWeek, manager.getLessonsByDayAndWeek(OddnessOfWeek.EVEN, DayOfWeek.WEDNESDAY));
		assertNotEquals(list, manager.getLessonsByDayAndWeek(OddnessOfWeek.EVEN, DayOfWeek.WEDNESDAY));
	}

	@Test
	public void getFreeDaylyPairsTest() {
		assertEquals(freePairs1, manager.getFreeDaylyPairs(DayOfWeek.MONDAY));
		assertNotEquals(freePairs2, manager.getFreeDaylyPairs(DayOfWeek.MONDAY));
	}
	
	@Test
	public void getLessonsByTeacherTest() {
		assertEquals(lessonsByTeacher5, manager.getLessonByTeacher("Anderson"));
		assertNotEquals(list, manager.getLessonByTeacher("Anderson"));
	}
	
	@Test
	public void getlessonByGroupTest() {
		assertEquals(list, manager.getlessonByGroup("101"));
		assertNotEquals(listWednesdayEvenWeek, manager.getlessonByGroup("101"));
	}
	
	@Test
	public void sortTimeTablesTest() {
		assertEquals(sortedPairsByDayAndWeek.get(0).getPair(), manager.sortTimeTables(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());
		assertNotEquals(list.get(0).getPair(), manager.sortTimeTables(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());
	}
	
	@Test
	public void getWindowsInDayTest(){
		assertEquals(windowsInDay.get(0).getPair(), manager.getWindowsInDay(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());;
		assertNotEquals(sortedPairsByDayAndWeek.get(0).getPair(), manager.getWindowsInDay(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());
	}
	
	@Test
	public void subjectsSetToArray(){
		Subject[] subjects = new Subject[10];
		assertEquals(subjectActual, manager.subjectsSetToArray(groupsSubgroupsStreams));
		assertNotEquals(subjects, manager.subjectsSetToArray(groupsSubgroupsStreams));
	}
	
	@Test
	public void findSubjectInKeySet(){
		assertEquals(subject1, manager.findSubjectInKeySet(manager.subjectsSetToArray(groupsSubgroupsStreams), timeTable));
		assertNotEquals(subject2, manager.findSubjectInKeySet(manager.subjectsSetToArray(groupsSubgroupsStreams), timeTable));
	}
	
	
}
