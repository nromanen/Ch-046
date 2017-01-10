package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.cv.tim.service.AllianceService;


/**
 * Created by rmochetc on 05.01.2017.
 */
@Controller
public class InitController {

    @Autowired
    private AllianceService service;

    @RequestMapping(value = {"/alliance"}, method = RequestMethod.GET)
    public String createAlliance() {
        return "alliances.html";
    }
}


