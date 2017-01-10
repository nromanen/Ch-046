package ua.cv.tim.dao;

import ua.cv.tim.model.User;

/**
 * Created by vyach on 29.12.2016.
 */

public interface UserDao extends CrudDao<User> {

    User getById(String uuid);

	User getUserByUsername(String username);
}
