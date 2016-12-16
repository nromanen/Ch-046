package com.ss.schedule.service;

import com.ss.schedule.dao.hibernate.HSubjectDao;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyach on 01.12.2016.
 */
public class SubjectService {

	private HSubjectDao subjectDao;

	public SubjectService(String propertiesFilePath) throws SQLException {
		this.subjectDao = new HSubjectDao(propertiesFilePath);
	}

	public void addSubject(Subject subject) throws SQLException {
		subjectDao.openCurrentSessionWithTransaction();
		subjectDao.add(subject);
		subjectDao.closeCurrentSessionAndCommitTransaction();
	}

	public void updateSubject(Subject subject) throws SQLException {
		subjectDao.openCurrentSessionWithTransaction();
		subjectDao.update(subject);
		subjectDao.closeCurrentSessionAndCommitTransaction();
	}

	public void deleteSubject(Subject subject) throws SQLException {
		subjectDao.openCurrentSessionWithTransaction();
		subjectDao.delete(subject);
		subjectDao.closeCurrentSessionAndCommitTransaction();
	}

	public Subject getSubjectById(long subjectId) throws SQLException {
		subjectDao.openCurrentSession();
		Subject subject = subjectDao.getById(subjectId);
		subjectDao.closeCurrentSession();
		return subject;
	}

	public List<Subject> getAllSubjects() throws SQLException {
		subjectDao.openCurrentSession();
		List<Subject> subjects = subjectDao.getAll();
		subjectDao.closeCurrentSession();
		return subjects;
	}

	public List<Subject> getSubjectsByCourse(int course) throws SQLException {
		subjectDao.openCurrentSession();
		List<Subject> subjects = subjectDao.getSubjectsByCourse(course);
		subjectDao.closeCurrentSession();
		return subjects;
	}

	public Subject getSubject(String name, SubjectType type, int course) throws SQLException {
		subjectDao.openCurrentSession();
		Subject subject = subjectDao.getSubject(name, type, course);
		subjectDao.closeCurrentSession();
		return subject;
	}

	public List<Subject> getUnusedSubjects() throws SQLException {
		subjectDao.openCurrentSession();
		List<Subject> subjects = subjectDao.getUnusedSubjects();
		subjectDao.closeCurrentSession();
		return subjects;
	}

	public List<Subject> getSubjectsByGroupId(long groupId) throws SQLException {
		subjectDao.openCurrentSession();
		List<Subject> subjects = subjectDao.getSubjectsByGroupId(groupId);
		subjectDao.closeCurrentSession();
		return subjects;
	}
}
