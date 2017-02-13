package ua.cv.tim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.ArmyDao;
import ua.cv.tim.model.Army;
import ua.cv.tim.service.ArmyService;

/**
 * Created by Serhii Starovoit on 1/5/2017 in 7:31 PM.
 */
@Service("armyService")
@Transactional
public class ArmyServiceImpl implements ArmyService {
    private static final Logger logger = LoggerFactory.getLogger(ArmyService.class);
    @Autowired
    private ArmyDao armyDao;

    @Override
    public void add(Army army) {

        armyDao.add(army);
        logger.info("New army added successfully: {}", army);
    }

    @Override
    public void update(Army army) {
        armyDao.update(army);
        logger.info("Army {}, upadted successfully: ", army);
    }

    @Override
    public void delete(Army army) {
        armyDao.delete(army);
        logger.info("Army {}, deleted successfully: ", army);
    }

    @Override
    public Army getArmyById(String uuid) {
        return armyDao.getArmyById(uuid);
    }

    @Override
    public Army getById(String uuid)  {
        return armyDao.getById(uuid);
    }
}
