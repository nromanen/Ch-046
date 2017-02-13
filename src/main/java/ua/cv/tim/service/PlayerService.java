package ua.cv.tim.service;

import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;

import java.util.List;

/**
 * Created by Oleg on 05.01.2017.
 */
public interface PlayerService {
	void add(Player player);

	Player getById(String id);

	Player getByIdWithVillages(String id);

	void update(Player player);

	void delete(Player player);

	List<Player> getPlayersByAllianceWithVillages(String allianceName);
}
