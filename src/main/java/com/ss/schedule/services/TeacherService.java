package com.ss.schedule.services;

import com.ss.schedule.dao.TeacherDao;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.Teacher;

import java.util.List;

/**
 * Created by Admin on 18.12.16.
 */
public class TeacherService {

    TeacherDao teacherDao = new TeacherDao();

    public Teacher getById(long teacherId) {
        return teacherDao.getById(teacherId);
    }

    public List<Teacher> getAll() {
        return teacherDao.getAll();
    }

    public List<Teacher> getTeachersBySubject(Subject subject) {
        return teacherDao.getTeachersBySubject(subject);
    }
}
