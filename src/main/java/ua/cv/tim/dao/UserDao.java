package ua.cv.tim.dao;

import ua.cv.tim.model.User;

import java.util.List;

/**
 * Created by vyach on 29.12.2016.
 */
public interface UserDao extends CrudDao<User> {

	long getCount();

	User getWithRolesById(String id);

	List<User> getAll();

	User getById(String id);

	List<User> getAllWithRoles();

	boolean isUserUnique(String username, String uuid);

	User getByMail(String mail, String uuid);

	List<User> getUsersByAlliance(String allianceName);

	User getByMail(String mail);

	User getUserByUsername(String username);

	User getUserWithAlliance(String username);

}
