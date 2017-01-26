package ua.cv.tim.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.model.Army;
import ua.cv.tim.model.Village;

import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

/**
 * Created by Oleg on 05.01.2017.
 */
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class VillageServiceImpTest extends AbstractTestNGSpringContextTests{
    @Autowired
    PlayerService playerServiceImpl;
    @Autowired
    VillageService villageService;
    @Autowired
    ArmyService armyService;
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
        village.setPlayer(playerServiceImpl.getById("77e0708b-0207-46e3-b521-b2ed1998e2b6"));
        String village_id = village.getUuid();
        villageService.add(village);

    }

    @Test
    public void testUpdate() throws Exception {
        Village village = villageService.getById("1");
//        village.setName("chernivtsi");
//        Army armyById = armyService.getArmyById("7564d81e-b4c7-440f-8f55-ea8938ec0f11");
//        armyById.setCount(5);
//        armyService.update(armyById);
         village.getArmies().get(0).setCount(20);
        villageService.update(village);


    }

    @Test
    public void testDelete() throws Exception {
        Village village = villageService.getById("1e70681a-6e67-4d7e-b66b-475df975c9f7");
        villageService.delete(village);
    }

    @Test
    public void testGetById() throws Exception {
        Village village = villageService.getById("5dd0b0f2-810e-44d0-b11b-66d2e0f32bbe");

    }

}