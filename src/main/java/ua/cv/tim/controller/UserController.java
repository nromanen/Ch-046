package ua.cv.tim.controller;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.dto.UserInfoDTO;
import ua.cv.tim.model.User;
import ua.cv.tim.service.UserService;
import java.util.List;

/**
 * Created by Oleg on 07.01.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.utils.SendMail;

import javax.mail.MessagingException;


@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String showAdminMainPage() {
		return "user-main.jsp";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addUserForm() {

		ModelAndView model = new ModelAndView("addUser.html");
		return model;
	}

	@RequestMapping(value = "/submitUserForm",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserDTO> submitUserForm(@RequestBody UserDTO userDTO) {
		logger.info("UserMail {}" ,userDTO.getEmail());
		logger.info("UserLogin {}" ,userDTO.getLogin());
		logger.info("UserPassword {}" ,userDTO.getPassword());
		logger.info("User Role {}" ,userDTO.getRole());
		try {
			userService.add(userDTO);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		logger.info("User added succesfully {}" ,userDTO.getLogin());
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/leader", method = RequestMethod.GET)
	public ModelAndView getAllUsers2() {

		ModelAndView model = new ModelAndView("leaderMainPage.html");

		return model;
	}

    @RequestMapping(value = "/user", method = RequestMethod.GET)
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

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        userService.add(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUuid()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        User currentUser = userService.getWithRolesById(id);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentUser.setLogin(user.getLogin());
        currentUser.setPassword(user.getPassword());
        userService.update(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable(name = "id") String id) {
		logger.info("UserController.deleteUser() method is working. User id: {}", id);
        userService.deleteById(id);
		logger.info("UserController.deleteUser() user has deleted");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

	// v.kruhlov realizations
	@RequestMapping(value = "/alliance-users/{allianceName}")
	public ResponseEntity<List<UserInfoDTO>> getUsersByAlliance(@PathVariable(name = "allianceName") String allianceName) {
		logger.info("UserController.getUsersByAlliance() method is working. Alliance name: {}", allianceName);
		List<UserInfoDTO> allianceUsers = userService.getUsersByAlliance(allianceName);
		logger.info("Users from DB: {}", allianceUsers);
		return new ResponseEntity<>(allianceUsers, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInfoDTO> addAllianceMember(@RequestBody UserInfoDTO member) {
		logger.info("UserController.addAllianceMember() method is working. Alliance name: {}", member.toString());
		User user = userService.getUserByUsername(member.getLogin());
		member.setUuid(user.getUuid());
		if (!userService.isUnique(user)) {
			logger.error("UserController.addAllianceMember: {}", "user is not unique");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		try {
			userService.addAllianceMember(member);
		} catch (MessagingException e) {
			logger.error("UserController.addAllianceMember: {}", e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);// should send error message on html
		}
		logger.info("UserController.getUsersByAlliance() method return: {}", member); // todo не сохраняет Player в service т.к. не вытащен Alliance
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateMember(@PathVariable("id") String id, @RequestBody UserInfoDTO user) {
		logger.info("UserController.updateUser() method is working. User id: {}, user body: {}", id, user);
		User currentUser = userService.getById(id);
		if (currentUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentUser.setLogin(user.getLogin());
		currentUser.setEmail(user.getEmail());
		userService.update(currentUser);
		return new ResponseEntity<>(currentUser, HttpStatus.OK);
	}

}
