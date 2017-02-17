package ua.cv.tim.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ua.cv.tim.exception.EntityNotUniqueException;
import ua.cv.tim.model.User;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.UserService;
import ua.cv.tim.service.VillageService;

import java.util.Collections;

/**
 * Created by Oleg on 08.01.2017.
 */

@RestController

public class VillagesController {
    private static final Logger log = LoggerFactory.getLogger(VillagesController.class);
    @Autowired
    VillageService villageService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/village/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Village> getVillageById(@PathVariable(name = "id") String id) {
        Village village = villageService.getById(id);
        if (village == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(village, HttpStatus.OK);
    }

    /**
     * Adds new village in a database.
     * @param village
     * @return added village.
     * @throws JsonProcessingException
     * @throws EntityNotUniqueException
     */
    @RequestMapping(value = "/village/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("authenticated")
    public ResponseEntity<Village> addVillage(@RequestBody Village village) throws JsonProcessingException, EntityNotUniqueException {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByUsername = userService.getUserByUsername(principal.getUsername());
        village.setPlayer(userByUsername.getPlayer());

        if (villageService.isUnique(village)) {
            villageService.add(village);
            log.info("Village added : {}",village);
        }
        return new ResponseEntity<>(village, HttpStatus.CREATED);
    }

    /**
     * Updates village.
     * @param id
     * @param village
     * @return updated village.
     */
    @RequestMapping(value = "/village/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Village> updateVillage(@PathVariable(name = "id") String id, @RequestBody Village village) {
        Village current_village = villageService.getById(id);
        if (current_village != null) {
            current_village.setName(village.getName());
            current_village.setxCoord(village.getxCoord());
            current_village.setyCoord(village.getyCoord());
            current_village.setPopulation(village.getPopulation());
            current_village.setWall(village.getWall());
            current_village.setIsCapital(village.getIsCapital());
            current_village.setUuid(village.getUuid());
            Collections.sort(village.getArmies());
            current_village.setArmies(village.getArmies());
            if (villageService.isUnique(current_village)) {
                villageService.update(current_village);
                log.info("Village updated : {}",current_village);
            }
            return new ResponseEntity<>(current_village, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/village/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Village> deleteVillage(@PathVariable(name = "id") String id) {
        Village Village = villageService.getById(id);
        if (Village == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        villageService.delete(Village);
        return new ResponseEntity<>(Village, HttpStatus.NO_CONTENT);
    }
}
