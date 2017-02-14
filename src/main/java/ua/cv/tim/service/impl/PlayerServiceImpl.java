package ua.cv.tim.service.impl;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 04.01.2017.
 */
@Service(value = "playerService")
@Transactional
public class PlayerServiceImpl implements PlayerService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
    @Autowired
    PlayerDao playerDao;
    @Autowired
    VillageDao villageDao;

    @Override
    public void add(Player player){
        playerDao.add(player);
        logger.info("Player with login {} and id {} was added successfully.",player.getUser().getLogin(), player.getUuid());
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

    public void update(Player player)    {
        playerDao.update(player);
        logger.info("Player with login {} and id {} was updated successfully.",player.getUser().getLogin(),player.getUuid());
    }

    public void delete(Player player)    {
        playerDao.delete(player);
        logger.info("Player with login {} and id {} was deleted successfully.",player.getUser().getLogin(),player.getUuid());
    }

    @Override
    public List<Player> getPlayersByAllianceWithVillages(String allianceName) {
        List<Player> players = playerDao.getPlayersByAllianceWithVillages(allianceName);
        logger.info("Alliance {} has players {}",allianceName,players);
        return players;
    }
}
