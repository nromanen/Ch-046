package com.ss.schedule.dao.hibernate;

import com.ss.schedule.dao.AbstractDao;
import com.ss.schedule.model.Group;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vyach on 28.11.2016.
 */

@Repository("groupDao")
public class HGroupDao extends AbstractDao<Group> implements GroupDao<Group> {

	@Override
	public Group getById(long id) {
		String request = "SELECT g FROM Group g WHERE g.id = :id";
		Session session = getCurrentSession();
		Query<Group> query = session.createQuery(request);
		query.setParameter("id", id);
		return query.uniqueResult();
	}

	@Override
	public List<Group> getAll() {
		String request = "SELECT g FROM Group g ORDER BY g.name ASC";
		Session session = getCurrentSession();
		Query<Group> query = session.createQuery(request);
		return query.getResultList();
	}

	@Override
	public List<Group> getGroupsBySubjectId(long subjectId) {
		String request = "SELECT g FROM Group g " +
				"JOIN g.subjects s " +
				"WHERE s.id = :id " +
				"ORDER BY g.name";
		Session session = getCurrentSession();
		Query<Group> query = session.createQuery(request);
		query.setParameter("id", subjectId);
		return query.getResultList();
	}

	@Override
	public List<Group> getGroupsByCourse(int course) {
		String request = "SELECT g FROM Group g " +
				"WHERE g.name LIKE '" + course + "%' " +
				"ORDER BY g.name";
		Session session = getCurrentSession();
		Query<Group> query = session.createQuery(request);
		return query.getResultList();
	}

	@Override
	public Group getGroupByName(String groupName) {
		String request = "SELECT g FROM Group g " +
				"WHERE g.name = :name";
		Session session = getCurrentSession();
		Query<Group> query = session.createQuery(request);
		query.setParameter("name", groupName);
		return query.uniqueResult();
	}
}
