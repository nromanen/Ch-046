package ua.cv.tim.service;

import ua.cv.tim.model.Army;

/**
 * Created by Serhii Starovoit on 1/5/2017 in 7:30 PM.
 */

public interface ArmyService {

	void add(Army army);

	void update(Army army);

	void delete(Army army);

	Army getArmyById(String uuid);

	Army getById(String uuid);
}
