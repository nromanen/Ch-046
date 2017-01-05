package ua.cv.tim.service;
/**
 * Created by mmaksymtc on 05.01.2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.cv.tim.dao.hibernate.UserDao;
import ua.cv.tim.model.User;

@Service("userService")
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void add(User user){
		userDao.add(user);
	}
}
