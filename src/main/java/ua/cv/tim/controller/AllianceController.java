package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.service.AllianceService;
import ua.cv.tim.service.UserService;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by rmochetc on 30.12.2016.
 */

@RestController
public class AllianceController {

    @Autowired
    private AllianceService service;

    @RequestMapping(value = "/allianceDTO", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AllianceDTO>> listAllAlliances() {


        List<AllianceDTO> alliances = service.getAll();
        if(alliances.isEmpty()){
            return new ResponseEntity<List<AllianceDTO>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<AllianceDTO>>(alliances, HttpStatus.OK);
    }



























//    @Autowired
//    private AllianceService allianceService;
//
//    @Autowired
//    private UserService userService;
//
////    @Autowired
////    private AllianceDao allianceDao;
//
//
//    @RequestMapping(value = {"/alliance"}, method = RequestMethod.GET)
//    public String createAlliance(ModelMap model) {
//
//        AllianceDTO allianceDTO = new AllianceDTO();
//
//        model.addAttribute("allianceDTO", allianceDTO);
//
//        return "addAlliance.html";
//    }
//
//    @RequestMapping(value = {"/alliance1"}, method = RequestMethod.GET)
//    public String createAlliance1(ModelMap model) {
//
//        Alliance alliance = allianceService.getById("1);
//       // System.out.println(alliance.getLeader());
////        User user = userService.getById(alliance.getLeader().g)
//
//        System.out.println(alliance);
//
//
//
//        AllianceDTO allianceDTO = new AllianceDTO();
//
//        model.addAttribute("allianceDTO", allianceDTO);
//
//        return "addAlliance.html";
//    }
//
//    @RequestMapping(value = {"/alliance"}, method = RequestMethod.POST)
//    public String addAlliance(@Valid AllianceDTO alliance, BindingResult result,
//                              ModelMap model) {
//        System.out.println("TEst post");
//
////        if (result.hasErrors()) {
////            System.out.println(result.getAllErrors());
////            return "addAlliance.html";
////        }
//
//        allianceService.addAlliance(alliance);
//        model.addAttribute("success", "Alliance " + alliance.getName() + " created successfully");
//        return "addAlliance.html";
//    }
//
////    @RequestMapping(value = {"/alliance"}, method = RequestMethod.PUT)
////    public String editAlliance(@Valid AllianceDTO alliance, BindingResult result,
////                              ModelMap model) {
////
////        if (result.hasErrors()) {
////            return "addAlliance";
////        }
////
////        allianceService.updateAlliance(alliance);
////        model.addAttribute("success", "Alliance " + alliance.getName() + " created successfully");
////        return "addAlliance";
////    }

}



