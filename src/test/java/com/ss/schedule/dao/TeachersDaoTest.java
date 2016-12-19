package com.ss.schedule.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.Teacher;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by oleg on 15.12.16.
 */
public class TeachersDaoTest {
    @Test
    public void testAddAll() throws Exception {
        List<Teacher> teachers = new InputOutputJson<List<Teacher>>(new TypeReference<List<Teacher>>() {
        }).readFromFile("/home/oleg/Рабочий стол/Ch-046 (copy) (другая копия)/src/test/resources/teacher.json");
        for(Teacher teacher: teachers){
            teacher.setSubjects(new JdbcSubjectDao().getAll());
        }
        new TeachersDao().addAll(teachers);
    }


}