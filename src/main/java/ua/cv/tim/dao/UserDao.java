package ua.cv.tim.dao;

import ua.cv.tim.model.User;

import java.util.List;

/**
 * Created by vyach on 29.12.2016.
 */
public interface UserDao extends CrudDao<User> {

	User getWithRolesById(String id);

	List<User> getAll();

	User getById(String id);

	List<User> getAllWithRoles();

	List<User> getUsersByAlliance(String allianceName);

	User getByMail(String mail);

	User getUserByUsername(String username);

	User getUserWithAlliance(String username);

	User getFullUserByUsername(String username);
}
