package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.cv.tim.dto.PlayerDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.service.VillageService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Oleg on 08.01.2017.
 */

@RestController
public class HelpController {


    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    private UserService userService;

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/askhelp", method = RequestMethod.GET)
    public ResponseEntity<PlayerDTO> getPlayerById() {

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


}