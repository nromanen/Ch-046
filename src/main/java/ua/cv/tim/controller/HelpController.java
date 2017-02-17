package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.dto.AttackDTO;
import ua.cv.tim.dto.PlayerDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;
import ua.cv.tim.service.AttackService;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.service.VillageService;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by rmochetc on 08.01.2017.
 */

@RestController
public class HelpController {

    private static final Logger logger = LoggerFactory.getLogger(HelpController.class);

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private AttackService attackService;

    @Autowired
    private VillageService villageService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/user/helpInit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AllianceDTO> getAllianceByPlayer() {
        logger.info("path: /user/helpInit/ is Starting");

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByUsername = userService.getUserByUsername(principal.getUsername());
        String id = userByUsername.getPlayer().getUuid();

        Player player = playerService.getByIdWithVillages(id);
        AllianceDTO allianceDto = new AllianceDTO();
        allianceDto.setName(player.getAlliance().getName());
        allianceDto.setAllianceUuid(player.getAlliance().getUuid());

        logger.info("path: /user/helpInit/ return AllianceDTO: name: {}, id: {}", allianceDto.getName(), allianceDto.getAllianceUuid());
        return new ResponseEntity<>(allianceDto, HttpStatus.OK);
    }


    @RequestMapping(value = "/askhelp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PlayerDTO> getPlayerById() {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByUsername = userService.getUserByUsername(principal.getUsername());
        String id = userByUsername.getPlayer().getUuid();
        Player player = playerService.getByIdWithVillages(id);
        PlayerDTO playerDTO = new PlayerDTO(player.getUser().getLogin(),
                player.getRace(), player.getVillages(), player.getAlliance(),userByUsername.getRoles());

        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/askhelp", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<AttackDTO> createHelp(@RequestBody AttackDTO attack) {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByUsername = userService.getUserByUsername(principal.getUsername());
        String id = userByUsername.getPlayer().getUuid();

        if(new Date().after(new Date(attack.getAttackTime()))){
            logger.info("Date of attack can't be in the past: {}", attack.getAttackTime());
            Locale locale = LocaleContextHolder.getLocale();
            String errorMessage =  messageSource.getMessage("helpController.attackTime", null, locale);
            throw new IllegalArgumentException(errorMessage);
        }
        attack.setPlayerId(id);
        logger.info("add new ask help. Attack: {}", attack);
        attackService.addAttack(attack);
        return new ResponseEntity<>(attack, HttpStatus.OK);
    }

    @RequestMapping(value = "/allAttack", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<AttackDTO>> allActiveAttack() {
        List<AttackDTO> activeAttack = attackService.getActive();

        logger.info("All active attack {}", activeAttack);
        return new ResponseEntity<>(activeAttack, HttpStatus.OK);
    }
}