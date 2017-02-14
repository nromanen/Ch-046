package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public String pars(@RequestBody CredentialsDTO credentialsDTO){
        parser.doOperation(credentialsDTO.getLogin(), credentialsDTO.getPassword());
        return "parsing.html";
    }


}
