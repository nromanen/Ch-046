package ua.cv.tim.dao;

import ua.cv.tim.model.Player;

import java.util.List;

/**
 * Created by Oleg on 05.01.2017.
 */
public interface PlayerDao extends CrudDao<Player> {

	Player getById(String id);

	Player getByIdWithVillages(String id);

	List<Player> getPlayersByAllianceWithVillages(String allianceName);
}
