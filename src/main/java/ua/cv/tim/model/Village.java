package ua.cv.tim.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Village", uniqueConstraints = @UniqueConstraint(columnNames = { "xCoord", "yCoord" }))
public class Village extends UuidEntity implements Comparable<Village> {
    public Village(){
	}
	private String name;
	@Column(nullable = false)
	private Short xCoord;
	@Column(nullable = false)
	private Short yCoord;
	private Short population;
	private Boolean isCapital;
	private Byte wall = 0;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owningVillage",cascade = CascadeType.ALL)
	private List<Army> armies = new ArrayList<>();

//	@JsonBackReference
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "armyRequestVillage")
	private List<Army> armyRequests;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "player_uuid", nullable = false)
	private Player player;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getxCoord() {
		return xCoord;
	}

	public void setxCoord(Short xCoord) {
		this.xCoord = xCoord;
	}

	public Short getyCoord() {
		return yCoord;
	}

	public void setyCoord(Short yCoord) {
		this.yCoord = yCoord;
	}

	public Short getPopulation() {
		return population;
	}

	public void setPopulation(Short population) {
		this.population = population;
	}

	public List<Army> getArmies() {
		return armies;
	}

	public void setArmies(List<Army> armies) {
		this.armies = armies;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Boolean getIsCapital() {
		return isCapital;
	}

	public void setIsCapital(Boolean isCapital) {
		this.isCapital = isCapital;
	}

	public Byte getWall() {
		return wall;
	}

	public void setWall(Byte wall) {
		this.wall = wall;
	}

	@Override
	public String toString() {
		return String.format("Village: %s, (%s/%s)", name, xCoord, yCoord);
	}

	public List<Army> getArmyRequests() {
		return armyRequests;
	}

	public void setArmyRequests(List<Army> armyRequests) {
		this.armyRequests = armyRequests;
	}

	@Override
	public int compareTo(Village other) {
		if(this.name == null) {
			return 0;
		}
		return this.name.compareTo(other.name);
	}

}
