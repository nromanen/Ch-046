package ua.cv.tim.dao;

/**
 * Created by vyach on 29.12.2016.
 */
public interface CrudDao<E> {
	void add(E entity);
	void update(E entity);
	void delete(E entity);

}
