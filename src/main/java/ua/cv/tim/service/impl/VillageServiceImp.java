package ua.cv.tim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.VillageService;

/**
 * Created by Oleg on 05.01.2017.
 */

@Service(value = "villageService")
@Transactional
public class VillageServiceImp implements VillageService {
    @Autowired
    private VillageDao villageDao;

    @Override
    public void add(Village village){
        villageDao.add(village);
    }

    @Override
    public void update(Village village){
        villageDao.update(village);
    }

    @Override
    public void delete(Village village){
        villageDao.delete(village);
    }

    @Override
    public Village getById(String id){
       return villageDao.getById(id);
    }

    @Override
    public Village getByCoordinates(short xCoord, short yCoord) {
        return villageDao.getByCoordinates(xCoord,yCoord);
    }

    @Override
    public boolean isUnique(Village village) {
        boolean[] nameAndCoordUnique=new boolean[2];
        Village comparingVillageByCoord = getByCoordinates(village.getxCoord(),village.getyCoord());
        Village comparingVillageByName = getByName(village.getName());
        nameAndCoordUnique[0] = comparingVillageByCoord == null || comparingVillageByCoord.getUuid().equals(village.getUuid());
        nameAndCoordUnique[1] =comparingVillageByName == null || comparingVillageByName.getUuid().equals(village.getUuid());

        String errorMessage = createErrorMessage(nameAndCoordUnique);
        if (errorMessage != null) {
            throw new IllegalArgumentException(errorMessage);
        }
        return true;
    }

    private String createErrorMessage(boolean[] nameAndCoordinatesUnique) {
        String errorMessage = null;
        if (!nameAndCoordinatesUnique[0] && !nameAndCoordinatesUnique[1]) {
            errorMessage = "Village with the same name and coordinates already exists!";
        } else if (!nameAndCoordinatesUnique[1]) {
            errorMessage = "Village with the same name  already exists!";
        }
        else if (!nameAndCoordinatesUnique[0]){
            errorMessage="Village with the same coordinates already exists!";
        }
        return errorMessage;
    }

    @Override
    public Village getByName(String name) {
        return villageDao.getByName(name);
    }
}
