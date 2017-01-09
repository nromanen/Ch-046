package ua.cv.tim.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.PlayerDaoImpl;
import ua.cv.tim.dao.VillageDaoImpl;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;

import java.util.List;

/**
 * Created by Oleg on 04.01.2017.
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerDaoImpl playerDaoImpl;
    @Autowired
     VillageDaoImpl villageDaoImpl;

    @Override
    public void add(Player player){
        playerDaoImpl.add(player);
    }

    @Override
    public Player getById(String id){
        Player byId = playerDaoImpl.getById(id);
        return byId;
    }

    public Player getByIdWithVillages(String id){
        Player byIdWithVillages = playerDaoImpl.getByIdWithVillages(id);
        List<Village> villages = byIdWithVillages.getVillages();
        Village village = villages.get(0);
        Hibernate.initialize(byIdWithVillages.getUser().getRoles());
        return byIdWithVillages;
    }

    public void update(Player player){
        playerDaoImpl.update(player);
    }

    public void delete(Player player){
        playerDaoImpl.delete(player);
    }


    public void deleteVillageOfPlayer(Village village) {
        villageDaoImpl.delete(village);
    }
}
