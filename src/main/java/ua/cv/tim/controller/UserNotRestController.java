package ua.cv.tim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by okunetc on 13.01.2017.
 */
@Controller
@RequestMapping(value = "/user")
public class UserNotRestController {
    @RequestMapping(method = GET)
    public String showPlayerMainPage(){
        return "";
    }
}
