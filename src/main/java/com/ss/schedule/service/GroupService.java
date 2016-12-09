package com.ss.schedule.service;

import com.ss.schedule.dao.jdbc.JdbcGroupDao;
import com.ss.schedule.dao.jdbc.JdbcSubjectDao;
import com.ss.schedule.model.Group;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyach on 01.12.2016.
 */
public class GroupService {

	private JdbcGroupDao groupDao;
	private JdbcSubjectDao subjectDao;

	public GroupService(String propertiesFilePath) throws SQLException {
		this.groupDao = new JdbcGroupDao(propertiesFilePath);
		this.subjectDao = new JdbcSubjectDao(propertiesFilePath);
	}

	public Group addGroup(Group group) throws SQLException {
		return groupDao.add(group);
	}

	public Group updateGroup(Group group) throws SQLException {
		Group updatedGroup = groupDao.update(group);
		updatedGroup.setSubjects(subjectDao.getSubjectsByGroupId(updatedGroup.getId()));
		return updatedGroup;
	}

	public boolean deleteGroup(long groupId) throws SQLException {
		return groupDao.delete(groupId);
	}

	public Group getGroupById(long groupId) throws SQLException {
		Group group = groupDao.getById(groupId);
		group.setSubjects(subjectDao.getSubjectsByGroupId(groupId));
		return group;
	}

	public List<Group> getAllGroups() throws SQLException {
		List<Group> groups = groupDao.getAll();
		setSubjectsIntoGroups(groups);

		return groups;
	}

	public List<Group> getGroupsByCourse(int course) throws SQLException {
		List<Group> groups = groupDao.getGroupsByCourse(course);
		for (int i = 0; i < groups.size(); i++) {
			groups.get(i).setSubjects(subjectDao.getSubjectsByGroupId(groups.get(i).getId()));
		}
		return groups;
	}

	public List<Group> getGroupsBySubjectId(long subjectId) throws SQLException {
		List<Group> groups = groupDao.getGroupsBySubjectId(subjectId);
		setSubjectsIntoGroups(groups);

		return groups;
	}

	private void setSubjectsIntoGroups(List<Group> groups) throws SQLException {
		for (Group group : groups) {
			group.setSubjects(subjectDao.getSubjectsByGroupId(group.getId()));
		}
	}
}
