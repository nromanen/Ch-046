package com.ss.schedule.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.institute.Util;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.Subject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import static org.testng.Assert.*;

/**
 * Created by oleg on 02.12.16.
 */
public class JdbcSubjectDaoTest  {
Util util;
    JdbcSubjectDao sDao=new JdbcSubjectDao();
    List<Subject> subjects;
    @BeforeTest
    void setUp(){
        subjects=new InputOutputJson<>(new TypeReference<List<Subject>>() {
        }).readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/OneDrive/" +
                "Education3/JavaFx/TimeTable1/Ch-046 (copy) (копия)/src/test/resources/subject.json");

    }
    @Test
    public void testAdd() throws Exception {
        for (Subject subject:subjects){
            sDao.add(subject);
        }
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {
        JdbcSubjectDao subjectDao = new JdbcSubjectDao();
        Subject subject = subjectDao.getById(1);
    }

    @Test
    public void testGetAll() throws Exception {
        JdbcSubjectDao subjectDao = new JdbcSubjectDao();
        List<Subject> all = subjectDao.getAll();

        System.out.println(all);
        assertEquals(all.size(),21);
    }

    @Test
    public void testGetSubjectsByGroupId() throws Exception {

    }

}