package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cv.tim.dao.hibernate.ArmyDao;
import ua.cv.tim.model.Army;

/**
 * Created by Serhii Starovoit on 1/5/2017 in 7:31 PM.
 */
@Service("armyService")
public class ArmyServiceImpl implements ArmyService {

    @Autowired
    private ArmyDao armyDao;

    @Override
    public void add(Army army) {
        armyDao.add(army);
    }

    @Override
    public void update(Army army) {
        armyDao.update(army);
    }

    @Override
    public void delete(Army army) {
        armyDao.delete(army);
    }


    public void setArmyDao(ArmyDao armyDao) {
        this.armyDao = armyDao;
    }
}
