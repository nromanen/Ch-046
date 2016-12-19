package com.ss.schedule.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.institute.TimeTableManager;
import com.ss.schedule.institute.Util;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by oleg on 01.12.16.
 */
public class TimeTableDaoTEST {
    @Test
    public void testGetTimetablesOfGroupInPreciseTime() throws Exception {

    }

    TimeTableDao tableDao=new TimeTableDao();
    TimeTable timeTable, timeTableWithSubgroup,timeTableWithStream;
    Util FCS;

    @BeforeTest
    void setUp(){
        InputOutputJson<ArrayList<Classroom>> iojClassRooms = new InputOutputJson<>(
                new TypeReference<ArrayList<Classroom>>() {
                });
        InputOutputJson<List<Group>>iojGroups=new InputOutputJson<>(new TypeReference<List<Group>>() {
        });
        InputOutputJson<List<Subject>>iojSubjects=new InputOutputJson<>(new TypeReference<List<Subject>>() {
        });
        InputOutputJson<List<Teacher>>iojTeachers=new InputOutputJson<>(new TypeReference<List<Teacher>>() {
        });

        List<Classroom> classrooms = new ClassroomDao().getAll();
        List<Group> groups = new GroupDao().getAll();
        List<Subject> subjects = new JdbcSubjectDao().getAll();
//        List<Teacher> teachers=iojTeachers.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/OneDrive/" +
//                "Education3/JavaFx/TimeTable1/Ch-046 (copy)/src/main/resourses/teacher.json");

        FCS=new Util("Util of computer science",groups,subjects,null,classrooms);
        LinkedHashMap<Subject, List<? extends StudentCommunity>>
                groupsSubgroupsStreams = FCS.getGroupsSubgroupsStreams();
        Stream stream = (Stream) groupsSubgroupsStreams.get(FCS.getSubjects().get(2)).get(0);
        timeTable=new TimeTable();
        timeTable.setClassroom(new ClassroomDao().getById(1));
        timeTable.setDay(DayOfWeek.MONDAY);
        timeTable.setStudentCommunity(new GroupDao().getById(1061));
        timeTable.setSubject(new JdbcSubjectDao().getById(121));
        timeTable.setOddnessOfWeek(OddnessOfWeek.ODD);
        timeTable.setPair(Pair.FIRST);
        timeTable.setTeacher(new Teacher());
        // ВИПРАВИ БАГ З ПРЕДМЕТАМИ!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        // ВИПРАВИ БАГ З ПРЕДМЕТАМИ!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        // ВИПРАВИ БАГ З ПРЕДМЕТАМИ!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //НЕ ЗАБУДЬ ПРО ЦЕ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
//        timeTableWithSubgroup =new TimeTable();
//        timeTableWithSubgroup.setClassroom(new ClassroomDao().getById(1));
//        timeTableWithSubgroup.setDay(DayOfWeek.MONDAY);
//        timeTableWithSubgroup.setStudentCommunity(new GroupDao().getById(495).getSubgroups().get(0));
//        timeTableWithSubgroup.setSubject(new JdbcSubjectDao().getById(121));
//        timeTableWithSubgroup.setOddnessOfWeek(OddnessOfWeek.ODD);
//        timeTableWithSubgroup.setPair(Pair.FIRST);
//        timeTableWithSubgroup.setTeacher(new Teacher());

//        timeTableWithStream=new TimeTable();
//        timeTableWithStream.setClassroom(new ClassroomDao().getById(1));
//        timeTableWithStream.setDay(DayOfWeek.MONDAY);
//        timeTableWithStream.setStudentCommunity(new GroupDao().getById(390).getSubgroups().get(0));
//        timeTableWithStream.setSubject(new JdbcSubjectDao().getById(121));
//        timeTableWithStream.setOddnessOfWeek(OddnessOfWeek.ODD);
//        timeTableWithStream.setPair(Pair.FIRST);
//        timeTableWithStream.setTeacher(new Teacher());
    }

    @Test
    public void testGetAll() throws Exception {
        List<TimeTable> all = tableDao.getAll();
    }

    @Test
    public void testGetById() throws Exception {
        TimeTable byId = tableDao.getById(28);
        System.out.println(byId);
    }

    @Test
    public void testUpdate() throws Exception {
        TimeTableManager timeTableManager=new TimeTableManager();
//        timeTableManager.isStudentCommunityFreeNow(new GroupDao().getById(1061));
    }

    @Test
    public void testDelete() throws Exception {
        System.out.printf("%s klkl;","gdgd");
        Scanner scanner=new Scanner(System.in);
        float v = scanner.nextFloat();
    }

    @Test
    public void testAdd() throws Exception {

        tableDao.add(timeTable);
    }

    @Test
    public void testGetTimetable() throws Exception {
        List<String> strings=new ArrayList<>();
    }

}