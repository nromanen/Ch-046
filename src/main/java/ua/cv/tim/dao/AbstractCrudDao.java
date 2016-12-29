package ua.cv.tim.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by vyach on 29.12.2016.
 */
public abstract class AbstractCrudDao<E> implements CrudDao<E> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void add(E entity) {
		getCurrentSession().save(entity);
	}

	@Override
	public void update(E entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public void delete(E entity) {
		getCurrentSession().delete(entity);
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
