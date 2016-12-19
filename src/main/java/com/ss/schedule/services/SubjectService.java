package com.ss.schedule.services;

import com.ss.schedule.dao.SubjectDao;
import com.ss.schedule.model.Subject;

/**
 * Created by Admin on 18.12.16.
 */
public class SubjectService {

    private SubjectDao subjectDao = new SubjectDao();
    public Subject getById(long subjectId) {
        return subjectDao.getById(subjectId);
    }
}
