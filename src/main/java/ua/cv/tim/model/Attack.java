package ua.cv.tim.model;

import java.util.Date;

/**
 * Created by rmochetc on 22.01.2017.
 */
public class Attack {
    private Player owner;
    private Village village;
    private String enemy;
    private Date attackTime;



    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public Date getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(Date attackTime) {
        this.attackTime = attackTime;
    }
}
