package ua.cv.tim.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.configuration.HibernateTestConfig;
import ua.cv.tim.dao.PlayerDaoImpl;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class PlayerServiceImplTest {
    @Test
    public void testUpdate() throws Exception {
        Player lkl = playerServiceImpl.getById("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        lkl.setRace(Race.ROMANS);
        playerServiceImpl.update(lkl);
    }


    @Autowired
    PlayerServiceImpl playerServiceImpl;




    @Test
    public void testAdd() throws Exception {

        Village village=new Village();
        List<Village> villages=new ArrayList<>();
        Player player=new Player();
        player.setRace(Race.GAULS);
        player.setVillages(villages);
        User user=new User();
        user.setUuid("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        player.setUser(user);
        village.setPlayer(player);
        village.setxCoord((short) 56);
        village.setyCoord((short) 58);
        villages.add(village);
        playerServiceImpl.add(player);

    }

   @Test
    public void testGetById() throws Exception {
       Player lkl = playerServiceImpl.getById("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
       List<Village> villages = lkl.getVillages();

   }

    @Test
    public void testGetByIdWithVillages() throws Exception {
        Player byIdWithVillages = playerServiceImpl.getByIdWithVillages("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        List<Village> villages =  byIdWithVillages.getVillages();
    }


    @Test
    public void testDelete() throws Exception {
        Player player = playerServiceImpl.getById("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        playerServiceImpl.delete(player);
    }

    @Test
    public void testDeleteVillage() throws Exception {
        Player player= playerServiceImpl.getById("lkl");
        Village village=new Village();
        village.setUuid("ee65fa8f-472b-4020-ad74-1a6c291afea2");
        village.setPlayer(player);
        village.setxCoord((short) 56);
        village.setyCoord((short) 58);
        playerServiceImpl.deleteVillageOfPlayer(player,village);
    }


}