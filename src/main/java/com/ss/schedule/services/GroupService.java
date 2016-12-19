package com.ss.schedule.services;

import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;

import java.util.List;

/**
 * Created by Admin on 18.12.16.
 */
public class GroupService {

    private GroupDao groupDao = new GroupDao();

    public List<Group> getAll() {
        return groupDao.getAll();
    }

    public Group getById(long groupId) {
        return groupDao.getById(groupId);
    }

    public List<Group> getGroupsBySubject(Subject subject) {
        return groupDao.getGroupsBySubject(subject);
    }
}
