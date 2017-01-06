package ua.cv.tim.dao;

import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.hibernate.UserDao;
import ua.cv.tim.model.User;

/**
 * Created by Oleg on 04.01.2017.
 */
@Repository

public class UserDaoIml extends AbstractCrudDao<User> implements UserDao {

    public UserDaoIml(Class<User> clazz) {
        super(clazz);
    }

    @Override
    public long getCount() {
        return (long) getCurrentSession().createCriteria(User.class).setProjection(Projections.rowCount()).uniqueResult();
    }
}
