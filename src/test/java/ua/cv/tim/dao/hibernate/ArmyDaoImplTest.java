package ua.cv.tim.dao.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.configuration.WebConfiguration;
import ua.cv.tim.dao.ArmyDao;
import ua.cv.tim.model.Army;

/**
 * Created by Serhii Starovoit on 1/5/2017 in 7:50 PM.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class, WebConfiguration.class})
public class ArmyDaoImplTest extends AbstractJUnit4SpringContextTests {



    @Autowired
    ArmyDao armyDao;
    @Test
    public void armyAddTest() {
        Army army1 = new Army();
        Army army2 = new Army();
        Army army3 = new Army();
        Army army4 = new Army();
        army1.setCount(2);
        armyDao.add(army1);
        armyDao.add(army2);
        armyDao.add(army3);
        armyDao.add(army4);
        army1.setCount(45);
        armyDao.update(army1);
        armyDao.delete(army2);
//        Assert.assertEquals(army3, armyDao.getArmyById(army3.getUuid()));
    }
}