package com.ss.schedule.dao.hibernate;

import com.ss.schedule.dao.BaseDao;

import java.util.List;

/**
 * Created by vyach on 26.12.2016.
 */
public interface GroupDao<E> extends BaseDao<E> {

	E getById(long id);

	List<E> getAll();

	List<E> getGroupsBySubjectId(long subjectId);

	List<E> getGroupsByCourse(int course);

	E getGroupByName(String groupName);
}
