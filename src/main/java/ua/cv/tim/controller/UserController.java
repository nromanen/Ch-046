package ua.cv.tim.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.utils.SendMail;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SendMail sendMail;
	
		
	
		@RequestMapping(value="/add", method = RequestMethod.GET)
		public ModelAndView addUserForm() {
			
			ModelAndView model = new ModelAndView("addUser");
			return model;
		}
			
		
		@RequestMapping(value="/submitUserForm", method = RequestMethod.POST)
		public ModelAndView submitUserForm(@ModelAttribute("user") User user, 
				@RequestParam(value = "Role", required = false) String role) {
			List<Role> roles = new ArrayList<>();
			roles.add(Role.USER);
			if(role!=null){
				roles.add(Role.valueOf(role));
			}
			user.setRoles(roles);			
			userService.add(user);
			if(userService.isUnique(user)){
			//	 View.SpanText = "This is span text"; 
			}
			try {
				sendMail.send(user.getEmail(), "Travian user's info", "Your login is"+user.getLogin()+" and password: "+user.getPassword());
				System.out.println("Password has been send on users mail");
			} catch (MessagingException e) {
				System.out.println("something gone wrong");
				e.printStackTrace();
			}
			ModelAndView model = new ModelAndView("leaderMainPage");
			System.out.println("User added succesfully "+user.getUuid()+user.getLogin());
			return model;
		}
		

		@RequestMapping(value="/leader", method = RequestMethod.GET)
		public ModelAndView getAllUsers() {
			
			ModelAndView model = new ModelAndView("leaderMainPage");
			
			return model;
		}
			
	}

