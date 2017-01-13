package ua.cv.tim.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.utils.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Oleg on 04.01.2017.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private PlayerDao playerDao;
	
	@Autowired
	private SendMail sendMail;

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
	@Override
	public void add(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setLogin(userDTO.getLogin());
		user.setPassword(userDTO.getPassword());
		List<Role> roles = new ArrayList<>();
		roles.add(Role.USER);
		if (userDTO.getRole() != null) {
			roles.add(Role.LEADER);
		}
		user.setRoles(roles);
		userDao.add(user);
		Player player = new Player();
		player.setUser(user);
		user.setPlayer(player);
		playerDao.add(player);
		
		try {
			sendMail.send(user.getEmail(), "Travian user's info",
					"Your login is" + user.getLogin() + " and password: " + user.getPassword()+"  role "+user.getRoles());
			logger.info("Password {} has been sent on user's e-mail {}" , user.getPassword(), user.getEmail());
        } catch (MessagingException e) {
            logger.error("The e-mail hasn't been sent {}", e);
		}

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
    public User getById(String id) {
        return userDao.getById(id);
    }
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		
	}
}
