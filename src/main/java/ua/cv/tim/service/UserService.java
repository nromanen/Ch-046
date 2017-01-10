package ua.cv.tim.service;

import ua.cv.tim.model.User;

/**
 * Created by vyach on 03.01.2017.
 */
public interface UserService {

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

	User getUserByUsername(String username);
}
