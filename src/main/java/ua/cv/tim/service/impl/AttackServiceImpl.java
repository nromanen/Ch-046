package ua.cv.tim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dto.AttackDTO;
import ua.cv.tim.model.Attack;
import ua.cv.tim.model.User;
import ua.cv.tim.service.AttackService;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
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

    private static final Logger logger = LoggerFactory.getLogger(AttackServiceImpl.class);

    @Autowired
    VillageService villageService;

    @Autowired
    PlayerService playerService;

    @Autowired
    UserService userService;


    @Override
    public List<Attack> getAll() {

        return attacks;
    }

    @Override
    public void addAttack(AttackDTO attack) {
        Attack newAttack = new Attack();

        logger.info("Add new Attack {}", attack);

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").parse(attack.getAttackTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        newAttack.setUuid("newAtt" + (int) (Math.random() * 100000));
        newAttack.setLastModified(new Date());
        newAttack.setAttackTime(date);
        newAttack.setEnemy(attack.getEnemy());
        newAttack.setOwner(playerService.getById(attack.getPlayerId()));
        newAttack.setVillage(villageService.getById(attack.getVillageId()));

        attacks.add(newAttack);

        System.out.println("Add: " + attacks);

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
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(principal.getUsername());

        for (Attack attack : attacks) {
            if (attack.getAttackTime().after(new Date()) && attack.getOwner().getAlliance().getName().equals(user.getPlayer().getAlliance().getName())) {
                AttackDTO attackDTO = new AttackDTO();
                attackDTO.setUuid(attack.getUuid());
                attackDTO.setEnemy(attack.getEnemy());
                attackDTO.setAttackTime("" + attack.getAttackTime().getTime());
                attackDTO.setPlayerId(attack.getOwner().getUuid());
                attackDTO.setPlayerName(attack.getOwner().getUser().getLogin());
                attackDTO.setVillageId(attack.getVillage().getUuid());
                attackDTO.setVillageName(attack.getVillage().getName() + "[" + attack.getVillage().getxCoord() + " , " + attack.getVillage().getyCoord() + "]");
                activeAttacks.add(attackDTO);
            }
        }

        System.out.println("GET ALL: " + activeAttacks);
        return activeAttacks;
    }
}