package ua.cv.tim.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.cv.tim.dto.AttackJson;
import ua.cv.tim.model.Attack;
import ua.cv.tim.model.AttackArchive;
import ua.cv.tim.service.AttackArchiveService;
import ua.cv.tim.service.AttackService;
import ua.cv.tim.service.UserService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 08.02.2017.
 */
@Component
public class Scheduler {

    @Autowired
    private AttackService attackService;

    @Autowired
    private UserService userService;

    @Autowired
    AttackArchiveService attackArchiveService;

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(cron = "0 * * * * *")
    public void clearTempFolder() throws ParseException, JsonProcessingException {
        List<Attack> attacks = attackService.getNotActive();
        if (!attacks.isEmpty()) {
            saveAttacks(attacks);
            attackService.deleteOldAttack();
        }
        userService.deleteOldToken();
    }

    private void saveAttacks(List<Attack> attacks) throws JsonProcessingException {

        List<AttackJson> result = new ArrayList<>();
        for (Attack attack : attacks) {
            AttackJson attackDto = new AttackJson();
            attackDto.setAllianceName(attack.getOwner().getAlliance().getName());
            attackDto.setAttackTime(attack.getAttackTime().toString());
            attackDto.setEnemy(attack.getEnemy());
            attackDto.setPlayerName(attack.getOwner().getUser().getLogin());
            attackDto.setVillageName(attack.getVillage().getName()+" [" + attack.getVillage().getxCoord() + "," + attack.getVillage().getyCoord() +" ]");
            result.add(attackDto);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String resultString = objectMapper.writeValueAsString(result);
        attackArchiveService.add(new AttackArchive(resultString));
        logger.info("Old attacks for save to Data Base archive in JSON: {}", resultString);
    }
}