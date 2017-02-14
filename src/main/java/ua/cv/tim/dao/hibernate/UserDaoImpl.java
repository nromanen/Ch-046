package ua.cv.tim.dao.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.model.*;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.service.UserService;

/**
 * Created by rmochetc on 03.01.2017.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String GET_USER_BY_LOGIN = "select u from User u where u.login = :login";
    private static final String GET_USER_BY_MAIL_AND_UUID = "select u FROM User u WHERE u.email=:mail and u.uuid != :uuid";
    private static final String GET_USER_BY_MAIL ="select u FROM User u WHERE u.email=:mail";
    private static final String GET_USER_BY_ALLIANCENAME = "select u from User u where u.player.alliance.name = :name";
    private static final String GET_USER_BY_ID = "select u FROM User u WHERE u.id=:id";
    private static final String GET_USER_BY_LOGIN_AND_UUID = "select u from User u where u.login = :login and u.uuid != :uuid";
    private static final String LOGIN = "login";

    @Override
    public User getUserByUsername(String username) {
        Query<User> query = getCurrentSession().createQuery(GET_USER_BY_LOGIN);
        query.setParameter(LOGIN, username);
        return query.uniqueResult();
    }

    @Override
    public User getWithRolesById(String id) {
        return getCurrentSession().get(User.class, id);

    }

    @Override
    public List<User> getAll() {
        List<User> users = (List<User>) getCurrentSession().createQuery("FROM User ").getResultList();
        return users;
    }
    @Override
        public User getByMail(String mail, String uuid) {
        Query query = null;
        if (uuid != null) {
            query = getCurrentSession().createQuery(GET_USER_BY_MAIL_AND_UUID);
            query.setParameter("mail", mail);
            query.setParameter("uuid", uuid);
        } else {
            query = getCurrentSession().createQuery(GET_USER_BY_MAIL);
            query.setParameter("mail", mail);
        }
        return (User) query.uniqueResult();
    }

    @Override
    public List<User> getUsersByAlliance(String allianceName) {
        Query<User> query = getCurrentSession().createQuery(GET_USER_BY_ALLIANCENAME);
        query.setParameter("name", allianceName);
        return query.list();
    }

    @Override
    public User getById(String uuid) {
        Query query = getCurrentSession().createQuery(GET_USER_BY_ID);
        query.setParameter("id", uuid);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public List<User> getAllWithRoles() {
        List<User> allWithRoles = (List<User>) getCurrentSession().createQuery("FROM User ").getResultList();
        for (User user : allWithRoles)
            Hibernate.initialize(user.getRoles());
        return allWithRoles;
    }

    @Override
    public User getByMail(String mail){
        Query<User> query = getCurrentSession().createQuery(GET_USER_BY_MAIL);
        query.setParameter("mail", mail);
        return query.uniqueResult();

    }
    @Override
    public boolean isUserUnique(String username, String uuid) {
        Query query = null;
        if (uuid != null) {
            query = getCurrentSession().createQuery(GET_USER_BY_LOGIN_AND_UUID);
            query.setParameter(LOGIN, username);
            query.setParameter("uuid", uuid);
        } else {
            query = getCurrentSession().createQuery(GET_USER_BY_LOGIN);
            logger.info("Id is null and username is {} ", username);
            query.setParameter(LOGIN, username);
        }
        User user = (User) query.uniqueResult();
            return user == null ? true : false;
    }

    @Override
    public User getUserWithAlliance(String username) {
        Query<User> query = getCurrentSession().createQuery(GET_USER_BY_LOGIN);
        query.setParameter(LOGIN, username);
        User user = query.getSingleResult();
        Hibernate.initialize(user.getPlayer().getAlliance());
        return user;
    }

    @Override
    public User getFullUserByUsername(String username) {
        Query<User> query = getCurrentSession().createQuery(GET_USER_BY_LOGIN);
        query.setParameter(LOGIN, username);
        User user = query.getSingleResult();
        if (user.getPlayer() != null) {
            initializePlayerVillages(user.getPlayer());
        }
        return user;
    }

    private void initializePlayerVillages(Player player) {
        for (Village village : player.getVillages()) {
            Hibernate.initialize(village.getArmies());
            Hibernate.initialize(village.getArmyRequests());
        }
        Hibernate.initialize(player.getVillages());
    }

}