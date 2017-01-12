package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.model.Village;

import static org.testng.Assert.assertEquals;

/**
 * Created by Oleg on 05.01.2017.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class VillageServiceTest {
    @Autowired
    PlayerService playerServiceImpl;
    @Autowired
    VillageService villageService;

    @Test
    public void testAdd() throws Exception {
        Village village = new Village();
        village.setxCoord((short) 56);
        village.setyCoord((short) 85);
        village.setName("Village1");
        village.setWall((byte) 25);
        village.setIsCapital(true);
        village.setUuid("5dd0b0f2-810e-44d0-b11b-66d2e0f32bbe");
        village.setPopulation((short) 98);
        village.setPlayer(playerServiceImpl.getById("4a08fc7d-f32a-4524-af12-438c4206d4b6"));
        String village_id = village.getUuid();
        villageService.add(village);
        assertEquals(village, villageService.getById(village_id));
    }

    @Test
    public void testUpdate() throws Exception {
        Village village = villageService.getById("5dd0b0f2-810e-44d0-b11b-66d2e0f32bbe");
        village.setName("chernivtsi");
        villageService.update(village);

    }

    @Test
    public void testDelete() throws Exception {
        Village village = villageService.getById("5dd0b0f2-810e-44d0-b11b-66d2e0f32bbe");
        villageService.delete(village);
    }

    @Test
    public void testGetById() throws Exception {
        Village village = villageService.getById("5dd0b0f2-810e-44d0-b11b-66d2e0f32bbe");

    }

}