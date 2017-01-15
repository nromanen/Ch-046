package ua.cv.tim.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.cv.tim.configuration.HibernateConfiguration;

import static org.junit.Assert.*;

/**
 * Created by okunetc on 12.01.2017.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class ArmyServiceImplTest {
    @Autowired
    UserService userService;
    @Test
    public void add() throws Exception {

    }

}