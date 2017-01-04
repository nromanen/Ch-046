package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.cv.tim.dao.hibernate.AllianceDao;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.model.AllianceDTO;
import ua.cv.tim.model.User;
import ua.cv.tim.service.AllianceService;
import ua.cv.tim.service.UserService;

import javax.validation.Valid;


/**
 * Created by rmochetc on 30.12.2016.
 */

@Controller
public class AllianceController {

    @Autowired
    private AllianceService allianceService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private AllianceDao allianceDao;


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String createAlliance(ModelMap model) {

//        AllianceDTO allianceDTO = new AllianceDTO();
//
//        model.addAttribute("allianceDTO", allianceDTO);

        return "index";
    }

    @RequestMapping(value = {"/alliance1"}, method = RequestMethod.GET)
    public String createAlliance1(ModelMap model) {

        Alliance alliance = allianceService.getById(1);
        System.out.println(alliance.getLeader());
//        User user = userService.getById(alliance.getLeader().g)

        System.out.println(alliance);



        AllianceDTO allianceDTO = new AllianceDTO();

        model.addAttribute("allianceDTO", allianceDTO);

        return "addAlliance";
    }

    @RequestMapping(value = {"/alliance"}, method = RequestMethod.POST)
    public String addAlliance(@Valid AllianceDTO alliance, BindingResult result,
                              ModelMap model) {
        System.out.println("TEst post");

        if (result.hasErrors()) {
            return "addAlliance";
        }

        allianceService.addAlliance(alliance);
        model.addAttribute("success", "Alliance " + alliance.getName() + " created successfully");
        return "addAlliance";
    }

//    @RequestMapping(value = {"/alliance"}, method = RequestMethod.PUT)
//    public String editAlliance(@Valid AllianceDTO alliance, BindingResult result,
//                              ModelMap model) {
//
//        if (result.hasErrors()) {
//            return "addAlliance";
//        }
//
//        allianceService.updateAlliance(alliance);
//        model.addAttribute("success", "Alliance " + alliance.getName() + " created successfully");
//        return "addAlliance";
//    }

}



