package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.UserDaoIml;
import ua.cv.tim.dao.hibernate.UserDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;

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


}
