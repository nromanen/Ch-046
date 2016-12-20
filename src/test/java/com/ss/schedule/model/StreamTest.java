package com.ss.schedule.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.institute.Util;
import com.ss.schedule.io.InputOutputJson;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.ss.schedule.model.SubjectType.LECTURE;

/**
 * Created by oleg on 27.11.16.
 */
public class StreamTest {

    private Util FCS;

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


        FCS=new Util("Util of computer science",groups,subjects,teachers,classrooms);
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



}