package com.ss.schedule.institute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by oleg on 27.11.16.
 */
public class FacultyTest {
    @Test
    public void testGetGroupsSubgroupsStreams() throws Exception {
        LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams = FCS.getGroupsSubgroupsStreams();
        System.out.println(groupsSubgroupsStreams);
    }

    @Test
    public void testCreateStream() throws Exception {
        List<Stream> stream = FCS.createStream(FCS.getSubjects().get(7));
        System.out.println(stream.get(0).getName());
    }

    private Faculty FCS;

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


    }

    @Test
    public void testCreateSubgroups() throws Exception {
        List<Subgroup> subgroups = FCS.createSubgroups(FCS.getSubjects().get(1));
        assertEquals(subgroups.size(),5);
    }

}