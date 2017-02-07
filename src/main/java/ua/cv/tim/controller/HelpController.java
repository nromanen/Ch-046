package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.cv.tim.dto.AttackDTO;
import ua.cv.tim.dto.PlayerDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;
import ua.cv.tim.service.AttackService;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;

import java.util.List;

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

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByUsername = userService.getUserByUsername(principal.getUsername());
        String id = userByUsername.getPlayer().getUuid();
        Player player = playerService.getByIdWithVillages(id);
        PlayerDTO playerDTO = new PlayerDTO(player.getUser().getLogin(),
                player.getRace(), player.getVillages(), player.getAlliance(),userByUsername.getRoles());

        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/askhelp", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AttackDTO> createHelp(@RequestBody AttackDTO attack) {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByUsername = userService.getUserByUsername(principal.getUsername());
        String id = userByUsername.getPlayer().getUuid();

        attack.setPlayerId(id);
        logger.info("add new ask help. Attack: {}", attack);
        System.out.println(attack);

        attackService.addAttack(attack);

        return new ResponseEntity<>(attack, HttpStatus.OK);
    }

    @RequestMapping(value = "/allAttack", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AttackDTO>> allActiveAttack() {
        logger.info("All active attack RequestMethod.GET");
        return new ResponseEntity<>(attackService.getActive(), HttpStatus.OK);
    }
}