package com.ss.schedule.dao.hibernate;

import com.ss.schedule.dao.AbstractDao;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vyach on 28.11.2016.
 */

@Repository("subjectDao")
public class HSubjectDao extends AbstractDao<Subject> implements SubjectDao<Subject> {

	@Override
	public Subject getById(long id) {
		String request = "SELECT s FROM Subject s " +
				"WHERE s.id = :id";
		Session session = getCurrentSession();
		Query<Subject> query = session.createQuery(request);
		query.setParameter("id", id);
		return query.uniqueResult();
	}

	@Override
	public List<Subject> getAll() {
		String request = "SELECT s FROM Subject s " +
				"ORDER BY s.course ASC, s.name ASC";
		Session session = getCurrentSession();
		Query<Subject> query = session.createQuery(request);
		return query.getResultList();
	}

	@Override
	public List<Subject> getSubjectsByGroupId(long groupId) {
		String request = "SELECT s FROM Group g " +
				"JOIN g.subjects s " +
				"WHERE g.id = :id " +
				"ORDER BY s.name";
		Session session = getCurrentSession();
		Query<Subject> query = session.createQuery(request);
		query.setParameter("id", groupId);
		return query.getResultList();
	}

	@Override
	public List<Subject> getSubjectsByCourse(int course) {
		String request = "SELECT s FROM Subject s " +
				"WHERE s.course = :course " +
				"ORDER BY s.name";
		Session session = getCurrentSession();
		Query<Subject> query = session.createQuery(request);
		query.setParameter("course", course);
		return query.getResultList();
	}

	@Override
	public Subject getSubject(String name, SubjectType type, int course) {
		String request = "SELECT s FROM Subject s " +
				"WHERE lower(s.name) = :name AND s.type = :type AND s.course = :course";
		Session session = getCurrentSession();
		Query<Subject> query = session.createQuery(request);
		query.setParameter("name", name.toLowerCase());
		query.setParameter("type", type);
		query.setParameter("course", course);
		return query.uniqueResult();
	}

	@Override
	public List<Subject> getUnusedSubjects() {
		String request = "SELECT s FROM Group g " +
				"RIGHT JOIN g.subjects s " +
				"WHERE g IS NULL " +
				"ORDER BY s.course, s.name";
		Session session = getCurrentSession();
		Query<Subject> query = session.createQuery(request);
		return query.getResultList();
	}
}
