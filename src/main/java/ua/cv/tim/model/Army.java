package ua.cv.tim.model;

import javax.persistence.*;

@Entity
public class Army extends UuidEntity implements Comparable<Army> {

	@Enumerated(EnumType.STRING)
	private UnitType type;

	private Integer count;

	private boolean ownUnit;

	@ManyToOne
	@JoinColumn(name = "village_uuid")
	private Village owningVillage;

	@ManyToOne
	@JoinColumn(name = "ar_village_uuid")
	private Village armyRequestVillage;

	public Army() {
	}

	public Army(Village village, boolean isArmyAequest) {
		super();
		if(isArmyAequest) {
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

}
