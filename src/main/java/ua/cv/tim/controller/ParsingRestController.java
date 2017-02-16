package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cv.tim.dto.CredentialsDTO;
import ua.cv.tim.service.impl.Parser;


/**
 * Created by Micro on 2/13/2017.
 */
@RestController
public class ParsingRestController {
    private static final Logger log = LoggerFactory.getLogger(ParsingRestController.class);

    @Autowired
    Parser parser;

    @RequestMapping(value = "parser", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity pars(@RequestBody CredentialsDTO credentialsDTO){
        try {
            parser.doOperation(credentialsDTO.getLogin(), credentialsDTO.getPassword());
        } catch (Exception e) {
            log.error("Exception: {}", e);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


}
