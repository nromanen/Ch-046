package com.ss.schedule.institute;

import java.util.ArrayList;
import java.util.List;

import com.ss.schedule.dao.*;
import com.ss.schedule.exceptions.TimetableException;
import com.ss.schedule.model.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.ss.schedule.model.SubjectType.LECTURE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.*;



public class TimeTableManagerTest {


//    TimeTableManager manager = new TimeTableManager();
    TimeTableManager managerOleg=new TimeTableManager();
    List<Pair> restriction = new ArrayList<>();
    ArrayList<TimeTable> list = new ArrayList<>();
    ArrayList<TimeTable> listWednesdayEvenWeek = new ArrayList<>();
    ArrayList<TimeTable> lessonsByTeacher5 = new ArrayList<>();
    List<Pair> freePairs1 = new ArrayList<>();
    List<Pair> freePairs2 = new ArrayList<>();
    List<TimeTable> sortedPairsByDayAndWeek = new ArrayList<>();
    List<TimeTable> windowsInDay = new ArrayList<>();
    private Util FCS;


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
        List<TimeTable> timeTables = new TimeTableDao().getAll();
//        timeTables.get(0).setTeacher(new Teacher("1","1"));
//        timeTables.get(1).setTeacher(new Teacher("2","2"));
        managerOleg.setTimeTables(timeTables);

    }

    public  List<TimeTable> getTimetables(){
        Subject english=new Subject();
        english.setName("English");
        english.setType(LECTURE);
        english.setCourse(3);


        Subject spanish=new Subject();
        spanish.setName("Spanish");
        spanish.setType(LECTURE);
        spanish.setCourse(3);

        Subject chineese=new Subject();
        chineese.setName("Chineese");
        chineese.setType(LECTURE);
        chineese.setCourse(3);

        Subject math=new Subject();
        math.setName("Math");
        math.setType(LECTURE);
        math.setCourse(3);

        TimeTable tt1=new TimeTable();
        tt1.setStudentCommunity(new GroupDao().getById(1061));
        tt1.setDay(DayOfWeek.MONDAY);
        tt1.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt1.setPair(Pair.FIRST);
        tt1.setSubject(new JdbcSubjectDao().getById(121));
        tt1.setClassroom(new ClassroomDao().getById(1));
        tt1.setTeacher(new TeachersDao().getById(1));

//        TimeTable tt2=new TimeTable();
//        tt2.setDay(DayOfWeek.MONDAY);
//        tt2.setOddnessOfWeek(OddnessOfWeek.ODD);
//        tt2.setPair(Pair.FIRST);
//        tt2.setSubject(spanish);
//        tt2.setStudentCommunity(new Stream());
//        tt2.setTeacher(new Teacher());
//        tt2.setClassroom(FCS.getClassrooms().get(3));
//        tt2.setTeacher(FCS.getTeachers().get(0));
//
//        TimeTable tt3=new TimeTable();
//        tt3.setDay(DayOfWeek.MONDAY);
//        tt3.setOddnessOfWeek(OddnessOfWeek.ODD);
//        tt3.setPair(Pair.SIXTH);
//        tt3.setSubject(chineese);
//        tt3.setStudentCommunity(new Stream());
//        tt3.setTeacher(new Teacher());
//        tt3.setClassroom(FCS.getClassrooms().get(3));
//        tt3.setTeacher(FCS.getTeachers().get(0));
//
//
//        TimeTable tt4=new TimeTable();
//        tt4.setDay(DayOfWeek.MONDAY);
//        tt4.setOddnessOfWeek(OddnessOfWeek.ODD);
//        tt4.setPair(Pair.SEVENTH);
//        tt4.setSubject(math);
//        tt4.setStudentCommunity(new Stream());
//        tt4.setTeacher(new Teacher());
//        tt4.setClassroom(FCS.getClassrooms().get(3));
//        tt4.setTeacher(FCS.getTeachers().get(0));

        List<TimeTable> timeTables=new ArrayList<>();
        timeTables.add(tt1);
//        timeTables.add(tt2);
//        timeTables.add(tt3);
//        timeTables.add(tt4);

        return timeTables;


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



    @Test
    public void getLessonsByWeekTest() {
//        assertTrue(manager.getLessonsByWeek(OddnessOfWeek.EVEN).containsAll(listWednesdayEvenWeek));
//        assertEquals(listWednesdayEvenWeek, manager.getLessonsByWeek(OddnessOfWeek.EVEN));
//        assertNotEquals(list, manager.getLessonsByWeek(OddnessOfWeek.EVEN));
    }

    @Test
    public void getLessonsByDayAndWeekTest() {
//        assertEquals(listWednesdayEvenWeek, manager.getLessonsByDayAndWeek(OddnessOfWeek.EVEN, DayOfWeek.WEDNESDAY));
//        assertNotEquals(list, manager.getLessonsByDayAndWeek(OddnessOfWeek.EVEN, DayOfWeek.WEDNESDAY));
    }

    @Test
    public void getFreeDaylyPairsTest() {
//        assertEquals(freePairs1, manager.getFreeDaylyPairs(DayOfWeek.MONDAY));
//        assertNotEquals(freePairs2, manager.getFreeDaylyPairs(DayOfWeek.MONDAY));
    }

    @Test
    public void getLessonsByTeacherTest() {
//        assertEquals(lessonsByTeacher5, manager.getLessonByTeacher("Anderson"));
//        assertNotEquals(list, manager.getLessonByTeacher("Anderson"));
    }

    @Test
    public void getlessonByGroupTest() {
//        assertEquals(list, manager.getTimetablesForGroup("101"));
//        assertNotEquals(listWednesdayEvenWeek, manager.getTimetablesForGroup("101"));
    }

    @Test
    public void sortTimeTablesTest() {
//        assertEquals(sortedPairsByDayAndWeek.get(0).getPair(), manager.sortTimeTables(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());
//        assertNotEquals(list.get(0).getPair(), manager.sortTimeTables(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());
    }

    @Test
    public void getWindowsInDayTest(){
//        assertEquals(windowsInDay.get(0).getPair(), manager.getWindowsInDay(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());;
//        assertNotEquals(sortedPairsByDayAndWeek.get(0).getPair(), manager.getWindowsInDay(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());
    }



    @Test
    public void testIsClassroomFreeNow() throws Exception {
        System.out.println(FCS.getClassrooms());
//        assertEquals(timet,false);
    }

    @Test
    public void testIsTeacherFreeNow() throws Exception {
//        boolean teacherFreeNow = managerOleg.
//                isTeacherFreeNow(DayOfWeek.MONDAY, OddnessOfWeek.ALL, FCS.getTeachers().get(0), Pair.FIRST);
//        System.out.println(teacherFreeNow);
//        assertFalse(teacherFreeNow);
    }

    @Test
    public void testIsGroupFreeNow() throws Exception {
        List<TimeTable> timetablesOfGroupOrSubgroup = new TimeTableDao().getTimetablesOfGroupOrSubgroup(getTimetables().get(0));

    }

    @Test
    public void testAddTimeTable() throws Exception {

        try {
            boolean b = managerOleg.canAddTimetable(getTimetables().get(0));
        } catch (TimetableException e){
            System.out.println(e.toString());
        }
    }

    @Test
    public void testFindGroupByName() throws Exception {
        Group groupByName = managerOleg.findGroupByName(FCS, "23");
        assertEquals(FCS.getGroups().get(7),groupByName);
    }
}