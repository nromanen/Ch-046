package ua.cv.tim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StartController {

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String start() {
		return "startPage";
	}
}