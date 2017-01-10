package ua.cv.tim.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.UserDaoIml;
import ua.cv.tim.dao.hibernate.UserDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;

import java.util.List;

/**
 * Created by Oleg on 04.01.2017.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService{
    @Autowired
    private UserDao userDao;

    public  long getCount(){
       return userDao.getCount();
    }

    @Override
    public User getWithRolesById(String id) {
        return userDao.getWithRolesById(id);
    }

    @Override
    public List<User> getAllWithRoles() {
        List<User> allWithRoles = userDao.getAllWithRoles();
        Hibernate.initialize(allWithRoles.get(0).getRoles());
        return allWithRoles;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }


    @Override
    public User getById(String id) {
        return userDao.getById(id);
    }
}
