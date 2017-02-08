package ua.cv.tim.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.configuration.WebSocketConfiguration;
import ua.cv.tim.service.impl.Parser;


/**
 * Created by Serhii Starovoit on 1/26/2017 in 12:03 AM.
 */

@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class, WebSocketConfiguration.class})
public class ParserTest extends AbstractTestNGSpringContextTests {

    @Autowired
    Parser parser;

//   "roma_ariezz@ukr.net","1123581321"

    @Test
    public void testDoOperation() {
        parser.doOperation("Star","321654aaa");
    }

}