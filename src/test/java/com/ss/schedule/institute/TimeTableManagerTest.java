//package com.ss.schedule.institute;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.ss.schedule.io.InputOutputJson;
//import com.ss.schedule.model.*;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import static com.ss.schedule.model.SubjectType.LECTURE;
//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.*;
//
//import com.ss.schedule.institute.TimeTableBuilder;
//import com.ss.schedule.institute.TimeTableManager;
//
//public class TimeTableManagerTest {
//
//
//    TimeTableManager manager = new TimeTableManager();
//    TimeTableManager managerOleg=new TimeTableManager();
//    List<Pair> restriction = new ArrayList<>();
//    ArrayList<TimeTable> list = new ArrayList<>();
//    ArrayList<TimeTable> listWednesdayEvenWeek = new ArrayList<>();
//    ArrayList<TimeTable> lessonsByTeacher5 = new ArrayList<>();
//    List<Pair> freePairs1 = new ArrayList<>();
//    List<Pair> freePairs2 = new ArrayList<>();
//    List<TimeTable> sortedPairsByDayAndWeek = new ArrayList<>();
//    List<TimeTable> windowsInDay = new ArrayList<>();
//    private Faculty FCS;
//
//
//    @BeforeTest
//    public void setRestriction() {
//        restriction.add(Pair.FIRST);
//        restriction.add(Pair.SECOND);
//        restriction.add(Pair.THIRD);
//        restriction.add(Pair.FORTH);
//        restriction.add(Pair.FIFTH);
//        restriction.add(Pair.SIXTH);
//        restriction.add(Pair.SEVENTH);
//    }
//
//    @BeforeTest
//    public void setTimeTableLists() {
//        InputOutputJson<ArrayList<Classroom>> iojClassRooms = new InputOutputJson<>(
//                new TypeReference<ArrayList<Classroom>>() {
//                });
//        ArrayList<Classroom> classrooms = iojClassRooms.readFromFile("room.json");
//        InputOutputJson<List<Group>>iojGroups=new InputOutputJson<>(new TypeReference<List<Group>>() {
//        });
//        InputOutputJson<List<Subject>>iojSubjects=new InputOutputJson<>(new TypeReference<List<Subject>>() {
//        });
//        InputOutputJson<List<Teacher>>iojTeachers=new InputOutputJson<>(new TypeReference<List<Teacher>>() {
//        });
//
//        List<Group> groups = iojGroups.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/" +
//                "OneDrive/Education3/JavaFx/TimeTable1/Ch-046/src/main/resourses/group.json");
//        List<Subject> subjects = iojSubjects.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/" +
//                "OneDrive/Education3/JavaFx/TimeTable1/Ch-046/src/main/resourses/subject.json");
//        List<Teacher> teachers=iojTeachers.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/OneDrive/" +
//                "Education3/JavaFx/TimeTable1/Ch-046/src/main/resourses/teacher.json");
//
//        FCS=new Faculty("Faculty of computer science",groups,subjects,teachers,classrooms);
////        FCS.classrooms=(classrooms);
//        FCS.getGroups().add(0,new Group("23_24",9,new LinkedList<>()));
//        LinkedHashMap<Subject, List<Group>> groupsSubgroupsStreams = FCS.getGroupsSubgroupsStreams();
//
//        Teacher teacher1 = new Teacher();
//        teacher1.setFirstName("Ann");
//        teacher1.setLastName("Parkinson");
//
//        Teacher teacher2 = new Teacher();
//        teacher2.setFirstName("Ben");
//        teacher2.setLastName("Alington");
//
//        Teacher teacher3 = new Teacher();
//        teacher3.setFirstName("Peter");
//        teacher3.setLastName("Jason");
//
//        Teacher teacher4 = new Teacher();
//        teacher4.setFirstName("Kate");
//        teacher4.setLastName("Peterson");
//
//        Teacher teacher5 = new Teacher();
//        teacher5.setFirstName("Poll");
//        teacher5.setLastName("Anderson");
//
//        Group groupArrayList1 = new Group();
//        groupArrayList1.setName("101");
//        groupArrayList1.setCount(21);
//
//        Subject subject1 = new Subject();
//        subject1.setName("Algebra");
//        subject1.setType(SubjectType.PRACTICE);
//        subject1.setCourse(1);
//
//        Subject subject2 = new Subject();
//        subject2.setName("Geometria");
//        subject2.setType(SubjectType.SEMINAR);
//        subject2.setCourse(1);
//
//        Subject subject3 = new Subject();
//        subject3.setName("Physics");
//        subject3.setType(SubjectType.LAB);
//        subject3.setCourse(1);
//
//        Subject subject4 = new Subject();
//        subject4.setName("Philosophy");
//        subject4.setType(SubjectType.LECTURE);
//        subject4.setCourse(1);
//
//        Subject subject5 = new Subject();
//        subject5.setName("English");
//        subject5.setType(SubjectType.PRACTICE);
//        subject5.setCourse(1);
//
//
//        Group group1 = new Group();
//        group1.setName("101");
//        group1.setCount(21);
//       List<Group> groupArrayList = new ArrayList<>();
//        groupArrayList.add(group1);
//
//        TimeTable table1 = new TimeTableBuilder(restriction).buildSubject(subject1).buildPair(Pair.FIRST)
//                .buildTeacher(teacher1).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.MONDAY).build();
//
//        TimeTable table2 = new TimeTableBuilder(restriction).buildSubject(subject2).buildPair(Pair.FIFTH)
//                .buildTeacher(teacher2).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.MONDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//
//        TimeTable table3 = new TimeTableBuilder(restriction).buildSubject(subject3).buildPair(Pair.THIRD)
//                .buildTeacher(teacher3).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.MONDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//
//        TimeTable table4 = new TimeTableBuilder(restriction).buildSubject(subject4).buildPair(Pair.SEVENTH)
//                .buildTeacher(teacher4).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.TUESDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//
//        TimeTable table5 = new TimeTableBuilder(restriction).buildSubject(subject5).buildPair(Pair.FORTH)
//                .buildTeacher(teacher5).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.TUESDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//
//        TimeTable table6 = new TimeTableBuilder(restriction).buildSubject(subject1).buildPair(Pair.SECOND)
//                .buildTeacher(teacher1).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.TUESDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//        TimeTable table7 = new TimeTableBuilder(restriction).buildSubject(subject1).buildPair(Pair.SEVENTH)
//                .buildTeacher(teacher1).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.WEDNESDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.EVEN).build();
//        list.add(table1);
//        list.add(table2);
//        list.add(table3);
//        list.add(table4);
//        list.add(table5);
//        list.add(table6);
//        list.add(table7);
//        listWednesdayEvenWeek.add(table7);
//        lessonsByTeacher5.add(table5);
//
//        TimeTable table8 = new TimeTableBuilder(restriction).buildSubject(subject3).buildPair(Pair.SECOND)
//                .buildTeacher(teacher3).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.MONDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//
//        TimeTable table9 = new TimeTableBuilder(restriction).buildSubject(subject4).buildPair(Pair.FIFTH)
//                .buildTeacher(teacher4).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.TUESDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//
//        TimeTable table10 = new TimeTableBuilder(restriction).buildSubject(subject5).buildPair(Pair.SEVENTH)
//                .buildTeacher(teacher5).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.TUESDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//
//        sortedPairsByDayAndWeek.add(table8);
//        sortedPairsByDayAndWeek.add(table9);
//        sortedPairsByDayAndWeek.add(table10);
//
//        TimeTable table11 = new TimeTableBuilder(restriction).buildSubject(subject3).buildPair(Pair.THIRD)
//                .buildTeacher(teacher3).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.MONDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//
//        TimeTable table12 = new TimeTableBuilder(restriction).buildSubject(subject4).buildPair(Pair.FORTH)
//                .buildTeacher(teacher4).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.TUESDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//
//        TimeTable table13 = new TimeTableBuilder(restriction).buildSubject(subject5).buildPair(Pair.SIXTH)
//                .buildTeacher(teacher5).buildGroup(groupArrayList).buildDayOfWeek(DayOfWeek.TUESDAY)
//                .buildOddnessOfWeek(OddnessOfWeek.ODD).build();
//
//        windowsInDay.add(table11);
//        windowsInDay.add(table12);
//        windowsInDay.add(table13);
//
//        List<TimeTable> timetables = setTimetables();
//
//        managerOleg.setTimeTables(timetables);
//    }
//
//    @BeforeTest
//    public void setTimeTableManager() {
//        manager.setTimeTables(list);
//
//    }
//
//    @BeforeTest
//    public void setFreeDaylyPairs(){
//        freePairs1.add(Pair.SECOND);
//        freePairs1.add(Pair.FORTH);
//        freePairs1.add(Pair.SIXTH);
//        freePairs1.add(Pair.SEVENTH);
//        freePairs1.add(Pair.EIGHTS);
//        freePairs1.add(Pair.NINTH);
//        freePairs1.add(Pair.TENTH);
//
//        freePairs2.add(Pair.SECOND);
//        freePairs2.add(Pair.FORTH);
//        freePairs2.add(Pair.SIXTH);
//        freePairs2.add(Pair.SEVENTH);
//        freePairs2.add(Pair.EIGHTS);
//        freePairs2.add(Pair.NINTH);
//    }
//
//    @BeforeTest
//    public void setSortedPairsByDayAndWeek(){
//
//    }
//
//    @Test
//    public void getLessonsByWeekTest() {
//        assertTrue(manager.getLessonsByWeek(OddnessOfWeek.EVEN).containsAll(listWednesdayEvenWeek));
//        assertEquals(listWednesdayEvenWeek, manager.getLessonsByWeek(OddnessOfWeek.EVEN));
//        assertNotEquals(list, manager.getLessonsByWeek(OddnessOfWeek.EVEN));
//    }
//
//    @Test
//    public void getLessonsByDayAndWeekTest() {
//        assertEquals(listWednesdayEvenWeek, manager.getLessonsByDayAndWeek(OddnessOfWeek.EVEN, DayOfWeek.WEDNESDAY));
//        assertNotEquals(list, manager.getLessonsByDayAndWeek(OddnessOfWeek.EVEN, DayOfWeek.WEDNESDAY));
//    }
//
//    @Test
//    public void getFreeDaylyPairsTest() {
//        assertEquals(freePairs1, manager.getFreeDaylyPairs(DayOfWeek.MONDAY));
//        assertNotEquals(freePairs2, manager.getFreeDaylyPairs(DayOfWeek.MONDAY));
//    }
//
//    @Test
//    public void getLessonsByTeacherTest() {
//        assertEquals(lessonsByTeacher5, manager.getLessonByTeacher("Anderson"));
//        assertNotEquals(list, manager.getLessonByTeacher("Anderson"));
//    }
//
//    @Test
//    public void getlessonByGroupTest() {
//        assertEquals(list, manager.getlessonByGroup("101"));
//        assertNotEquals(listWednesdayEvenWeek, manager.getlessonByGroup("101"));
//    }
//
//    @Test
//    public void sortTimeTablesTest() {
////        assertEquals(sortedPairsByDayAndWeek.get(0).getPair(), manager.sortTimeTables(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());
////        assertNotEquals(list.get(0).getPair(), manager.sortTimeTables(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());
//    }
//
//    @Test
//    public void getWindowsInDayTest(){
//        assertEquals(windowsInDay.get(0).getPair(), manager.getWindowsInDay(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());;
//        assertNotEquals(sortedPairsByDayAndWeek.get(0).getPair(), manager.getWindowsInDay(OddnessOfWeek.ODD, DayOfWeek.TUESDAY).get(0).getPair());
//    }
//
//    public  List<TimeTable> setTimetables(){
//        Subject english=new Subject();
//        english.setName("English");
//        english.setType(LECTURE);
//        english.setCourse(3);
//
//
//        Subject spanish=new Subject();
//        spanish.setName("Spanish");
//        spanish.setType(LECTURE);
//        spanish.setCourse(3);
//
//        Subject chineese=new Subject();
//        chineese.setName("Chineese");
//        chineese.setType(LECTURE);
//        chineese.setCourse(3);
//
//        Subject math=new Subject();
//        math.setName("Math");
//        math.setType(LECTURE);
//        math.setCourse(3);
//
//        TimeTable tt1=new TimeTable();
//        tt1.setGroup(new ArrayList<>());
//        tt1.setDay(DayOfWeek.MONDAY);
//        tt1.setOddnessOfWeek(OddnessOfWeek.ODD);
//        tt1.setPair(Pair.FIRST);
//        tt1.setSubject(english);
//        tt1.getGroup().add(FCS.getGroups().get(7));
//        tt1.setTeacher(new Teacher());
//        tt1.setClassroom(FCS.getClassrooms().get(0));
//        tt1.setTeacher(FCS.getTeachers().get(0));
//
//        TimeTable tt2=new TimeTable();
//        tt2.setDay(DayOfWeek.MONDAY);
//        tt2.setOddnessOfWeek(OddnessOfWeek.ODD);
//        tt2.setPair(Pair.FIRST);
//        tt2.setSubject(spanish);
//        tt2.setGroup(new ArrayList<>());
//        tt2.setTeacher(new Teacher());
//        tt2.setClassroom(FCS.getClassrooms().get(3));
//        tt2.setTeacher(FCS.getTeachers().get(0));
//
//        TimeTable tt3=new TimeTable();
//        tt3.setDay(DayOfWeek.MONDAY);
//        tt3.setOddnessOfWeek(OddnessOfWeek.ODD);
//        tt3.setPair(Pair.SIXTH);
//        tt3.setSubject(chineese);
//        tt3.setGroup(new ArrayList<>());
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
//        tt4.setGroup(new ArrayList<>());
//        tt4.setTeacher(new Teacher());
//        tt4.setClassroom(FCS.getClassrooms().get(3));
//        tt4.setTeacher(FCS.getTeachers().get(0));
//
//        List<TimeTable> timeTables=new ArrayList<>();
//        timeTables.add(tt1);
////        timeTables.add(tt2);
////        timeTables.add(tt3);
////        timeTables.add(tt4);
//
//        return timeTables;
//    }
//
//    @Test
//    public void testIsClassroomFreeNow() throws Exception {
//        System.out.println(FCS.getClassrooms());
//        assertEquals(managerOleg.isClassroomFreeNow
//                (DayOfWeek.MONDAY,OddnessOfWeek.ODD,
//                FCS.getClassrooms().get(0),
//                        Pair.FIRST),false);
//    }
//
//    @Test
//    public void testIsTeacherFreeNow() throws Exception {
//        boolean teacherFreeNow = managerOleg.
//                isTeacherFreeNow(DayOfWeek.MONDAY, OddnessOfWeek.ALL, FCS.getTeachers().get(0), Pair.FIRST);
//        System.out.println(teacherFreeNow);
//        assertFalse(teacherFreeNow);
//    }
//
//    @Test
//    public void testIsGroupFreeNow() throws Exception {
//        FCS.getGroups().add(0,new Group("23_24",9,new LinkedList<>()));
//        List<Group> groups=new ArrayList<>();
//        groups.add( FCS.getGroups().get(0));
//        boolean groupFreeNow = managerOleg.isGroupFreeNow(DayOfWeek.MONDAY, OddnessOfWeek.ODD,
//               groups, Pair.FIRST, FCS.getGroupsSubgroupsStreams(), FCS);
//        assertFalse(groupFreeNow);
//    }
//
//    @Test
//    public void testAddTimeTable() throws Exception {
//        List<Group> groups=new ArrayList<>();
//        groups.add( FCS.getGroups().get(0));
//        TimeTable tt3=new TimeTable();
//        tt3.setDay(DayOfWeek.MONDAY);
//        tt3.setOddnessOfWeek(OddnessOfWeek.ALL);
//        tt3.setPair(Pair.FIRST);
//        tt3.setSubject(new Subject("jnlljk",5,LECTURE));
//        tt3.setGroup(groups);
//        tt3.setClassroom(FCS.getClassrooms().get(2));
//        tt3.setTeacher(FCS.getTeachers().get(0));
//        managerOleg.addTimeTable(tt3,FCS.getGroupsSubgroupsStreams(),FCS);
//        System.out.println("cnt");
//        boolean contains = managerOleg.getTimeTables().contains(tt3);
//        assertFalse(contains);
//    }
//
//    @Test
//    public void testFindGroupByName() throws Exception {
//        Group groupByName = managerOleg.findGroupByName(FCS, "23");
//        assertEquals(FCS.getGroups().get(7),groupByName);
//    }
//}