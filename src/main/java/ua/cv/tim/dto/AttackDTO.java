package ua.cv.tim.dto;

import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;

import java.util.Date;

/**
 * Created by rmochetc on 25.01.2017.
 */
public class AttackDTO {

    private String uuid;
    private String  playerId;
    private String  playerName;
    private String villageId;
    private String villageName;
    private String enemy;
    private String attackTime;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public String getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(String attackTime) {
        this.attackTime = attackTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AttackDTO{");
        sb.append("uuid='").append(uuid).append('\'');
        sb.append(", playerId='").append(playerId).append('\'');
        sb.append(", villageId='").append(villageId).append('\'');
        sb.append(", villageName='").append(villageName).append('\'');
        sb.append(", enemy='").append(enemy).append('\'');
        sb.append(", attackTime='").append(attackTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
