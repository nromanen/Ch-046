package com.ss.schedule.service;

import com.ss.schedule.dao.jdbc.JdbcGroupDao;
import com.ss.schedule.dao.jdbc.JdbcSubjectDao;
import com.ss.schedule.model.Subject;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyach on 01.12.2016.
 */
public class SubjectService {

	private JdbcSubjectDao subjectDao;
	private JdbcGroupDao groupDao;

	public SubjectService(String propertiesFilePath) throws SQLException {
		this.subjectDao = new JdbcSubjectDao(propertiesFilePath);
		this.groupDao = new JdbcGroupDao(propertiesFilePath);
	}

	public Subject addSubject(Subject subject) throws SQLException {
		return subjectDao.add(subject);
	}

	public Subject updateSubject(Subject subject) throws SQLException {
		return subjectDao.update(subject);
	}

	public boolean deleteSubject(long subjectId) throws SQLException {
		return subjectDao.delete(subjectId);
	}

	public Subject getSubjectById(long subjectId) throws SQLException {
		return subjectDao.getById(subjectId);
	}

	public List<Subject> getAllSubjects() throws SQLException {
		return subjectDao.getAll();
	}

	public List<Subject> getSubjectsByCourse(int course) throws SQLException {
		return subjectDao.getSubjectsByCourse(course);
	}

	public Subject getSubject(String name, String type, int course) throws SQLException {
		return subjectDao.getSubject(name, type, course);
	}

	public List<Subject> getUnusedSubjects() throws SQLException {
		return subjectDao.getUnusedSubjects();
	}
}
