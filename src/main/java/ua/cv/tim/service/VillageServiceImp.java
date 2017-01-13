package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.Village;

/**
 * Created by Oleg on 05.01.2017.
 */

@Service
@Transactional
public class VillageServiceImp implements  VillageService{
    @Autowired
    private VillageDao villageDao;

    @Override
    public void add(Village village) {

    }

    @Override
    public void update(Village village) {

    }

    @Override
    public void delete(Village village) {

    }

    @Override
    public Village getById(String id) {
        return null;
    }

//    @Override
//    public void add(Village village){
//        villageDao.add(village);
//    }
//
//    @Override
//    public void update(Village village){
//        villageDao.update(village);
//    }
//
//    @Override
//    public void delete(Village village){
//        villageDao.delete(village);
//    }
//
//    @Override
//    public Village getById(String id){
//       return villageDao.getById(id);
//    }
}
