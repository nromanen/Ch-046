package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.dto.AttackDTO;
import ua.cv.tim.dto.PlayerDTO;
import ua.cv.tim.model.Attack;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.AttackService;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.service.VillageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Oleg on 08.01.2017.
 */

@RestController
public class HelpController {

    private static final Logger logger = LoggerFactory.getLogger(HelpController.class);



    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    private UserService userService;

    @Autowired
    PlayerService playerService;

    @Autowired
    AttackService attackService;

    @RequestMapping(value = "/askhelp", method = RequestMethod.GET)
    public ResponseEntity<PlayerDTO> getPlayerById() {

        System.out.println("Help");

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.getUsername());
        User userByUsername = userService.getUserByUsername(principal.getUsername());
        String id = userByUsername.getPlayer().getUuid();
        System.out.println(id);
        Player player = playerService.getById(id);
        PlayerDTO playerDTO = new PlayerDTO(player.getUser().getLogin(),
                player.getUser().getPassword(), player.getUser().getEmail(),
                player.getRace(), player.getVillages(), player.getAlliance());

        System.out.println(playerDTO);
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/askhelp", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AttackDTO> createHelp(@RequestBody AttackDTO attack) {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.getUsername());
        User userByUsername = userService.getUserByUsername(principal.getUsername());
        String id = userByUsername.getPlayer().getUuid();

       // GregorianCalendar date = new GregorianCalendar(2017, 0, 26, 12, 00, 03);
        attack.setPlayerId(id);
        logger.info("add new ask help. Attack: {}", attack);



        //attack.setAttackTime(date.getTime().toString());

        attackService.addAttack(attack);

        System.out.println(attackService.getActive());
        return new ResponseEntity<>(attack, HttpStatus.OK);
    }

    @RequestMapping(value = "/allAttack", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AttackDTO>> allActiveAttack() {
        logger.info("All active attack RequestMethod.GET");
        return new ResponseEntity<>(attackService.getActive(), HttpStatus.OK);
    }


}