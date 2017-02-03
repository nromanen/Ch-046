package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oleg on 08.01.2017.
 */

@RestController
public class PlayerController {

	private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    VillageService villageService;

    @Autowired
    PlayerService playerService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/player/{id}/village", method = RequestMethod.GET)
    public ResponseEntity<List<Village>> getUsersVillages(@PathVariable(name = "id") String id) {
        Player byIdWithVillages = playerService.getByIdWithVillages(id);
        List<Village> villages = byIdWithVillages.getVillages();
        return new ResponseEntity<>(villages, HttpStatus.OK);
    }

    @RequestMapping(value = "/player", method = RequestMethod.GET)
    public ResponseEntity<PlayerDTO> getPlayerById() {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByUsername = userService.getUserByUsername(principal.getUsername());
        String id = userByUsername.getPlayer().getUuid();
        Player player = playerService.getByIdWithVillages(id);
        for (Village village:player.getVillages()){
            Collections.sort(village.getArmies());
        }
        PlayerDTO playerDTO=new PlayerDTO(player.getUser().getLogin(),
                player.getRace(),player.getVillages(),player.getAlliance());
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/player/", method = RequestMethod.POST)
    public ResponseEntity<Player> addPlayer(@RequestBody Player player, UriComponentsBuilder builder) {
        playerService.add(player);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/player/{id}").buildAndExpand(player.getUser().getUuid()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Player> updatePlayer(@PathVariable(name = "id") String id, @RequestBody Player player) {
        Player current_player = playerService.getById(id);
        if (current_player != null) {
            current_player.setRace(player.getRace());
            current_player.setUser(player.getUser());
            current_player.setVillages(player.getVillages());
            current_player.setAlliance(player.getAlliance());
            playerService.add(current_player);

            return new ResponseEntity<>(current_player, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Player> deletePlayer(@PathVariable(name = "id") String id) {
        Player player = playerService.getById(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.delete(player);
        return new ResponseEntity<>(player, HttpStatus.NO_CONTENT);
    }

	@RequestMapping(value = "/player/alliance", method = RequestMethod.GET) // todo change to get allianceID from principal
	public ResponseEntity<List<PlayerDTO>> getPlayersByAlliance() {
		String allianceName = getAllianceName();  // todo change this after principal will
		log.info("Alliance name: {}", allianceName);
		List<Player> players = playerService.getPlayersByAllianceWithVillages(allianceName);
		log.info("Players from DB: {}", players);
		List<PlayerDTO> playerDTOs = initPlayerDTOs(players);
		return new ResponseEntity<>(playerDTOs, HttpStatus.OK);
	}

	private String getAllianceName() {
		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserWithAlliance(principal.getUsername());
		return user.getPlayer().getAlliance().getName();
	}

	private List<PlayerDTO> initPlayerDTOs(List<Player> players) {
		List<PlayerDTO> playerDTOs = new ArrayList<>();

		for (Player player : players) {
			PlayerDTO playerDTO = new PlayerDTO();
			playerDTO.setLogin(player.getUser().getLogin());
			playerDTO.setRace(player.getRace());
			playerDTO.setAlliance(player.getAlliance());
			playerDTO.setVillages(player.getVillages());
			playerDTOs.add(playerDTO);
		}

		return playerDTOs;
	}
}
