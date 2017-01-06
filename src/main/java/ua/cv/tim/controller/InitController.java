package ua.cv.tim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by rmochetc on 05.01.2017.
 */
@Controller
public class InitController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String createAlliance() {

        System.out.println("GET /");

        return "index.html";
    }
}


