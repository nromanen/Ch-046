package ua.cv.tim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**Represents Village class*/
@Entity
@Table(name = "Village", uniqueConstraints = @UniqueConstraint(columnNames = { "xCoord", "yCoord" }))
public class Village extends UuidEntity implements Comparable<Village>, Serializable {
	/**Empty constructor needed to instantiate beans properly*/
    public Village(){
	}

	//*Village name value*/
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

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "armyRequestVillage")
	private List<Army> armyRequests;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "player_uuid", nullable = false)
	private Player player;

    /**Returns the name of village
     *
     * @return Returns the name of village
     */
	public String getName() {
		return name;
	}

    /** Sets village's name
     *
     * @param name This is the name of village
     */
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Village village = (Village) o;

		if (name != null ? !name.equals(village.name) : village.name != null) return false;
		if (xCoord != null ? !xCoord.equals(village.xCoord) : village.xCoord != null) return false;
		if (yCoord != null ? !yCoord.equals(village.yCoord) : village.yCoord != null) return false;
		if (population != null ? !population.equals(village.population) : village.population != null) return false;
		if (isCapital != null ? !isCapital.equals(village.isCapital) : village.isCapital != null) return false;
		if (wall != null ? !wall.equals(village.wall) : village.wall != null) return false;
		if (armies != null ? !armies.equals(village.armies) : village.armies != null) return false;
		if (armyRequests != null ? !armyRequests.equals(village.armyRequests) : village.armyRequests != null)
			return false;
		return player != null ? player.equals(village.player) : village.player == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (xCoord != null ? xCoord.hashCode() : 0);
		result = 31 * result + (yCoord != null ? yCoord.hashCode() : 0);
		result = 31 * result + (population != null ? population.hashCode() : 0);
		result = 31 * result + (isCapital != null ? isCapital.hashCode() : 0);
		result = 31 * result + (wall != null ? wall.hashCode() : 0);
		result = 31 * result + (armies != null ? armies.hashCode() : 0);
		result = 31 * result + (armyRequests != null ? armyRequests.hashCode() : 0);
		result = 31 * result + (player != null ? player.hashCode() : 0);
		return result;
	}
}
