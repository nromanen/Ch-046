package ua.cv.tim.service;

import ua.cv.tim.model.Village;

/**
 * Created by okunetc on 13.01.2017.
 */
public interface VillageService {
	void add(Village village);

	void update(Village village);

	void delete(Village village);

	Village getById(String id);

	Village getByCoordinates(short xCoord, short yCoord);

	boolean isUnique(Village village);

	Village getByName(String name);

}
