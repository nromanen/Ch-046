package ua.cv.tim.service;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.WebConfigurationTest;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.utils.AllianceServiceTestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by mmaksymtc on 26.01.2017.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfigurationTest.class})
public class AllianceServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(AllianceServiceImplTest.class);

    @Autowired
    AllianceServiceTestUtils allianceServiceTestUtils;
    @Autowired
    private AllianceService allianceService;
    @Autowired
    private AllianceDao allianceDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private UserService userService;

    @Test
    public void getAllTest(){
        assertEquals(allianceService.getAll().size(),1);
        assertEquals(allianceService.getAll().get(0).getName(),"valhala");
    }
    @Transactional
    @Rollback(true)
    @Test
    public void addAllianceTest(){
        AllianceDTO allianceDTO = new AllianceDTO("9876d81e-b4c7-440f-8f55-ea8938ewdf54", "1234d81e-b4c7-440f-8f55-ea8938ec0f37", "alalala", "newLeader", "newLeader@ukr.net");
        allianceService.addAlliance(allianceDTO);
        assertNotNull(allianceServiceTestUtils.getAllianceByName("alalala"));
        assertEquals(allianceServiceTestUtils.getAllAlliances().size(),2);
       // logger.info("allianceServiceTestUtils {}",allianceServiceTestUtils.getAllianceByName("alalala"));
    }
    @Test
    public void getIdByNameTest(){
        assertEquals(allianceService.getIdByName("valhala"),"7564d81e-b4c7-440f-8f55-ea8938ewdf54");
    }
    @Transactional
    @Rollback(true)
    @Test
    public void updateAllianceTest(){
        AllianceDTO allianceDTO = new AllianceDTO("7564d81e-b4c7-440f-8f55-ea8938ewdf54", "6666d81e-b4c7-440f-8f55-ea8938ewdf54", "TRATATAalalala", "allianceLeader", "newLeader@ukr.net");
        allianceService.updateAlliance(allianceDTO);
        assertNotNull(allianceServiceTestUtils.getAllianceByName("TRATATAalalala"));
        assertEquals(allianceServiceTestUtils.getIdByName("TRATATAalalala"),"7564d81e-b4c7-440f-8f55-ea8938ewdf54");
    }
    @Transactional
    @Rollback(true)
    @Test
    public void deleteAllianceTest(){
        allianceService.deleteAlliance("7564d81e-b4c7-440f-8f55-ea8938ewdf54");
        assertNull(allianceServiceTestUtils.getAllianceByName("valhala"));
        assertEquals(allianceServiceTestUtils.getAllAlliances().size(),0);
    }
    @Test
    public void getByIdTest(){
        assertEquals(allianceService.getById("7564d81e-b4c7-440f-8f55-ea8938ewdf54"),allianceServiceTestUtils.getAllianceByName("valhala"));
    }
    @Test
    public void isUniqueAllianceTest(){
        AllianceDTO allianceDTO = new AllianceDTO("9999d81e-b4c7-440f-8f55-ea8938ewdf54", "6666d81e-b4c7-440f-8f55-ea8938ewdf54", "TRATATAalalala", "allianceLeader", "newLeader@ukr.net");
        assertFalse(allianceService.isUniqueAlliance("valhala", null));
        assertTrue(allianceService.isUniqueAlliance("valhala", "7564d81e-b4c7-440f-8f55-ea8938ewdf54"));
        assertTrue(allianceService.isUniqueAlliance("TRATATAalalala", "9999d81e-b4c7-440f-8f55-ea8938ewdf54"));
    }
}
