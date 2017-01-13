package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.model.Army;

/**
 * Created by okunetc on 12.01.2017.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class ArmyServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ArmyService armyService;


    @Test
    public void getArmyById() {
        armyService.getArmyById("4046c02c-24e5-486b-8127-2848da877ba6");
        System.out.print(armyService.getArmyById("4046c02c-24e5-486b-8127-2848da877ba6"));
    }


    @Test
    public void add() throws Exception {
        Army army = new Army();
        army.setCount(23);
        armyService.add(army);
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }


}

