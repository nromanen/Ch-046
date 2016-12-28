package com.ss.schedule.dao;

/**
 * Created by vyach on 27.12.2016.
 */
public interface BaseDao<E> {

	void add(E entity);

	void update(E entity);

	void delete(E entity);
}
