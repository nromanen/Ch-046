package ua.cv.tim.service;

import ua.cv.tim.model.User;

import java.util.List;

/**
 * Created by vyach on 03.01.2017.
 */
public interface UserService {

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

	User getUserByUsername(String username);

	long getCount();
	User getWithRolesById(String id);
	List<User> getAllWithRoles();
	List<User> getAll();
	User getById(String id);
}
