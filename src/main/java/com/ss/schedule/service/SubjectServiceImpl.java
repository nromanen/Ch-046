package com.ss.schedule.service;

import com.ss.schedule.dao.hibernate.SubjectDao;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vyach on 01.12.2016.
 */

@Service("subjectService")
@Transactional
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectDao<Subject> subjectDao;

	@Override
	public void addSubject(Subject subject) {
		subjectDao.add(subject);
	}

	@Override
	public void updateSubject(Subject subject) {
		subjectDao.update(subject);
	}

	@Override
	public void deleteSubject(Subject subject) {
		subjectDao.delete(subject);
	}

	@Override
	public Subject getSubjectById(long subjectId) {
		return subjectDao.getById(subjectId);
	}

	@Override
	public List<Subject> getAllSubjects() {
		return subjectDao.getAll();
	}

	@Override
	public List<Subject> getSubjectsByCourse(int course) {
		return subjectDao.getSubjectsByCourse(course);
	}

	@Override
	public Subject getSubject(String name, SubjectType type, int course) {
		return subjectDao.getSubject(name, type, course);
	}

	@Override
	public List<Subject> getUnusedSubjects() {
		return subjectDao.getUnusedSubjects();
	}

	@Override
	public List<Subject> getSubjectsByGroupId(long groupId) {
		return subjectDao.getSubjectsByGroupId(groupId);
	}
}
