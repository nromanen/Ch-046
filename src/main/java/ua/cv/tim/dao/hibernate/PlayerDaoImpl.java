package ua.cv.tim.dao.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;

import java.util.List;

/**
 * Created by Oleg on 04.01.2017.
 */
@Repository
public class PlayerDaoImpl extends AbstractCrudDao<Player> implements PlayerDao {

@Autowired
VillageDao villageDao;


    @Override
    public void deleteVillageOfPlayer(Player player, Village village) {
        villageDao.delete(village);
    }

    @Override
    public Player getById(String id) {
        return (Player) getCurrentSession().get(Player.class,id);
    }

    @Override
    public Player getByIdWithVillages(String id) {
        Player player = getById(id);
        List<Village> villages = player.getVillages();
        return player;
    }
}
