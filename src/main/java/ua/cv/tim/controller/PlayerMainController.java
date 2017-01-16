package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.cv.tim.model.User;
import ua.cv.tim.service.UserService;

/**
 * Created by okunetc on 16.01.2017.
 */
@Controller
@RequestMapping(value = "/user/{id}")
public class PlayerMainController {
    @Autowired
    UserService userService;
    @RequestMapping(method= RequestMethod.GET)
    public String showPlayerMainPage(){
//        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userByUsername = userService.getUserByUsername(principal.getUsername());
//        String uuid = userByUsername.getUuid();
        return "villagesList.html";
    }
}
