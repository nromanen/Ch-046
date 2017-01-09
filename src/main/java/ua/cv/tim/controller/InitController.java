package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.cv.tim.dao.hibernate.AllianceDao;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.service.AllianceService;


/**
 * Created by rmochetc on 05.01.2017.
 */
@Controller
public class InitController {

    @Autowired
    private AllianceService service;

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String createAlliance() {

        System.out.println("ADDDDDDDDDDDDDDDDDDDDDDD");
        AllianceDTO alliance = new AllianceDTO("Valhala", "Borg", "bord_testemail@travian.com");

        service.addAlliane(alliance);

        System.out.println("GET /");

        return "index.html";
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public String getAlliance() {
        System.out.println("GGGGGGGGGGGGGGGGGGEEEEEEEEEEEEEEEEEEEEEEEEEETTTTTTTTTTTTTTTTTTTTTTTT ALLLLLLLLLL");
        service.getById("if");


        return "index.html";
    }


    @RequestMapping(value = {"/delete"}, method = RequestMethod.GET)
    public String deleteAlliance() {
        System.out.println("DEELLLLLLLLLEEEEEEEEEEEETTTTTTTTTTTTTEEEEEEEEEEE");
        service.deleteAlliance("if");


        return "index.html";
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.GET)
    public String updateAlliance() {
        System.out.println("UUUUUUUUUUUPPPPPPPPPPPPPPPPPPDDDDDDDDDDDDDDDDDDEEEEEEEEEEEEEEEEEETTTTTTTTTTTTTTTTTTTTEEEEEEEEEEEEEEEEEE");
        service.updateAlliance(null);


        return "index.html";
    }
}


