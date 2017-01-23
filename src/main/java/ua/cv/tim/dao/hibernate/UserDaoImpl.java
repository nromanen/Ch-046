package ua.cv.tim.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.dao.UserDao;

/**
 * Created by rmochetc on 03.01.2017.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {


    @Override
    public long getCount() {
        return (long) getCurrentSession().createCriteria(User.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public User getWithRolesById(String id) {
        User user = getCurrentSession().get(User.class, id);
        List<Role> roles = user.getRoles();
        return user;

    }

    @Override
    public List<User> getAll() {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM User ");
        List<User> users = (List<User>) query.getResultList();
        return users;
    }

    public User getByMail(String mail, String uuid) {
        System.out.println("Dao get by email");
        System.out.println("uuid = : " + uuid);

        Session session = getCurrentSession();
        Query query = null;
        if (uuid != null) {
            System.out.println("uuid no = null : " + uuid);
            query = session.createQuery("select u FROM User u WHERE u.email=:mail and u.uuid != :uuid");
            query.setParameter("mail", mail);
            query.setParameter("uuid", uuid);
        } else {
            query = session.createQuery("select u FROM User u WHERE u.email=:mail");
            query.setParameter("mail", mail);
        }
        User user = (User) query.uniqueResult();
        System.out.println(user);
        return user;
    }

    @Override
    public User getById(String uuid) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select u FROM User u WHERE u.id=:id");
        query.setParameter("id", uuid);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public List<User> getAllWithRoles() {


        Query query = getCurrentSession().createQuery("FROM User ");
        List<User> allWithRoles = (List<User>) query.getResultList();
        for (User user : allWithRoles)
            Hibernate.initialize(user.getRoles());
        return allWithRoles;
    }

    @Override
    public User getUserByUsername(String username, String uuid) {


        Session session = getCurrentSession();
        Query query = null;
        if (uuid != null) {
            System.out.println("uuid no = null : " + uuid);
            query = session.createQuery("select u from User u where u.login = :login and u.uuid != :uuid");
            query.setParameter("login", username);
            query.setParameter("uuid", uuid);
        } else {
            query = session.createQuery("select u from User u where u.login = :login");
            query.setParameter("login", username);
        }
        User user = (User) query.uniqueResult();
        System.out.println(user);
        return user;
    }

}