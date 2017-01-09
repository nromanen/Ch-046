package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.cv.tim.dao.hibernate.ArmyDao;

@Controller
@RequestMapping("/")
public class StartController {
    @Autowired
    private ArmyDao armyDao;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String start() {
		return "startPage";
	}
}