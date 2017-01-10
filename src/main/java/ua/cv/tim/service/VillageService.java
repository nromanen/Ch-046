package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.Village;

/**
 * Created by Oleg on 05.01.2017.
 */
@Transactional
@Service
public class VillageService {
    @Autowired
    private VillageDao villageDao;

    public void add(Village village){
        villageDao.add(village);
    }

    public void update(Village village){
        villageDao.update(village);
    }

    public void delete(Village village){
        villageDao.delete(village);
    }

    public Village getById(String id){
       return villageDao.getById(id);
    }
}
