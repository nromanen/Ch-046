package com.ss.schedule.dao;

import java.sql.Connection;
import java.util.List;

import com.ss.schedule.dbutil.DBConnector;

public abstract class AbstractDao<E> {

	protected  Connection connection;

	public AbstractDao() {
		connection = DBConnector.getConnection();
	}

	// get all entitis from DB
	public abstract List<E> getAll();

	// get entity from DB bty id
	public abstract E getById(long id);

	// update entity in DB
	public abstract E update(E entity);

	// delete entity from DB
	public abstract boolean delete(long id);

	// add new entity in DB
	public abstract E add(E entity);
}
