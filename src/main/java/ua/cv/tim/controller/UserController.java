package ua.cv.tim.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

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

import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.utils.SendMail;

@RestController
@RequestMapping(value = "/user")
public class UserController {

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
		System.out.println("UserMail "+userDTO.getEmail());
		System.out.println("UserLogin "+userDTO.getLogin());
		System.out.println("UserPassword "+userDTO.getPassword());
		System.out.println("User Role  "+userDTO.getRole());		
		userService.add(userDTO);
		System.out.println("User added succesfully " + " Login "+userDTO.getLogin());
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/leader", method = RequestMethod.GET)
	public ModelAndView getAllUsers() {

		ModelAndView model = new ModelAndView("leaderMainPage.html");

		return model;
	}

}
