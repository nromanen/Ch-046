package ua.cv.tim.service.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.PlayerService;

import java.util.List;

/**
 * Created by Oleg on 04.01.2017.
 */
@Service(value = "playerService")
@Transactional
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerDao playerDao;
    @Autowired
    VillageDao villageDao;

    @Override
    public void add(Player player){
        playerDao.add(player);
    }

    @Override
    public Player getById(String id){
        Player byId = playerDao.getById(id);
        return byId;
    }

    public Player getByIdWithVillages(String id){
        Player byIdWithVillages = playerDao.getByIdWithVillages(id);
        return byIdWithVillages;
    }

    public void update(Player player){
        playerDao.update(player);
    }

    public void delete(Player player){
        playerDao.delete(player);
    }

    public void deleteVillageOfPlayer(Village village) {
        villageDao.delete(village);
    }

    @Override
    public List<Player> getPlayersByAllianceWithVillages(String allianceName) {
        return playerDao.getPlayersByAllianceWithVillages(allianceName);
    }
}
