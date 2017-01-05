package ua.cv.tim.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.UserService;
import ua.cv.tim.utils.SendMail;


@Controller
public class AddUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SendMail sendMail;
	
		
	
		@RequestMapping(value="/", method = RequestMethod.GET)
		public ModelAndView getAdmissionForm() {
			
			ModelAndView model = new ModelAndView("addUser");
			return model;
		}
			
		
		@RequestMapping(value="/submitUserForm", method = RequestMethod.POST)
		public ModelAndView submitAdmissionForm(@ModelAttribute("user") User user, @RequestParam(value = "Role", required = false) String role) {
			//String checkRole = null;
			List<Role> roles = new ArrayList<>();
			user.setUuid("lhljh-122223t"+Math.random()*100);
			roles.add(Role.USER);
			if(role!=null){
				roles.add(Role.valueOf(role));
			}
			user.setRoles(roles);
			userService.add(user);
			try {
				sendMail.send(user.getEmail(), "Travian user's info", "Your login is"+user.getLogin()+" and password: "+user.getPassword());
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
			ModelAndView model = new ModelAndView("addUser");
			System.out.println("User added succesfully"+user.getLogin()+roles);
			return model;
		}
	}

