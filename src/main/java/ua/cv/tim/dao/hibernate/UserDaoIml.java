package ua.cv.tim.dao.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.model.User;

import java.util.List;

/**
 * Created by Oleg on 04.01.2017.
 */
@Repository
public class UserDaoIml extends AbstractCrudDao<User> implements UserDao {


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
    public User getById(String id) {
        return  getCurrentSession().get(User.class,id);
    }

    @Override
    public List<User> getAllWithRoles() {
        List<User> list = getCurrentSession().createCriteria(User.class).list();
        Hibernate.initialize(list.get(0).getRoles());
        return list;
    }

    @Override
    public User getUserByUsername(String username) {
        String request = "select u from User u where u.login = :login";
        org.hibernate.query.Query<User> query = getCurrentSession().createQuery(request);
        query.setParameter("login", username);
        return query.getSingleResult();
    }

    @Override
    public User getByMail(String mail) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM User WHERE email=:mail");
        query.setParameter("mail", mail);
        User user = (User) query.getSingleResult();
        return user;

    }
}
