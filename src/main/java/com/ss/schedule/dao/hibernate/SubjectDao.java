package com.ss.schedule.dao.hibernate;

import com.ss.schedule.dao.BaseDao;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.util.List;

/**
 * Created by vyach on 26.12.2016.
 */

public interface SubjectDao<E> extends BaseDao<E> {

	E getById(long id);

	List<E> getAll();

	List<E> getSubjectsByGroupId(long groupId);

	List<E> getSubjectsByCourse(int course);

	Subject getSubject(String name, SubjectType type, int course);

	List<E> getUnusedSubjects();
}
