package com.ss.schedule.service;

import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.util.List;

/**
 * Created by vyach on 26.12.2016.
 */
public interface SubjectService {

	void addSubject(Subject subject);

	void updateSubject(Subject subject);

	void deleteSubject(Subject subject);

	Subject getSubjectById(long subjectId);

	List<Subject> getAllSubjects();

	List<Subject> getSubjectsByCourse(int course);

	Subject getSubject(String name, SubjectType type, int course);

	List<Subject> getUnusedSubjects();

	List<Subject> getSubjectsByGroupId(long groupId);
}
