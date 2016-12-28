package com.ss.schedule.service;

import com.ss.schedule.dao.hibernate.GroupDao;
import com.ss.schedule.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vyach on 01.12.2016.
 */

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao<Group> groupDao;

	@Override
	public void addGroup(Group group) {
		groupDao.add(group);
	}

	@Override
	public void updateGroup(Group group) {
		groupDao.update(group);
	}

	@Override
	public void deleteGroup(Group group) {
		groupDao.delete(group);
	}

	@Override
	public Group getGroupById(long groupId) {
		return groupDao.getById(groupId);
	}

	@Override
	public List<Group> getAllGroups() {
		return groupDao.getAll();
	}

	@Override
	public List<Group> getGroupsByCourse(int course) {
		return groupDao.getGroupsByCourse(course);
	}

	@Override
	public List<Group> getGroupsBySubjectId(long subjectId) {
		return groupDao.getGroupsBySubjectId(subjectId);
	}

	@Override
	public Group getGroupByName(String groupName) {
		return groupDao.getGroupByName(groupName);
	}
}
