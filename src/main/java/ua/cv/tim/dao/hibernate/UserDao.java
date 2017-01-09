package ua.cv.tim.dao.hibernate;

import ua.cv.tim.dao.CrudDao;
import ua.cv.tim.model.User;

import java.util.List;

/**
 * Created by vyach on 29.12.2016.
 */
public interface UserDao extends CrudDao<User> {
        long getCount();
        User getWithRolesById(String id);
        List<User> getAll();
        List<User> getAllWithRoles();
}
