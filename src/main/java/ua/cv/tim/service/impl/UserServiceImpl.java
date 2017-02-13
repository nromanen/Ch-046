package ua.cv.tim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.PasswordResetDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.PasswordResetToken;
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

	@Autowired
	private PasswordResetDao passwordResetDao;

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public void update(User user) throws MessagingException {
		userDao.update(user);
		String message = "Player updated successfully, your login is \"" + user.getLogin() + "\", role: " + user.getRoles();
		sendEmail(user, message);
	}


	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public boolean isUnique(User user) {
		boolean[] isLoginEmailUnique = new boolean[2];
		User compareUser = userDao.getUserByUsername(user.getLogin());
		isLoginEmailUnique[0] = compareUser == null || compareUser.getUuid().equals(user.getUuid());
		compareUser = userDao.getByMail(user.getEmail());
		isLoginEmailUnique[1] = compareUser == null || compareUser.getUuid().equals(user.getUuid());

		String errorMessage = createErrorMessage(isLoginEmailUnique);
		if (errorMessage != null) {
			throw new IllegalArgumentException(errorMessage);
		}
		return true;
	}

	private String createErrorMessage(boolean[] isLoginEmailUnique) {
		String errorMessage = null;
		if (!isLoginEmailUnique[0] && !isLoginEmailUnique[1]) {
			errorMessage = "User with the same login and email exists!";
		} else if (!isLoginEmailUnique[0]) {
			errorMessage = "User with the same login exists!";
		} else if (!isLoginEmailUnique[1]) {
			errorMessage = "User with the same email exists!";
		}
		return errorMessage;
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
					user.getPlayer().getAlliance().getName(), user.getRoles().size() == 2));
		}
		return allianceUsers;
	}

	public void addUser(UserDTO member) throws MessagingException {
		User user = new User();
		user.setLogin(member.getLogin());
		user.setEmail(member.getEmail());
		String password = generatePassword(10);
		user.setPassword(encodePassword(password));
		logger.info("Password is {} ", user.getPassword());
		List<Role> roles = new ArrayList<>();
		roles.add(Role.USER);
		if (member.getIsLeader()) {
			roles.add(Role.LEADER);
		}
		user.setRoles(roles);
		userDao.add(user);
		Player player = new Player();
		player.setUser(user);
		player.setAlliance(allianceDao.getAllianceByName(member.getAlliance()));
		user.setPlayer(player);
		playerDao.add(player);
		String message = "Your login is \"" + user.getLogin() + "\", and password: \""
				+ password + "\",  role: " + user.getRoles();
		sendEmail(user, message);
	}

	public void sendEmail(User user, String message) throws MessagingException {
		try {
			sendMail.send(user.getEmail(), "Travian user's info", message);
			logger.info("Email: {}, message: {}", user.getEmail(), message);
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

	private String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Override
	public User getUserWithAlliance(String username) {
		return userDao.getUserWithAlliance(username);
	}

	@Override
	public User getFullUserByUsername(String username) {
		return userDao.getFullUserByUsername(username);
	}


	@Override
	public User getByMail(String email) {
		return userDao.getByMail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		PasswordResetToken passwordResetToken = passwordResetDao.getByUser(user);
		if(passwordResetToken != null){
			passwordResetToken.setToken(token);
			passwordResetDao.update(passwordResetToken);
		} else {
			passwordResetDao.add(myToken);
		}
	}

	@Override
	public boolean isToken(User user, String token) {
		return passwordResetDao.isToken(user, token);
	}

	@Override
	public void updateUserPassword(User user) throws MessagingException {
		System.out.println(user.getPassword());
		String password  = user.getPassword();
		password = encodePassword(password);
		user.setPassword(password);
		System.out.println(user.getPassword());
		update(user);
		passwordResetDao.deleteByUser(user);
	}

	@Override
	public void deleteOldToken() {
		passwordResetDao.deleteOldToken();
	}
}
