package ua.cv.tim.dao;

import ua.cv.tim.dao.CrudDao;
import java.util.List;

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
	User getUserByUsername(String username,  String uuid);
	User getByMail(String mail, String uuid);
	List<User> getUsersByAlliance(String allianceName);
	public User getByMail(String mail);
	public User getUserByUsername(String username);


}
