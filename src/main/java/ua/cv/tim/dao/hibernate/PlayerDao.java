package ua.cv.tim.dao.hibernate;

import ua.cv.tim.dao.CrudDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;

/**
 * Created by Oleg on 05.01.2017.
 */
public interface PlayerDao extends CrudDao<Player> {
    void deleteVillageOfPlayer(Player player, Village village);

    Player getByIdWithVillages(String id);
}
