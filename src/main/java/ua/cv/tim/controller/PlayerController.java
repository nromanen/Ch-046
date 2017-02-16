package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.cv.tim.dto.PlayerDTO;
import ua.cv.tim.model.AuthorizedUser;
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

	@RequestMapping(value = "/player/{id}/village", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Village>> getUsersVillages(@PathVariable(name = "id") String id) {
		Player byIdWithVillages = playerService.getByIdWithVillages(id);
		List<Village> villages = byIdWithVillages.getVillages();
		return new ResponseEntity<>(villages, HttpStatus.OK);
	}

    /**
     *
     * @return player with villages in JSON-format.
     */
    @RequestMapping(value = "/player", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PlayerDTO> getPlayerById() {

		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userByUsername = userService.getUserByUsername(principal.getUsername());
		String id = userByUsername.getPlayer().getUuid();

		Player player = playerService.getByIdWithVillages(id);
		for (Village village : player.getVillages()) {
			Collections.sort(village.getArmies());
		}
		PlayerDTO playerDTO = new PlayerDTO(player.getUser().getLogin(),
				player.getRace(), player.getVillages(), player.getAlliance(), userByUsername.getRoles());
		log.info(playerDTO.toString());
		return new ResponseEntity<>(playerDTO, HttpStatus.OK);
	}

    /**
     * Adds new player in a database
     * @param player player in JSON-format
     * @param builder
     * @return created player.
     */
    @RequestMapping(value = "/player/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Player> addPlayer(@RequestBody Player player, UriComponentsBuilder builder) {
        playerService.add(player);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/player/{id}").buildAndExpand(player.getUser().getUuid()).toUri());
        log.info(player.toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     *
     * @param id
     * @param player
     * @return updated player.
     */
    @RequestMapping(value = "/player/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Player> updatePlayer(@PathVariable(name = "id") String id, @RequestBody Player player) {
        Player currentPlayer = playerService.getById(id);
        if (currentPlayer != null) {
            currentPlayer.setRace(player.getRace());
            currentPlayer.setUser(player.getUser());
            currentPlayer.setVillages(player.getVillages());
            currentPlayer.setAlliance(player.getAlliance());
            playerService.add(currentPlayer);
            return new ResponseEntity<>(currentPlayer, HttpStatus.CREATED);
        }
        throw new IllegalArgumentException("Player doesn't exist");
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Player> deletePlayer(@PathVariable(name = "id") String id) {
        Player player = playerService.getById(id);
        if (player == null) {
             throw new IllegalArgumentException("Player with entered id does not exist!");
        }
        playerService.delete(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

	@RequestMapping(value = "/player/alliance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<PlayerDTO>> getPlayersByAlliance() {
		AuthorizedUser principal = (AuthorizedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Player> players = playerService.getPlayersByAllianceWithVillages(principal.getAlliance().getName());
		log.info("Players from DB: {}", players);
		List<PlayerDTO> playerDTOs = initPlayerDTOs(players);
		return new ResponseEntity<>(playerDTOs, HttpStatus.OK);
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
