package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.service.AllianceService;
import java.util.List;


/**
 * Created by rmochetc on 30.12.2016.
 */

@RestController
public class AllianceController {

    @Autowired
    private AllianceService service;

    @RequestMapping(value = "/allianceDTO", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AllianceDTO>> listAllAlliances() {
        List<AllianceDTO> alliances = service.getAll();
        if(alliances.isEmpty()){
            return new ResponseEntity<List<AllianceDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<AllianceDTO>>(alliances, HttpStatus.OK);
    }

    @RequestMapping(value = "/allianceDTO", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AllianceDTO> createAlliance(@RequestBody AllianceDTO allianceDTO) {
        service.addAlliane(allianceDTO);
        return new ResponseEntity<AllianceDTO>(allianceDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/allianceDTO/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AllianceDTO> updateAlliance(@PathVariable("id") String uuid, @RequestBody AllianceDTO allianceDTO) {

        if (service.getById(uuid) == null){
            return new ResponseEntity<AllianceDTO>(HttpStatus.NOT_FOUND);
        }
        allianceDTO.setUuid(uuid);
        service.updateAlliance(allianceDTO);
        return new ResponseEntity<AllianceDTO>(allianceDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/allianceDTO/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AllianceDTO> deleteAlliance(@PathVariable("id") String uuid) {

        if (service.getById(uuid) == null){
            return new ResponseEntity<AllianceDTO>(HttpStatus.NOT_FOUND);
        }
        service.deleteAlliance(uuid);
        return new ResponseEntity<AllianceDTO>(HttpStatus.NO_CONTENT);
    }
}



