package ua.cv.tim.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Army extends UuidEntity implements Comparable<Army>, Serializable {

    @Enumerated(EnumType.STRING)
    private UnitType type;

    private Integer count;

    private boolean ownUnit;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "village_uuid")
    private Village owningVillage;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ar_village_uuid")
    private Village armyRequestVillage;

    public Army() {
    }

    public Army(Village village, boolean isArmyAequest) {
        super();
        if (isArmyAequest) {
            armyRequestVillage = village;
        } else {
            owningVillage = village;
        }

    }

    @Transient
    public boolean isArmy() {
        return owningVillage != null;
    }

    @Transient
    public boolean isArmyRequest() {
        return armyRequestVillage != null;
    }

    public UnitType getType() {
        return type;
    }


    public void setType(UnitType type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public boolean isOwnUnit() {
        return ownUnit;
    }

    public void setOwnUnit(boolean ownUnit) {
        this.ownUnit = ownUnit;
    }

    public Village getOwningVillage() {
        return owningVillage;
    }

    public void setOwningVillage(Village owningVillage) {
        this.owningVillage = owningVillage;
    }

    public Village getArmyRequestVillage() {
        return armyRequestVillage;
    }

    public void setArmyRequestVillage(Village armyRequestVillage) {
        this.armyRequestVillage = armyRequestVillage;
    }

    @Override
    public int compareTo(Army other) {
        return this.type.compareTo(other.type);
    }

    @Override
    public String toString() {
        return "Army(" + type + "/" + count + ", ownUnit=" + ownUnit + "uuid=" + getUuid();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army)) return false;
        if (!super.equals(o)) return false;

        Army army = (Army) o;

        if (ownUnit != army.ownUnit) return false;
        if (type != army.type) return false;
        if (count != null ? !count.equals(army.count) : army.count != null) return false;
        return owningVillage != null ? owningVillage.equals(army.owningVillage) : army.owningVillage == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (ownUnit ? 1 : 0);
        result = 31 * result + (owningVillage != null ? owningVillage.hashCode() : 0);
        return result;
    }

}
