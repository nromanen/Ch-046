package ua.cv.tim.dao.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.model.Village;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rmochetc on 03.01.2017.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {

    @Override
    public User getUserByUsername(String username) {
        Query<User> query = getCurrentSession().getNamedQuery("GET_USER_BY_LOGIN");
        query.setParameter("login", username);
        return query.uniqueResult();
    }

    @Override
    public User getWithRolesById(String id) {
        User user = getCurrentSession().get(User.class, id);
        List<Role> roles = user.getRoles();
        Hibernate.initialize(roles);
        return getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) getCurrentSession().getNamedQuery("GET_ALL_FROM_USER").getResultList();

    }

    @Override
    public List<User> getUsersByAlliance(String allianceName) {
        TypedQuery<User> query = getCurrentSession().getNamedQuery("GET_USER_BY_ALLIANCENAME");
        query.setParameter("name", allianceName);
        return query.getResultList();
    }

    @Override
    public User getById(String uuid) {
        TypedQuery<User> query = getCurrentSession().getNamedQuery("GET_USER_BY_ID");
        query.setParameter("id", uuid);
        return query.getSingleResult();
    }

    @Override
    public List<User> getAllWithRoles() {
        List<User> allWithRoles = (List<User>) getCurrentSession().getNamedQuery("GET_ALL_FROM_USER").getResultList();
        for (User user : allWithRoles)
            Hibernate.initialize(user.getRoles());
        return allWithRoles;
    }

    @Override
    public User getByMail(String mail){
        Query<User> query = getCurrentSession().getNamedQuery("GET_USER_BY_MAIL");
        query.setParameter("mail", mail);
        return query.uniqueResult();

    }

    @Override
    public User getUserWithAlliance(String username) {
        TypedQuery<User> query = getCurrentSession().getNamedQuery("GET_USER_BY_LOGIN");
        query.setParameter("login", username);
        User user = query.getSingleResult();
        Hibernate.initialize(user.getPlayer().getAlliance());
        return user;
    }

    @Override
    public User getFullUserByUsername(String username) {
        TypedQuery<User> query = getCurrentSession().getNamedQuery("GET_USER_BY_LOGIN");
        query.setParameter("login", username);
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