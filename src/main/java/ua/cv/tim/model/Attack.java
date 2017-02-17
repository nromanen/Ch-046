package ua.cv.tim.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by rmochetc on 22.01.2017.
 */
@Entity
public class Attack extends UuidEntity implements Serializable {

    @OneToOne
    private Player owner;

    @OneToOne
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Attack{");
        sb.append("owner=").append(owner.getUser().getLogin());
        sb.append(", village=").append(village.getName());
        sb.append(", enemy='").append(enemy).append('\'');
        sb.append(", attackTime=").append(attackTime);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Attack attack = (Attack) o;

        if (owner != null ? !owner.equals(attack.owner) : attack.owner != null) return false;
        if (village != null ? !village.equals(attack.village) : attack.village != null) return false;
        if (enemy != null ? !enemy.equals(attack.enemy) : attack.enemy != null) return false;
        return attackTime != null ? attackTime.equals(attack.attackTime) : attack.attackTime == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (village != null ? village.hashCode() : 0);
        result = 31 * result + (enemy != null ? enemy.hashCode() : 0);
        result = 31 * result + (attackTime != null ? attackTime.hashCode() : 0);
        return result;
    }
}
