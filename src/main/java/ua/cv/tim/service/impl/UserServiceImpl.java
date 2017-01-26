package ua.cv.tim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.UserService;
import ua.cv.tim.utils.SendMail;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Oleg on 04.01.2017.
 */

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private AllianceDao allianceDao;
	@Autowired
	private PlayerDao playerDao;
	@Autowired
	private SendMail sendMail;

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByName(username);
	}

	public void add(User user) {
		userDao.add(user);
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


		if (userDao.isUserUnique(user.getLogin(), user.getUuid())) {
			return true;
		}
		if (userDao.getByMail(user.getEmail(), user.getUuid()) != null) {
			return true;
		}
		return false;
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
	public void deleteById(String id) {
		User byId = userDao.getById(id);
		userDao.delete(byId);
	}

	public List<UserDTO> getUsersByAlliance(String allianceName) {
		logger.info("alliance name is {} ", allianceName);
		List<User> users = userDao.getUsersByAlliance(allianceName);
		List<UserDTO> allianceUsers = new ArrayList<>();
		for (User user : users) {
			allianceUsers.add(new UserDTO(user.getUuid(), user.getLogin(), user.getEmail(),
					user.getPlayer().getAlliance().getName()));
		}
		return allianceUsers;
	}

	public void addUser(UserDTO member) throws MessagingException {
		User user = new User();
		user.setLogin(member.getLogin());
		user.setEmail(member.getEmail());
		user.setPassword(generatePassword(10));
		logger.info("Password is {} ", user.getPassword());
		List<Role> roles = new ArrayList<>();
		roles.add(Role.USER);
		if (member.getRole() != null) {
			roles.add(Role.LEADER);
		}
		user.setRoles(roles);
		userDao.add(user);
		Player player = new Player();
		player.setUser(user);
		player.setAlliance(allianceDao.getAllianceByName(member.getAlliance()));
		user.setPlayer(player);
		playerDao.add(player);
		sendEmail(user);
	}

	public void sendEmail(User user) throws MessagingException {
		try {
			sendMail.send(user.getEmail(), "Travian user's info", "Your login is" + user.getLogin() + " and password: "
					+ user.getPassword() + "  role " + user.getRoles());
			logger.info("Password {} has been sent on user's e-mail {}", user.getPassword(), user.getEmail());
		} catch (MessagingException e) {
			logger.error("The e-mail {} hasn't been sent {}", user.getEmail(), e);
		}
	}

	private String generatePassword(int length) {
		String symbols = "][{}()~!@#$%^&*.,`";
		String numbers = "0123456789";
		String upperalphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		char[] password = new char[length];
		Random random = new Random();
		for (int i = 0; i < length - 2; i++) {
			if (i % 2 == 0) {
				password[i] = upperalphabet.charAt(random.nextInt(upperalphabet.length()));
			} else {
				password[i] = lowerAlphabet.charAt(random.nextInt(lowerAlphabet.length()));
			}
		}
		password[length - 2] = numbers.charAt(random.nextInt(numbers.length()));
		password[length - 1] = symbols.charAt(random.nextInt(symbols.length()));
		for (int i = 0; i < password.length; i++) {
			int randomIndex = (int) (Math.random() * password.length);
			char temp = password[i];
			password[i] = password[randomIndex];
			password[randomIndex] = temp;
		}
		logger.info("Auto-generated password is {}", new String(password));
		return new String(password);
	}

	public boolean isUserUnique(UserDTO member) {
		User user = new User();
		user.setLogin(member.getLogin());
		user.setEmail(member.getEmail());

		if (userDao.isUserUnique(user.getLogin(), user.getUuid())) {
			return false;
		} else if (userDao.getByMail(user.getEmail(), user.getUuid()) != null) {
			return false;
		} else return true;
	}

}
