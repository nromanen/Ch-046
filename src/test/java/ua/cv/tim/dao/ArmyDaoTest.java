package ua.cv.tim.dao;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.cv.tim.TestHibernateConfiguration;
import ua.cv.tim.dao.hibernate.ArmyDao;
import ua.cv.tim.model.Army;

/**
 * Created by Serhii Starovoit on 1/5/2017 in 7:50 PM.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestHibernateConfiguration.class})
public class ArmyDaoTest extends AbstractJUnit4SpringContextTests {



    @Autowired
    ArmyDao armyDao;
    @org.junit.Test
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
        Assert.assertEquals(army3, armyDao.getArmyById(army3.getUuid()));
    }




}
