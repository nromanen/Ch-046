package com.ss.schedule.institute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.JdbcSubjectDao;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by oleg on 27.11.16.
 */
public class UtilTest {
    @Test
    public void testGetName() throws Exception {
        System.out.printf("%s","gdgd");
    }

    @Test
    public void testSetName() throws Exception {

    }

    @Test
    public void testGetGroups() throws Exception {

    }

    @Test
    public void testSetGroups() throws Exception {

    }

    @Test
    public void testGetSubjects() throws Exception {

    }

    @Test
    public void testSetSubjects() throws Exception {

    }

    @Test
    public void testGetTeachers() throws Exception {

    }

    @Test
    public void testSetTeachers() throws Exception {

    }

    @Test
    public void testGetClassrooms() throws Exception {

    }

    @Test
    public void testSetClassrooms() throws Exception {

    }

    @Test
    public void testGetSubgroupsBySubject() throws Exception {
        FCS.getGroupsSubgroupsStreams();
        List<Subgroup> subgroupsBySubject = FCS.getSubgroupsBySubject(FCS.getSubjects().get(1), FCS.getGroups().get(0));
    }

    @Test
    public void testAddListOfSubjects() throws Exception {

    }

    @Test
    public void testAddSubject() throws Exception {

    }

    @Test
    public void testDeleteSubject() throws Exception {

    }

    @Test
    public void testGetUnusedSubjects() throws Exception {

    }

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

    private Util FCS;

    @BeforeTest
    public void prepareForTest(){
        InputOutputJson<List<Teacher>>iojTeachers=new InputOutputJson<>(new TypeReference<List<Teacher>>() {
        });

        List<Classroom> classrooms = new ClassroomDao().getAll();
        List<Group> groups = new GroupDao().getAll();
        List<Subject> subjects = new JdbcSubjectDao().getAll();
        List<Teacher> teachers=iojTeachers.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/OneDrive/" +
                "Education3/JavaFx/TimeTable1/Ch-046 (copy)/src/main/resourses/teacher.json");

        FCS=new Util("Util of computer science",groups,subjects,teachers,classrooms);


    }

    @Test
    public void testCreateSubgroups() throws Exception {
        List<Subgroup> subgroups = FCS.createSubgroups(FCS.getSubjects().get(1));
        assertEquals(subgroups.size(),5);
    }

}