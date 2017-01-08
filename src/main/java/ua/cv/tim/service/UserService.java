package ua.cv.tim.service;

import java.util.List;

/**
 * Created by mmaksymtc on 05.01.2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.cv.tim.dao.hibernate.PlayerDao;
import ua.cv.tim.dao.hibernate.UserDao;
import ua.cv.tim.dao.hibernate.UserDaoImpl;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Race;
import ua.cv.tim.model.User;

@Service("userService")
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private PlayerDao playerDao;
	

	public void add(User user) {
		userDao.add(user);
		Player player = new Player();
		player.setUser(user);
		user.setPlayer(player);
		playerDao.add(player);

	}

	public List<User> getAll() {
		return userDao.getAll();
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void delete(User user) {
		userDao.delete(user);
	}

	public boolean isUnique(User user) {
		if(userDao.getByMail(user.getEmail())!=null)
		{
			return false;
		}
		else return true;
	}
}
