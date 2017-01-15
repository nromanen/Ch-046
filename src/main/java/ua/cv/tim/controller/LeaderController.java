package ua.cv.tim.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vyach on 05.01.2017.
 */

@RestController
@RequestMapping(value = "/leader")
public class LeaderController {

    @RequestMapping(method = RequestMethod.GET)
    public String showAdminMainPage() {
        return "leaderMainPage.html";
    }

}
