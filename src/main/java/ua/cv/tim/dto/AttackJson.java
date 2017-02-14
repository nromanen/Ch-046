package ua.cv.tim.dto;

/**
 * Created by rmochetc on 14.02.2017.
 */
public class AttackJson {
    private String allianceName;
    private String attackTime;
    private String enemy;
    private String playerName;
    private String villageName;

    public String getAllianceName() {
        return allianceName;
    }

    public void setAllianceName(String allianceName) {
        this.allianceName = allianceName;
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

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AttackJson{");
        sb.append("allianceName='").append(allianceName).append('\'');
        sb.append(", attackTime='").append(attackTime).append('\'');
        sb.append(", enemy='").append(enemy).append('\'');
        sb.append(", playerName='").append(playerName).append('\'');
        sb.append(", villageName='").append(villageName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
