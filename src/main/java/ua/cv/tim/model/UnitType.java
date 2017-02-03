package ua.cv.tim.model;

import java.util.Arrays;
import java.util.List;

import static ua.cv.tim.model.Specialty.*;

public enum UnitType {

    Phalanx(Race.GAULS, "Phalanx", 15, 40, 50, 1, 7, Defense),
    Swordsman(Race.GAULS, "Swordsman", 65, 35, 20, 1, 6, Offense),
    Pathfinder(Race.GAULS, "Pathfinder", 0, 20, 10, 2, 17, Scouting),
    TheutatesThunder(Race.GAULS, "TheutatesThunder", 90, 25, 40, 2, 19, Offense),
    Druidrider(Race.GAULS, "Druidrider", 45, 115, 55, 2, 16, Defense),
    Haeduan(Race.GAULS, "Haeduan", 140, 60, 165, 3, 13, Offense, Defense),
    RamG(Race.GAULS, "RamG", 50, 30, 105, 3, 4, Machinery),
    Trebuchet(Race.GAULS, "Trebuchet", 70, 45, 10, 6, 3, Machinery),
    Chieftain(Race.GAULS, "Chieftain", 40, 50, 50, 4, 5, Offense),

    Clubswinger(Race.TEUTONS, "Clubswinger", 40, 20, 5, 1, 7, Offense),
    Spearman(Race.TEUTONS, "Spearman", 10, 35, 60, 1, 7, Defense),
    Axeman(Race.TEUTONS, "Axeman", 60, 30, 30, 1, 6, Offense),
    Scout(Race.TEUTONS, "Scout", 0, 10, 5, 1, 9, Scouting),
    Paladin(Race.TEUTONS, "Paladin", 55, 100, 40, 2, 10, Defense),
    TeutonicKnight(Race.TEUTONS, "TeutonicKnight", 150, 50, 75, 3, 9, Offense),
    RamT(Race.TEUTONS, "RamT", 65, 30, 80, 3, 4,  Machinery),
    Catapult(Race.TEUTONS, "Catapult", 50, 60, 10, 6, 3, Machinery),
    Chief(Race.TEUTONS, "Chief", 40, 60, 40, 4, 4, Offense),


    Legionnaire(Race.ROMANS, "Legionnaire", 40, 35, 50, 1, 6, Defense, Offense),
	Praetorian(Race.ROMANS, "Praetorian", 30, 65, 35, 1, 5, Defense),
	Imperian(Race.ROMANS, "Imperian", 70, 40, 25, 1, 7, Offense),
	EquitesLegati(Race.ROMANS, "EquitesLegati", 0, 20, 10, 2, 16, Scouting),
	EquitesImperatoris(Race.ROMANS, "EquitesImperatoris", 120, 65, 50, 3, 14, Offense),
	EquitesCaesaris(Race.ROMANS, "EquitesCaesaris", 180, 80, 105, 4, 10, Offense),
	BatteringRam(Race.ROMANS, "BatteringRam", 60, 30, 75, 3, 4, Machinery),
	FireCatapult(Race.ROMANS, "FireCatapult", 75, 60, 10, 6, 3, Machinery),
	Senator(Race.ROMANS, "Senator", 50, 40, 30, 5, 4, Offense);




	UnitType(Race race, String name, Integer atack, Integer infantryDef, Integer cavalryDef, Integer upkeep, Integer speed,
			Specialty... specialties) {
		this.race = race;
		this.name = name;
		this.attack = atack;
		this.infantryDef = infantryDef;
		this.cavalryDef = cavalryDef;
		this.upkeep = upkeep;
		this.speed = speed;
		this.specialties = Arrays.asList(specialties);
	}


	private Race race;

	private String name;

	private Integer attack;

	private Integer infantryDef;

	private Integer cavalryDef;

	private Integer upkeep;

	private Integer speed;

	private List<Specialty> specialties;

	public Race getRace() {
		return race;
	}

	public String getName() {
		return name;
	}

	public Integer getAttack() {
		return attack;
	}

	public Integer getInfantryDef() {
		return infantryDef;
	}

	public Integer getCavalryDef() {
		return cavalryDef;
	}

	public Integer getUpkeep() {
		return upkeep;
	}

	public Integer getSpeed() {
		return speed;
	}

	public List<Specialty> getSpecialties() {
		return specialties;
	}

}
