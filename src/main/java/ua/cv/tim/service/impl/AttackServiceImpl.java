package ua.cv.tim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AttackDao;
import ua.cv.tim.dto.AttackDTO;
import ua.cv.tim.model.Attack;
import ua.cv.tim.model.User;
import ua.cv.tim.service.AttackService;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.service.VillageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rmochetc on 22.01.2017.
 */
@Service
@Transactional
public class AttackServiceImpl implements AttackService {


    @Autowired
    private AttackDao attackDao;

    private static final Logger logger = LoggerFactory.getLogger(AttackServiceImpl.class);

    @Autowired
    private VillageService villageService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private UserService userService;

    @Override
    public List<Attack> getNotActive() {
        List<Attack> notActiveAttacks = attackDao.getNotActive();
        logger.info("Not active Attacks {}", notActiveAttacks);
        return notActiveAttacks;
    }

    @Override
    public void addAttack(AttackDTO attack) {
        Attack newAttack = new Attack();
        logger.info("Add new Attack {}", attack);
        Date date = new Date(attack.getAttackTime());
        newAttack.setAttackTime(date);
        newAttack.setEnemy(attack.getEnemy());
        newAttack.setOwner(playerService.getById(attack.getPlayerId()));
        newAttack.setVillage(villageService.getById(attack.getVillageId()));
        attackDao.add(newAttack);
    }

    @Override
    public void deleteAttack(Attack attack) {
        attackDao.delete(attack);
    }

    @Override
    public List<AttackDTO> getActive() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(principal.getUsername());
        List<Attack> attacks = attackDao.getActive();
        List<AttackDTO> activeAttacks = getAttacksDTO(attacks, user.getPlayer().getAlliance().getName() );

        return activeAttacks;
    }

    @Override
    public void deleteOldAttack() {
        attackDao.deleteOldAttack();
    }

    private List<AttackDTO> getAttacksDTO(List<Attack> attacks, String allianceName) {

        List<AttackDTO> result = new ArrayList<>();
        for (Attack attack : attacks) {
            if (attack.getOwner().getAlliance().getName().equals(allianceName)) {
                AttackDTO attackDTO = new AttackDTO();
                attackDTO.setUuid(attack.getUuid());
                attackDTO.setEnemy(attack.getEnemy());
                attackDTO.setAttackTime("" + attack.getAttackTime().getTime());
                attackDTO.setPlayerId(attack.getOwner().getUuid());
                attackDTO.setPlayerName(attack.getOwner().getUser().getLogin());
                attackDTO.setVillageId(attack.getVillage().getUuid());
                attackDTO.setVillageName(attack.getVillage().getName() + "[" + attack.getVillage().getxCoord() + " , " + attack.getVillage().getyCoord() + "]");
                result.add(attackDTO);
            }
        }
        return result;
    }
}