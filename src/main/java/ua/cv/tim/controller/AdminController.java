package ua.cv.tim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by vyach on 05.01.2017.
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @RequestMapping(method = RequestMethod.GET)
    public String showAdminMainPage() {
        return "test.html";
    }
}
