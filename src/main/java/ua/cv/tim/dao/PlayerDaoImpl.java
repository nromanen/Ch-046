package ua.cv.tim.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.hibernate.PlayerDao;
import ua.cv.tim.dao.hibernate.VillageDao;
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
    public PlayerDaoImpl(Class<Player> clazz) {
        super(clazz);
    }

    @Override
    public void deleteVillageOfPlayer(Player player, Village village) {
        villageDao.delete(village);
    }

    @Override
    public Player getByIdWithVillages(String id) {
        Player player = getById(id);
        List<Village> villages = player.getVillages();
        return player;
    }
}
