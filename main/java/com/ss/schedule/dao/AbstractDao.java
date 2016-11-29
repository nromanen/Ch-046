package com.ss.schedule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.ss.schedule.dbutil.DBConnector;

public abstract class AbstractDao<E, K> {

	protected Connection connection;

	public AbstractDao() {
		connection = DBConnector.getConnection();
	}

	// get all entitis from DB
	public abstract List<E> getAll();

	// get entity from DB bty id
	public abstract E getById(K id);

	// update entity in DB
	public abstract E update(E entity);

	// delete entity from DB
	public abstract boolean delete(K id);

	// add new entity in DB
	public abstract boolean add(E entity);

	public Statement getStatement() {
		Statement s = null;
		try {
			s = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	// Get PrepareStatment
	public PreparedStatement getPrepareStatement(String sql) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	// Close PrepareStatement
	public void closePrepareStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
