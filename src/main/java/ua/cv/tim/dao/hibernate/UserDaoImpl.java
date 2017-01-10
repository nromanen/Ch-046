package ua.cv.tim.dao.hibernate;


import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.model.User;

/**
 * Created by rmochetc on 03.01.2017.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {


}
