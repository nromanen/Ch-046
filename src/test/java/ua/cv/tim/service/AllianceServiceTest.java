package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.model.Alliance;

import static org.testng.Assert.*;

/**
 * Created by okunetc on 13.01.2017.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class AllianceServiceTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    AllianceService allianceService;
    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testAddAlliane() throws Exception {

    }

    @Test
    public void testUpdateAlliance() throws Exception {

    }

    @Test
    public void testDeleteAlliance() throws Exception {
        Alliance alliance = allianceService.getById("ac2caca5-cd27-4565-8a95-917980cf90ce");
        allianceService.deleteAlliance("ac2caca5-cd27-4565-8a95-917980cf90ce");
    }

    @Test
    public void testGetById() throws Exception {
        Alliance alliance = allianceService.getById("ac2caca5-cd27-4565-8a95-917980cf90ce");
    }

}