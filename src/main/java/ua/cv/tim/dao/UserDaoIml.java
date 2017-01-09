package ua.cv.tim.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.hibernate.UserDao;
import ua.cv.tim.model.User;

import java.util.List;

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

    @Override
    public User getWithRolesById(String id) {
        User user = getCurrentSession().get(User.class, id);
        Hibernate.initialize(user.getRoles());
        return user;
    }

    @Override
    public List<User> getAll() {
        return getCurrentSession().createCriteria(User.class).list();
    }

    @Override
    public List<User> getAllWithRoles() {
        List<User> list = getCurrentSession().createCriteria(User.class).list();
        Hibernate.initialize(list.get(0).getRoles());
        return list;
    }
}
