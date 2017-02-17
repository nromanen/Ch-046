package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.model.User;
import ua.cv.tim.service.AllianceService;
import ua.cv.tim.service.UserService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * Created by rmochetc on 30.12.2016.
 */

@RestController
public class AllianceController {

    @Autowired
    private AllianceService allianceService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/allianceDTO", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<AllianceDTO>> listAllAlliances() {
        List<AllianceDTO> alliances = allianceService.getAll();
        if (alliances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(alliances, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/allianceDTO", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<AllianceDTO> createAlliance(@RequestBody @Valid AllianceDTO allianceDTO) throws MessagingException, InvocationTargetException {

        User user = new User();
        user.setLogin(allianceDTO.getLeaderLogin());
        user.setEmail(allianceDTO.getLeaderEmail());
        if (userService.isUnique(user) && allianceService.isUniqueAlliance(allianceDTO.getName(), null)) {

            allianceService.addAlliance(allianceDTO);
            allianceDTO.setAllianceUuid(allianceService.getByName(allianceDTO.getName()).getUuid());
        }
        return new ResponseEntity<>(allianceDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/allianceDTO/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<AllianceDTO> updateAlliance(@PathVariable("id") String uuid, @RequestBody @Valid AllianceDTO allianceDTO) throws MessagingException, InvocationTargetException {

        Alliance updatedAlliance = allianceService.getById(uuid);
        if (updatedAlliance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = new User();
        user.setLogin(allianceDTO.getLeaderLogin());
        user.setEmail(allianceDTO.getLeaderEmail());
        user.setUuid(allianceDTO.getLeaderUuid());
        if (userService.isUnique(user) && allianceService.isUniqueAlliance(allianceDTO.getName(), uuid)) {
            allianceDTO.setAllianceUuid(uuid);
            allianceService.updateAlliance(allianceDTO);
        }
        return new ResponseEntity<>(allianceDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/allianceDTO/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<AllianceDTO> deleteAlliance(@PathVariable("id") String uuid) {
        if (allianceService.getById(uuid) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        allianceService.deleteAlliance(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



