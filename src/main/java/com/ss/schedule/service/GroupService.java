package com.ss.schedule.service;

import com.ss.schedule.dao.hibernate.HGroupDao;
import com.ss.schedule.dao.hibernate.HSubjectDao;
import com.ss.schedule.model.Group;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyach on 01.12.2016.
 */
public class GroupService {

	private HGroupDao groupDao;
	private HSubjectDao subjectDao;

	public GroupService(String propertiesFilePath) throws SQLException {
		this.groupDao = new HGroupDao(propertiesFilePath);
		this.subjectDao = new HSubjectDao(propertiesFilePath);
	}

	public void addGroup(Group group) throws SQLException {
		groupDao.openCurrentSessionWithTransaction();
		groupDao.add(group);
		groupDao.closeCurrentSessionAndCommitTransaction();
	}

	public void updateGroup(Group group) throws SQLException {
		groupDao.openCurrentSessionWithTransaction();
		groupDao.update(group);
		groupDao.closeCurrentSessionAndCommitTransaction();
	}

	public void deleteGroup(Group group) throws SQLException {
		groupDao.openCurrentSessionWithTransaction();
		groupDao.delete(group);
		groupDao.closeCurrentSessionAndCommitTransaction();
	}

	public Group getGroupById(long groupId) throws SQLException {
		groupDao.openCurrentSession();
		Group group = groupDao.getById(groupId);
		groupDao.closeCurrentSession();
		return group;
	}

	public List<Group> getAllGroups() throws SQLException {
		groupDao.openCurrentSession();
		List<Group> groups = groupDao.getAll();
		groupDao.closeCurrentSession();
		return groups;
	}

	public List<Group> getGroupsByCourse(int course) throws SQLException {
		groupDao.openCurrentSession();
		List<Group> groups = groupDao.getGroupsByCourse(course);
		groupDao.closeCurrentSession();
		return groups;
	}

	public List<Group> getGroupsBySubjectId(long subjectId) throws SQLException {
		groupDao.openCurrentSession();
		List<Group> groups = groupDao.getGroupsBySubjectId(subjectId);
		groupDao.closeCurrentSession();
		return groups;
	}
}
