package com.ss.schedule.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.io.InputOutput;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.Classroom;
import org.junit.Before;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by oleg on 16.12.16.
 */
public class ClassroomDaoTest {
    @Test
    public void testGetByTypeAndCapacity() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testGetEntityIdByName() throws Exception {
        InputOutputJson<List<Classroom>> inputOutputJson=new InputOutputJson<>(new TypeReference<List<Classroom>>() {
        });
        List<Classroom> classrooms = inputOutputJson.readFromFile("/home/oleg/Рабочий стол/" +
                "Ch-046 (copy) (другая копия)/src/test/resources/group1.json");
//        for (Classroom classroom:classrooms)
//        new ClassroomDao().add(classroom);
        List<Classroom> classrooms1=new ClassroomDao().getAll();
    }

    @BeforeTest
    void setUp(){
         }

}