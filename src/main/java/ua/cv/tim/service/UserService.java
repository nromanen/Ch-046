package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.hibernate.UserDao;
import ua.cv.tim.model.User;

/**
 * Created by Admin on 31.12.16.
 */
@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        userDao.add(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public User getById(String id){
        return userDao.getById(id);
    }

}


