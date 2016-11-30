package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyach on 29.11.2016.
 */
public abstract class AbstractDao<E> {

	protected Connection connection;

	public AbstractDao() throws SQLException {
		connection = DBConnector.getConnection();
	}

	public abstract E add(E entity) throws SQLException;

	public abstract E update(E entity) throws SQLException;

	public abstract boolean delete(long id) throws SQLException;

	public abstract E getById(long id) throws SQLException;

	public abstract List<E> getAll() throws SQLException;
}
