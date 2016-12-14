package com.ss.schedule.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyach on 29.11.2016.
 */
public abstract class AbstractDao<E> {

	private final String propertiesFilePath;
	private Session currentSession;
	private Transaction currentTransaction;
	private SessionFactory sessionFactory;

	public AbstractDao(String propertiesFilePath) throws SQLException {
		this.propertiesFilePath = propertiesFilePath;
	}

	public abstract void add(E entity) throws SQLException;

	public abstract void update(E entity) throws SQLException;

	public abstract void delete(E entity) throws SQLException;

	public abstract E getById(long id) throws SQLException;

	public abstract List<E> getAll() throws SQLException;

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionWithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	private SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure(propertiesFilePath);
			sessionFactory = configuration.buildSessionFactory();
		}
		return sessionFactory;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionAndCommitTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	public Session getCurrentSession() {
		return currentSession;
	}
}
