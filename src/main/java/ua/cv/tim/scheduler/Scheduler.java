package ua.cv.tim.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
        saveAttacks(attackService.getNotActive());
        attackService.deleteOldAttack();
        userService.deleteOldToken();
    }

    private void saveAttacks(List<Attack> attacks) throws JsonProcessingException {

        if (attacks.size() != 0) {

            List<AttackJson> result = new ArrayList<>();

            for (Attack attack : attacks) {
                AttackJson attackDto = new AttackJson();
                attackDto.setAllianceUuid(attack.getOwner().getAlliance().getUuid());
                attackDto.setAttackTime(attack.getAttackTime().toString());
                attackDto.setEnemy(attack.getEnemy());
                attackDto.setPlayerUuid(attack.getOwner().getUuid());
                attackDto.setVillageUuid(attack.getVillage().getUuid());
                result.add(attackDto);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String resultString = objectMapper.writeValueAsString(result);
            attackArchiveService.add(new AttackArchive(resultString));
            logger.info("Old attacks for save to Data Base archive in JSON: {}", resultString);
        }
    }

    private static class AttackJson{
        private String allianceUuid;
        private String attackTime;
        private String enemy;
        private String playerUuid;
        private String villageUuid;

        public String getAllianceUuid() {
            return allianceUuid;
        }

        public void setAllianceUuid(String allianceUuid) {
            this.allianceUuid = allianceUuid;
        }

        public String getAttackTime() {
            return attackTime;
        }

        public void setAttackTime(String attackTime) {
            this.attackTime = attackTime;
        }

        public String getEnemy() {
            return enemy;
        }

        public void setEnemy(String enemy) {
            this.enemy = enemy;
        }

        public String getPlayerUuid() {
            return playerUuid;
        }

        public void setPlayerUuid(String playerUuid) {
            this.playerUuid = playerUuid;
        }

        public String getVillageUuid() {
            return villageUuid;
        }

        public void setVillageUuid(String villageUuid) {
            this.villageUuid = villageUuid;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("AttackDto{");
            sb.append("allianceUuid='").append(allianceUuid).append('\'');
            sb.append(", attackTime='").append(attackTime).append('\'');
            sb.append(", enemy='").append(enemy).append('\'');
            sb.append(", playerUuid='").append(playerUuid).append('\'');
            sb.append(", villageUuid='").append(villageUuid).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}