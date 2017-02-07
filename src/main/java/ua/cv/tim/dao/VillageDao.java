package ua.cv.tim.dao;

import ua.cv.tim.dao.CrudDao;
import ua.cv.tim.model.Village;

/**
 * Created by Oleg on 05.01.2017.
 */
public interface VillageDao extends CrudDao<Village> {
    Village getById(String id);
    Village getByCoordinates(short xCoort, short yCoord);
    Village getByName(String name);
}
