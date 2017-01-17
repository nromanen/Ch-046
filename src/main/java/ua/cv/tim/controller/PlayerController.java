package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.cv.tim.dto.PlayerDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.VillageService;

import java.util.List;

/**
 * Created by Oleg on 08.01.2017.
 */

@RestController
public class PlayerController {
    @Autowired
    VillageService villageServiceImp;

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/player/{id}/village", method = RequestMethod.GET)
    public ResponseEntity<List<Village>> getUsersVillages(@PathVariable(name = "id") String id) {
        Player byIdWithVillages = playerService.getByIdWithVillages(id);
        List<Village> villages = byIdWithVillages.getVillages();
        return new ResponseEntity<>(villages, HttpStatus.OK);
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET)
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable(name = "id") String id) {
        Player player = playerService.getByIdWithVillages(id);
        PlayerDTO playerDTO=new PlayerDTO(player.getUser().getLogin(),
                player.getUser().getPassword(),player.getUser().getEmail(),
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
}
