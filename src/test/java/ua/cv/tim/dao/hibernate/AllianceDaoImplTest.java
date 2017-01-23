package ua.cv.tim.dao.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by rmochetc on 23.01.2017.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class AllianceDaoImplTest {



    @Autowired
    private AllianceDaoImpl allianceDao;

    @Test
    public void testGetById() throws Exception {
        Alliance alliance = new Alliance();
        alliance.setName("Alliance_Test");
        alliance.setUuid("ekke-dscd-s3cs-dde4");

        User user1 = new User();
        User user2 = new User();
        user1.setLogin("testUser1");
        user2.setLogin("testUser2");
        user1.setEmail("user1testemail@mail.com");
        user2.setEmail("user2testemail@mail.com");
        user1.setPassword("111");
        user2.setPassword("111");

        List<Role> role1 = new ArrayList<>();
        role1.add(Role.LEADER);
        role1.add(Role.USER);

        List<Role> role2 = new ArrayList<>();
        role2.add(Role.USER);

        user1.setRoles(role1);
        user2.setRoles(role2);

        Player player1 = new Player();
        player1.setUuid("hhdj-rnbf-443k-s3ld");
        player1.setRace(Race.GAULS);
        player1.setLastModified(new Date());
        player1.setAlliance(alliance);
        player1.setUser(user1);

        Player player2 = new Player();
        player2.setUuid("hhdj-rnbf-443k-esgw");
        player2.setRace(Race.ROMANS);
        player2.setLastModified(new Date());
        player2.setAlliance(alliance);
        player2.setUser(user2);

        user1.setPlayer(player1);
        user2.setPlayer(player2);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        alliance.setPlayers(players);

        System.out.println(alliance);

        allianceDao.add(alliance);


    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testGetIdByName() throws Exception {

    }

    @Test
    public void testGetByName() throws Exception {

    }

}