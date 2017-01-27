package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.cv.tim.service.impl.ParserImpl;

/**
 * Created by Serhii Starovoit on 1/23/2017 in 8:35 PM.
 */
@Controller
@RequestMapping(value = "parser")
public class ParsingController {

    @Autowired
    ParserImpl parserImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String loginParserPage() {
        parserImpl.doOperation("Star", "321654aaa");
        return "parsing.html";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String pars(@RequestParam ("username" ) String name , @RequestParam ("password" ) String password ){
        parserImpl.doOperation(name, password);
        return "parsing.html";
    }




}