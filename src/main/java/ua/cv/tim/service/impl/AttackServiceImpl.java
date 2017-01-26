package ua.cv.tim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dto.AttackDTO;
import ua.cv.tim.model.Attack;
import ua.cv.tim.service.AttackService;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.VillageService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rmochetc on 22.01.2017.
 */
@Service
@Transactional
public class AttackServiceImpl implements AttackService {

    private static List<Attack> attacks = new ArrayList<>();

    @Autowired
    VillageService villageService;

    @Autowired
    PlayerService playerService;


    @Override
    public List<Attack> getAll() {

        return attacks;
    }

    @Override
    public void addAttack(AttackDTO attack) {
        Attack newAttack = new Attack();

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").parse(attack.getAttackTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        newAttack.setUuid("newAtt" + (int)(Math.random()*100000));
        newAttack.setLastModified(new Date());
        newAttack.setAttackTime(date);
        newAttack.setEnemy(attack.getEnemy());
        newAttack.setOwner(playerService.getById(attack.getPlayerId()));
        newAttack.setVillage(villageService.getById(attack.getVillageId()));

        attacks.add(newAttack);
    }

    @Override
    public void deleteAAttack(String id) {

    }

    @Override
    public Attack getById(String uuid) {

        return null;
    }

    @Override
    public void updateAAttack(Attack attack) {

    }

    @Override
    public List<AttackDTO> getActive() {

        List<AttackDTO> activeAttacks = new ArrayList<>();
        AttackDTO attackDTO = new AttackDTO();

        for (Attack attack: attacks){
            if(attack.getAttackTime().after(new Date())){
                attackDTO.setUuid(attack.getUuid());
                attackDTO.setEnemy(attack.getEnemy());
                attackDTO.setAttackTime(attack.getAttackTime().toString());
                attackDTO.setPlayerId(attack.getOwner().getUuid());
                attackDTO.setPlayerName(attack.getOwner().getUser().getLogin());
                attackDTO.setVillageId(attack.getVillage().getUuid());
                attackDTO.setVillageName(attack.getVillage().getName()+"["+attack.getVillage().getxCoord()+" , " + attack.getVillage().getyCoord()+"]");
                activeAttacks.add(attackDTO);
            }
        }
        return activeAttacks;
    }
}
