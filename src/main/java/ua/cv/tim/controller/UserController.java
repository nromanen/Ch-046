package ua.cv.tim.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.cv.tim.dto.UserInfoDTO;
import ua.cv.tim.exception.EntityNotUniqueException;
import ua.cv.tim.model.User;
import ua.cv.tim.service.UserService;

import java.util.List;

/**
 * Created by Oleg on 07.01.2017.
 */

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;


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

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable(name = "id") String id) {
        User user = userService.getById(id);
        user.setUuid(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/alliance-users/{allianceName}")
    public ResponseEntity<List<UserInfoDTO>> getUsersByAlliance(@PathVariable(name = "allianceName") String allianceName) {
        logger.info("UserController.getUsersByAlliance() method is working. Alliance name: {}", allianceName);
        List<UserInfoDTO> allianceUsers = userService.getUsersByAlliance(allianceName);
        logger.info("Users from DB: {}", allianceUsers);
        return new ResponseEntity<>(allianceUsers, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoDTO> addAllianceMember(@RequestBody UserInfoDTO member) throws MessagingException,EntityNotUniqueException {
        logger.info("UserController.addAllianceMember() method is working. Alliance name: {}", member.toString());
        User user = new User();
        user.setLogin(member.getLogin());
        user.setEmail(member.getEmail());
        if (!userService.isUnique(user)) {
            logger.error("UserController.addAllianceMember: {}", "user is not unique");
            throw new EntityNotUniqueException(" User with entered login or e-mail already exist ");
        }
        addNewAllianceMember(member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    private boolean addNewAllianceMember(@RequestBody UserInfoDTO member) throws MessagingException {
        try {
            userService.addAllianceMember(member);
            logger.info("UserController.addAllianceMember() method try section, send message on  {}", member.getEmail());
        } catch (MessagingException e) {
            logger.error("UserController.addAllianceMember: {}", e);
            throw new MessagingException("Message did not send check internet connection");
        }
        return true;
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
