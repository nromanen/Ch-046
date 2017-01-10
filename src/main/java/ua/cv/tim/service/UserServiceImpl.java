package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.model.User;

import java.util.List;

/**
 * Created by Oleg on 04.01.2017.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		userDao.add(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

    @Override
    public long getCount() {
        return userDao.getCount();
    }

    @Override
    public User getWithRolesById(String id) {
        return userDao.getWithRolesById(id);
    }

    @Override
    public List<User> getAllWithRoles() {
        return userDao.getAllWithRoles();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(String id) {
        return userDao.getById(id);
    }
}
