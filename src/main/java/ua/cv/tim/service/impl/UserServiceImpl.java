package ua.cv.tim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import java.util.Locale;
import java.util.Random;


/**
 * @author Oleg on 04.01.2017.
 *
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
	private MessageSource messageSource;

	@Autowired
	private PasswordResetDao passwordResetDao;

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	public void add(User user) {
		userDao.add(user);
		logger.info("User with login {} and id {} was added ",user.getLogin(),user.getUuid());
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	/**
	 * Updates user and sends email with updated info
	 * @param user
	 * @throws MessagingException if there is no internet connection
	 */
	@Override
	public void update(User user) throws MessagingException {
		userDao.update(user);
		String message = "Player updated successfully, your login is \"" + user.getLogin() + "\", role: " + user.getRoles();
		sendEmail(user, message);
		logger.info("User with login {} and id {} was updated ",user.getLogin(),user.getUuid());
	}


	@Override
	public void delete(User user) {
	    userDao.delete(user);
		logger.info("User with login {} and id {} was deleted ",user.getLogin(),user.getUuid());
	}

	/**
	 * Checks if user already exists in repository.
	 * @param user
	 * @return true if user is unique
	 * @throws IllegalArgumentException if user is not unique
	 */
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
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = null;
		if (!isLoginEmailUnique[0] && !isLoginEmailUnique[1]) {
			errorMessage = messageSource.getMessage("userService.errorMessage.loginAndEmail", null, locale);
		} else if (!isLoginEmailUnique[0]) {
			errorMessage = messageSource.getMessage("userService.errorMessage.login", null, locale);
		} else if (!isLoginEmailUnique[1]) {
			errorMessage = messageSource.getMessage("userService.errorMessage.email", null, locale);
		}
		return errorMessage;
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
		logger.info("User with login {} and id {} was deleted ",byId.getLogin(),byId.getUuid());
	}

	public List<UserDTO> getUsersByAlliance(String allianceName) {
		List<User> users = userDao.getUsersByAlliance(allianceName);
		List<UserDTO> allianceUsers = new ArrayList<>();
		for (User user : users) {
			allianceUsers.add(new UserDTO(user.getUuid(), user.getLogin(), user.getEmail(),
					user.getPlayer().getAlliance().getName(), user.getRoles().size() == 2));
		}
		return allianceUsers;
	}

	/**
	 * Converts userDTO into user and adds into repository
	 * @param member
	 * @throws MessagingException if there is no internet connection
	 */
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
		logger.info("User with login {} and id {} was added",user.getLogin(),user.getUuid());
		String message = "Your login is \"" + user.getLogin() + "\", and password: \""
				+ password + "\",  Role: " + user.getRoles();
		sendEmail(user,message);
	}

	/**
	 *Sends message on users email
	 * @param user
	 * @param message
	 * @throws MessagingException if there is no internet connection
	 */
	@Override
	public void sendEmail(User user, String message) throws MessagingException {
			sendMail.send(user.getEmail(), "Travian user's info",message );
		logger.info("Info message sent successfully on email {}",user.getEmail());
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

	/**
	 * Returns eager user with  player, race, alliance, villages and armies
	 * @param username
	 * @return user
	 */
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
		logger.info("Token generated : {}", token);
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

		String password  = user.getPassword();
		password = encodePassword(password);
		user.setPassword(password);
		update(user);
		logger.info("User password reset for user with id = {}", user.getUuid());
		passwordResetDao.deleteByUser(user);
	}

	@Override
	public void deleteOldToken() {
		passwordResetDao.deleteOldToken();
	}
}
