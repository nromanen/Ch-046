package ua.cv.tim.utils;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.configuration.WebConfigurationTest;
import ua.cv.tim.model.User;

import java.util.List;

/**
 * Created by mmaksymtc on 24.01.2017.
 */

@Service("userServiceTestUtils")
@Transactional
@ContextConfiguration(classes = {WebConfigurationTest.class})
public class UserServiceTestUtils {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTestUtils.class);
    @Autowired
    private SessionFactory session;

    public List<User> getUsersList() {
        Query query = session.getCurrentSession().createQuery("FROM User ");
        List<User> users = (List<User>) query.getResultList();
        logger.info("getUsersList {}  ",users);
        return users;
    }

    public User getById(String uuid) {
        Query query = session.getCurrentSession().createQuery("select u FROM User u WHERE u.id=:id");
        query.setParameter("id", uuid);
        User user = (User) query.uniqueResult();
        return user;
    }

    public User getUserByUsername(String username){
        Query  query = session.getCurrentSession().createQuery("select u from User u where u.login = :login");
        query.setParameter("login", username);
        User user = (User) query.uniqueResult();
        return user;
    }

}
