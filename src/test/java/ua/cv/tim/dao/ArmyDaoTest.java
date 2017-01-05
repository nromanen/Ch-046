package ua.cv.tim.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import ua.cv.tim.dao.hibernate.ArmyDao;
import ua.cv.tim.model.Army;

/**
 * Created by Serhii Starovoit on 1/5/2017 in 7:50 PM.
 */
public class ArmyDaoTest {

    @Autowired
    private ArmyDao armyDao;

    @Test
    public void armyAddTest() {
        Army army = new Army();
        army.setCount(2);

        armyDao.add(army);

    }
}
