package com.ss.schedule.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.institute.Faculty;
import com.ss.schedule.io.InputOutputJson;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.ss.schedule.model.SubjectType.LECTURE;
import static org.testng.Assert.*;

/**
 * Created by oleg on 27.11.16.
 */
public class StreamTest {

    private Faculty FCS;

    @DataProvider(name = "streamProvider")
    public Object[][] streamProvider(){
        return new Object[][]{
                {FCS.getGroupsSubgroupsStreams().get(FCS.getSubjects().get(0)),"hjk"}
        };
    }

    @BeforeTest
    public void prepareForTest(){
        InputOutputJson<ArrayList<Classroom>> iojClassRooms = new InputOutputJson<>(
                new TypeReference<ArrayList<Classroom>>() {
                });
        InputOutputJson<List<Group>>iojGroups=new InputOutputJson<>(new TypeReference<List<Group>>() {
        });
        InputOutputJson<List<Subject>>iojSubjects=new InputOutputJson<>(new TypeReference<List<Subject>>() {
        });
        InputOutputJson<List<Teacher>>iojTeachers=new InputOutputJson<>(new TypeReference<List<Teacher>>() {
        });

        List<Classroom> classrooms = iojClassRooms.readFromFile("room.json");
        List<Group> groups = iojGroups.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/OneDrive/Education3/" +
                "JavaFx/TimeTable1/Ch-046 (copy)/src/main/resourses/group.json");
        List<Subject> subjects = iojSubjects.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/" +
                "OneDrive/Education3/JavaFx/TimeTable1/Ch-046 (copy)/src/main/resourses/subject.json");
        List<Teacher> teachers=iojTeachers.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/OneDrive/" +
                "Education3/JavaFx/TimeTable1/Ch-046 (copy)/src/main/resourses/teacher.json");


        FCS=new Faculty("Faculty of computer science",groups,subjects,teachers,classrooms);
        for (Group group:FCS.getGroups())
            group.setSubgroups(new ArrayList<>());
        FCS.createSubgroups(FCS.getSubjects().get(1));
        FCS.getGroupsSubgroupsStreams();
    }

    @Test
    public void testCanBeAddedToTimetableManager() throws Exception {

    }

    @Test(dataProvider = "streamProvider")
    public void testIsPresentOrEquals(StudentCommunity studentCommunity) throws Exception {

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
        //tt1.setStudentCommunity(FCS.getGroups().get(0));
        tt1.setStudentCommunity(FCS.getGroupsSubgroupsStreams().get(FCS.getSubjects().get(5)).get(0));
        tt1.setDay(DayOfWeek.MONDAY);
        tt1.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt1.setPair(Pair.FIRST);
        tt1.setSubject(english);
        tt1.setTeacher(new Teacher());
        tt1.setClassroom(FCS.getClassrooms().get(0));
        tt1.setTeacher(FCS.getTeachers().get(0));

        TimeTable tt2=new TimeTable();
        tt2.setDay(DayOfWeek.MONDAY);
        tt2.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt2.setPair(Pair.FIRST);
        tt2.setSubject(spanish);
        tt2.setStudentCommunity(new Stream());
        tt2.setTeacher(new Teacher());
        tt2.setClassroom(FCS.getClassrooms().get(3));
        tt2.setTeacher(FCS.getTeachers().get(0));

        TimeTable tt3=new TimeTable();
        tt3.setDay(DayOfWeek.MONDAY);
        tt3.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt3.setPair(Pair.SIXTH);
        tt3.setSubject(chineese);
        tt3.setStudentCommunity(new Stream());
        tt3.setTeacher(new Teacher());
        tt3.setClassroom(FCS.getClassrooms().get(3));
        tt3.setTeacher(FCS.getTeachers().get(0));


        TimeTable tt4=new TimeTable();
        tt4.setDay(DayOfWeek.MONDAY);
        tt4.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt4.setPair(Pair.SEVENTH);
        tt4.setSubject(math);
        tt4.setStudentCommunity(new Stream());
        tt4.setTeacher(new Teacher());
        tt4.setClassroom(FCS.getClassrooms().get(3));
        tt4.setTeacher(FCS.getTeachers().get(0));

        List<TimeTable> timeTables=new ArrayList<>();
        timeTables.add(tt1);
        timeTables.add(tt2);
        timeTables.add(tt3);
        timeTables.add(tt4);

        return timeTables;


    }


}