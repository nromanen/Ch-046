package ua.cv.tim.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;

import ua.cv.tim.model.Player;
import ua.cv.tim.model.Race;
import ua.cv.tim.model.User;
import ua.cv.tim.model.Village;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Oleg on 04.01.2017.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class PlayerServiceImplTest extends AbstractTestNGSpringContextTests {
    @Test
    public void testUpdate() throws Exception {
        Player lkl = playerService.getById("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        lkl.setRace(Race.GAULS);
        playerService.update(lkl);
    }


    @Autowired
    PlayerService playerService;




    @Test
    public void testAdd() throws Exception {

        Village village=new Village();
        List<Village> villages=new ArrayList<>();
        Player player=new Player();
        player.setRace(Race.GAULS);
        player.setVillages(villages);
        User user=new User();
        user.setUuid("1e2381a4-6e22-457f-875d-932f546b1c08");
        player.setUser(user);
        village.setPlayer(player);
        village.setxCoord((short) 56);
        village.setyCoord((short) 58);
        villages.add(village);
        playerService.add(player);

    }

   @Test
    public void testGetById() throws Exception {
       Player lkl = playerService.getById("77e0708b-0207-46e3-b521-b2ed1998e2b6");
       List<Village> villages = lkl.getVillages();

   }

    @Test
    public void testGetByIdWithVillages() throws Exception {
        Player byIdWithVillages = playerService.getByIdWithVillages("77e0708b-0207-46e3-b521-b2ed1998e2b6");
        List<Village> villages =  byIdWithVillages.getVillages();


    }


    @Test
    public void testDelete() throws Exception {
        Player player = playerService.getById("77e0708b-0207-46e3-b521-b2ed1998e2b6");
        playerService.delete(player);
    }

    @Test
    public void testDeleteVillage() throws Exception {
        Player player= playerService.getById("lkl");
        Village village=new Village();
        village.setUuid("ee65fa8f-472b-4020-ad74-1a6c291afea2");
        village.setPlayer(player);
        village.setxCoord((short) 56);
        village.setyCoord((short) 58);
        playerService.deleteVillageOfPlayer(village);
    }


}