package com.ss.schedule.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by vyach on 29.11.2016.
 */

public abstract class AbstractDao<E> implements BaseDao<E> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void add(E entity) {
		getCurrentSession().save(entity);
	}

	public void update(E entity) {
		getCurrentSession().update(entity);
	}

	public void delete(E entity) {
		getCurrentSession().delete(entity);
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
