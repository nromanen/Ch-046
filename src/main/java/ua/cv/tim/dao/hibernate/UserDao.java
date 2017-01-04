package ua.cv.tim.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.CrudDao;
import ua.cv.tim.model.User;

/**
 * Created by vyach on 29.12.2016.
 */


public interface UserDao extends CrudDao<User> {

    User getById(String uuid);

//    @Override
//    public User getById(long id) {
//        return null;
//    }
}
