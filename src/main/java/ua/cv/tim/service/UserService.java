package ua.cv.tim.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.dto.UserInfoDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.utils.SendMail;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Oleg on 04.01.2017.
 */

@Service("userService")
@Transactional
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private PlayerDao playerDao;
	@Autowired
	private AllianceDao allianceDao;
	@Autowired
	private SendMail sendMail;

	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

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
					"Your login is" + user.getLogin() + " and password: " + user.getPassword() + "  role " + user.getRoles());
			logger.info("Password {} has been sent on user's e-mail {}", user.getPassword(), user.getEmail());
		} catch (MessagingException e) {
			logger.error("The e-mail hasn't been sent {}", e);
		}
	}

	public void add(User user) {
		userDao.add(user);
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
		if (userDao.getByMail(user.getEmail()) != null) {
			return false;
		} else return true;
	}

	public long getCount() {
		return userDao.getCount();
	}

	public User getWithRolesById(String id) {
		return userDao.getWithRolesById(id);
	}

	public List<User> getAllWithRoles() {
		return userDao.getAllWithRoles();
	}

	public User getById(String id) {
		return userDao.getById(id);
	}

	public void deleteById(String id) {
		User byId = userDao.getById(id);
		userDao.delete(byId);
	}

	public List<UserInfoDTO> getUsersByAlliance(String allianceName) {
		List<User> users = userDao.getUsersByAlliance(allianceName);
		List<UserInfoDTO> allianceUsers = new ArrayList<>();
		for (User user : users) {
			allianceUsers.add(new UserInfoDTO(user.getUuid(), user.getLogin(), user.getEmail(),
					user.getPlayer().getAlliance().getName()));
		}
		return allianceUsers;
	}

	public void addAllianceMember(UserInfoDTO member) {
		User user = new User();
		user.setLogin(member.getLogin());
		user.setEmail(member.getEmail());
		user.setPassword("hello");
		List<Role> roles = new ArrayList<>();
		roles.add(Role.USER);
		user.setRoles(roles);
		userDao.add(user);

		Player player = new Player();
		player.setUser(user);
		player.setAlliance(allianceDao.getAllianceByName(member.getAlliance()));
		player.getAlliance().setName(member.getAlliance());
		user.setPlayer(player);
		playerDao.add(player);
	}
}
