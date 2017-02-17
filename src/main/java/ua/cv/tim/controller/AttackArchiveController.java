package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.cv.tim.dto.AttackArchiveDTO;
import ua.cv.tim.dto.AttackDTO;
import ua.cv.tim.model.AttackArchive;
import ua.cv.tim.service.AttackArchiveService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 14.02.2017.
 */
@RestController
public class AttackArchiveController {

    private static final Logger logger = LoggerFactory.getLogger(AttackArchiveController.class);
    @Autowired
    private AttackArchiveService attackArchiveService;

    @RequestMapping(value = "/user/attackArchive", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AttackArchiveDTO>> listAllAttackArchive() {
        List<AttackArchive> archive = attackArchiveService.getAll();
        if (archive.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<AttackArchiveDTO> archiveDTOS = getAttackArchiveDTO(archive);

        logger.info("Attack archives:  {}", archiveDTOS);
        return new ResponseEntity<>(archiveDTOS, HttpStatus.OK);
    }

    private List<AttackArchiveDTO> getAttackArchiveDTO(List<AttackArchive> archive){

        List<AttackArchiveDTO> result = new ArrayList<>();

        for (AttackArchive attackArchive: archive){
           result.add(new AttackArchiveDTO(attackArchive.getUuid(), attackArchive.getLastModified().toString().substring(0,10)));
        }
        return  result;
    }

    @RequestMapping(value = "/user/attack/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AttackDTO>> listAttackArchive(@PathVariable String id) throws IOException {

        List<AttackDTO> attack = attackArchiveService.getById(id);

        if (attack.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Attack archives:  {}", attack);
        return new ResponseEntity<>(attack, HttpStatus.OK);
    }

}
