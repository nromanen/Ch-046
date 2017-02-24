package ua.cv.osbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET)	
	public String getLoginPage(){		
		return "Login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)	
	public String handleLoginRequest(@RequestParam String name, ModelMap model){	
		model.put("name", name);
		System.out.println(name);
		return "Welcome";
	}
}
