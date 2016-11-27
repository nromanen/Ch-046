package com.ss.schedule.institute;

import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rmochetc on 24.11.2016.
 */
public class ClassroomManagerTest {

    private ClassroomManager classroomManager = new ClassroomManager();
    private Group group = new Group();
    private Subject subject = new Subject();
    private List<Classroom> exspectedList = new ArrayList<>();

    @BeforeMethod
    public void setUp() throws Exception {

        List<SubjectType> types1 = new ArrayList<>();
        types1.add(SubjectType.LAB);

        List<SubjectType> types2 = new ArrayList<>();
        types2.add(SubjectType.LECTURE);
        types2.add(SubjectType.PRACTICE);

        List<SubjectType> types3 = new ArrayList<>();
        types3.add(SubjectType.LECTURE);

        Classroom classroom1 = new Classroom("001", 15, types1);
        Classroom classroom2 = new Classroom("002", 150, types2, "with proektor");
        Classroom classroom3 = new Classroom("003", 30, types3);

        List<Classroom> classrooms= new ArrayList<>();
        classrooms.add(classroom1);
        classrooms.add(classroom2);
        classrooms.add(classroom3);

        Faculty.setClassrooms(classrooms);

        group.setCount(25);
        subject.setType(SubjectType.LECTURE);
        exspectedList.add(classroom2);
        exspectedList.add(classroom3);

        Collections.sort(exspectedList);
    }

    @Test
    public void testGetListOfAvailableRooms() throws Exception {
        Assert.assertTrue(exspectedList.equals(classroomManager.getListOfAvailableRooms(subject,group)));
    }
}