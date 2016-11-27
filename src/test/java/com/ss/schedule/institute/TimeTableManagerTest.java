package com.ss.schedule.institute;

import java.util.ArrayList;
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

		Group group1 = new Group();
		group1.setName("101");
		group1.setCount(21);

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
	
	@BeforeTest
	public void setSortedPairsByDayAndWeek(){
		
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
}
