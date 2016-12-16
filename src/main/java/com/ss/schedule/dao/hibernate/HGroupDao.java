package com.ss.schedule.dao.hibernate;

import com.ss.schedule.dao.AbstractDao;
import com.ss.schedule.model.Group;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyach on 28.11.2016.
 */
public class HGroupDao extends AbstractDao<Group> {

	public HGroupDao(String propertiesFilePath) throws SQLException {
		super(propertiesFilePath);
	}

	@Override
	public void add(Group group) throws SQLException {
		getCurrentSession().save(group);
	}

	@Override
	public void update(Group group) throws SQLException {
		getCurrentSession().update(group);
	}

	@Override
	public void delete(Group group) throws SQLException {
		getCurrentSession().delete(group);
	}

	public Group getById(long id) throws SQLException {
		String request = "SELECT g FROM Group g WHERE g.id = :id";
		Session session = getCurrentSession();
		Query<Group> query = session.createQuery(request);
		query.setParameter("id", id);
		return query.uniqueResult();
	}

	@Override
	public List<Group> getAll() throws SQLException {
		String request = "SELECT g FROM Group g ORDER BY g.name ASC";
		Session session = getCurrentSession();
		Query<Group> query = session.createQuery(request);
		return query.list();
	}

	public List<Group> getGroupsBySubjectId(long subjectId) throws SQLException {
		String request = "SELECT g FROM Group g " +
				"JOIN g.subjects s " +
				"WHERE s.id = :id " +
				"ORDER BY g.name";
		Session session = getCurrentSession();
		Query<Group> query = session.createQuery(request);
		query.setParameter("id", subjectId);
		return query.list();
	}

	public List<Group> getGroupsByCourse(int course) throws SQLException {
		String request = "SELECT g FROM Group g " +
				"WHERE g.name LIKE '" + course + "%' " +
				"ORDER BY g.name";
		Session session = getCurrentSession();
		Query<Group> query = session.createQuery(request);
		return query.list();
	}

	public Group getGroupByName(String groupName) {
		String request = "SELECT g FROM Group g " +
				"WHERE g.name = :name";
		Session session = getCurrentSession();
		Query<Group> query = session.createQuery(request);
		query.setParameter("name", groupName);
		return query.uniqueResult();
	}
}
