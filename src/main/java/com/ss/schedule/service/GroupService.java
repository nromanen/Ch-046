package com.ss.schedule.service;

import com.ss.schedule.model.Group;

import java.util.List;

/**
 * Created by vyach on 26.12.2016.
 */
public interface GroupService {

	void addGroup(Group group);

	void updateGroup(Group group);

	void deleteGroup(Group group);

	Group getGroupById(long groupId);

	List<Group> getAllGroups();

	List<Group> getGroupsByCourse(int course);

	List<Group> getGroupsBySubjectId(long subjectId);

	Group getGroupByName(String groupName);
}
