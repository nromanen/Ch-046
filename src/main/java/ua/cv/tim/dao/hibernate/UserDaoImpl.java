package ua.cv.tim.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.service.UserService;

/**
 * Created by rmochetc on 03.01.2017.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public long getCount() {
        return (long) getCurrentSession().createCriteria(User.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public User getUserByUsername(String username) {
        logger.info("Username is {} ", username);
        String request = "select u from User u where u.login = :login";
        Query<User> query = getCurrentSession().createQuery(request);
        query.setParameter("login", username);
        return query.uniqueResult();
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
        logger.info("Mail is {} uuid is {} ", mail,uuid);
        Session session = getCurrentSession();
        Query query = null;
        if (uuid != null) {
            logger.info("User with mail {} has uuid {} ",mail, uuid);
            query = session.createQuery("select u FROM User u WHERE u.email=:mail and u.uuid != :uuid");
            query.setParameter("mail", mail);
            query.setParameter("uuid", uuid);
        } else {
            query = session.createQuery("select u FROM User u WHERE u.email=:mail");
            query.setParameter("mail", mail);
        }
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public List<User> getUsersByAlliance(String allianceName) {
        String request = "select u from User u where u.player.alliance.name = :name";
        Query<User> query = getCurrentSession().createQuery(request);
        query.setParameter("name", allianceName);
        return query.list();
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


    public User getByMail(String mail){
        String request = "select u FROM User u WHERE u.email=:mail";
        Query<User> query = getCurrentSession().createQuery(request);
        query.setParameter("mail", mail);
        return query.uniqueResult();

    }

    public User getUserByUsername(String username, String uuid) {
        Session session = getCurrentSession();
        Query query = null;
        if (uuid != null) {
            logger.info("Username is {} ", username);
            System.out.println("uuid no = null : " + uuid);
            query = session.createQuery("select u from User u where u.login = :login and u.uuid != :uuid");
            query.setParameter("login", username);
            query.setParameter("uuid", uuid);
        } else {
            query = session.createQuery("select u from User u where u.login = :login");
            logger.info("Id is null and username is {} ", username);
            query.setParameter("login", username);
        }
        User user = (User) query.uniqueResult();
            return user;
    }
}