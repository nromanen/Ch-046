package ua.cv.tim.helpNotification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.service.VillageService;

/**
 * Created by rmochetc on 30.01.2017.
 */
@RestController
public class HelpInitController {

    private static final Logger logger = LoggerFactory.getLogger(HelpInitController.class);

    @Autowired
    VillageService villageService;

    @Autowired
    PlayerService playerService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/helpInit", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AllianceDTO> getAllianceByPlayer() {
        logger.info("path: /user/helpInit/ is Starting");

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByUsername = userService.getUserByUsername(principal.getUsername());



        String id = userByUsername.getPlayer().getUuid();

        Player player = playerService.getByIdWithVillages(id);
        AllianceDTO allianceDto = new AllianceDTO();
        allianceDto.setName(player.getAlliance().getName());
        allianceDto.setAllianceUuid(player.getAlliance().getUuid());
        Alliance alliance = player.getAlliance();

        logger.info("path: /user/helpInit/ return AllianceDTO: name: {}, id: {}", allianceDto.getName(), allianceDto.getAllianceUuid());

        return new ResponseEntity<>(allianceDto, HttpStatus.OK);
    }
}
