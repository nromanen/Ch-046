package ua.cv.tim.dao;

import ua.cv.tim.model.Army;

/**
 * Created by Serhii Starovoit on 1/5/2017 in 5:32 PM.
 */

public interface ArmyDao extends CrudDao<Army> {
    Army getArmyById(String uuid);
    public Army getById(String id);
}
