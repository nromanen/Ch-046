package ua.cv.tim.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.AuthorizedUser;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.UserService;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by Oleg on 07.01.2017.
 */


@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserService userService;


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserWithAlliance() {
		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AuthorizedUser authorizedUser = (AuthorizedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info(authorizedUser.toString());
		User user = userService.getUserWithAlliance(principal.getUsername());
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		UserDTO userDTO = new UserDTO(user.getUuid(), user.getLogin(), user.getEmail(),
				user.getPlayer().getAlliance().getName(), user.getRoles().size() == 2);
		logger.info(userDTO.toString());
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allWithRoles = userService.getAllWithRoles();
		if (allWithRoles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(allWithRoles, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getById(@PathVariable("id") String id) {
		User user = userService.getWithRolesById(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO member) throws MessagingException {
		logger.info("Alliance name: {}", member.toString());
		User user = new User();
		user.setLogin(member.getLogin());
		user.setEmail(member.getEmail());
		if (userService.isUnique(user)) {
			addUserToDataBase(member);
			member.setUuid(userService.getUserByUsername(member.getLogin()).getUuid());
		}
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

	private boolean addUserToDataBase(@RequestBody UserDTO member) throws MessagingException {
		try {
			userService.addUser(member);
			logger.info("Try section, send message on  {}", member.getEmail());
		} catch (MessagingException e) {
			logger.error("Exception: {}", e);
			throw new MessagingException("Message did not send check internet connection");
		}
		return true;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteUser(@PathVariable(name = "id") String id) {
		if (isUserExists(id)) {
			logger.info("User id: {}", id);
			userService.deleteById(id);
			logger.info("User has deleted successfully");
			return new ResponseEntity(HttpStatus.OK);
		} else {
			throw new IllegalArgumentException("User with the same id does not exist!");
		}
	}


	private boolean isUserExists(String id) {
		return userService.getById(id) != null;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") String id, @RequestBody UserDTO user) throws MessagingException {
		logger.info("User id: {}, user body: {}", id, user);
		User currentUser = userService.getById(id);

		if (currentUser == null) {
			throw new IllegalArgumentException("User with the same id does not exist!");
		}

		currentUser.setLogin(user.getLogin());
		currentUser.setEmail(user.getEmail());
		changeUserRole(user, currentUser.getRoles());

		if (userService.isUnique(currentUser)) {
			userService.update(currentUser);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	private void changeUserRole(UserDTO user, List<Role> currentUserRoles) {
		if (user.getIsLeader() && currentUserRoles.size() != 2) {
			currentUserRoles.add(Role.LEADER);
		} else if (!user.getIsLeader() && currentUserRoles.size() == 2) {
			currentUserRoles.remove(Role.LEADER);
		}
	}

	@RequestMapping(value = "/alliance-users")
	public ResponseEntity<List<UserDTO>> getUsersByAlliance() {
		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserWithAlliance(principal.getUsername());
		List<UserDTO> allianceUsers = userService.getUsersByAlliance(user.getPlayer().getAlliance().getName());
		logger.info("Users from DB: {}", allianceUsers);
		return new ResponseEntity<>(allianceUsers, HttpStatus.OK);
	}
}
