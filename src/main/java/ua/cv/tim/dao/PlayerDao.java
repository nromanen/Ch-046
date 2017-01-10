package ua.cv.tim.dao;

import ua.cv.tim.dao.CrudDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;

/**
 * Created by Oleg on 05.01.2017.
 */
public interface PlayerDao extends CrudDao<Player> {
    void deleteVillageOfPlayer(Player player, Village village);
    Player getById(String id);
    Player getByIdWithVillages(String id);
}
