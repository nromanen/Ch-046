package ua.cv.tim.dao;

/**
 * Created by vyach on 29.12.2016.
 */
public interface UserDao<E> extends CrudDao<E> {

	E getUserByUsername(String username);
}
