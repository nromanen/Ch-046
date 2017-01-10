package ua.cv.tim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;

/**
 * Created by vyach on 03.01.2017.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PlayerDao playerDao;

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
	@Override
	public void add(User user) {
		userDao.add(user);
		Player player = new Player();
		player.setUser(user);
		user.setPlayer(player);
		playerDao.add(player);

	}
	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}
	@Override
	public void update(User user) {
		userDao.update(user);
	}
	@Override
	public void delete(User user) {
		userDao.delete(user);
	}
	@Override
	public boolean isUnique(User user) {
		if(userDao.getByMail(user.getEmail())!=null)
		{
			return false;
		}
		else return true;
	}
}
