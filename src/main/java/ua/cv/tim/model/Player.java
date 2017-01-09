package ua.cv.tim.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.*;
import javax.persistence.OrderBy;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
public class Player implements Serializable {
	@Id
	@JoinColumn(name = "user_uuid")
	@OneToOne(targetEntity = User.class)
	@JsonIgnoreProperties("player")
	private User user;

	@Enumerated(EnumType.STRING)
	private Race race;

	@Formula(value = "(select count(v.uuid) from village v where v.player_uuid=uuid)")
	private Integer villagesCount;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@OrderBy("population")
	private List<Village> villages;

	@ManyToOne
	@JoinColumn(name = "alliance_id")
	private Alliance alliance;

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public List<Village> getVillages() {
		return villages;
	}

	public void setVillages(List<Village> villages) {
		this.villages = villages;
	}

	public Integer getVillagesCount() {
		return villagesCount;
	}

	public void setVillagesCount(Integer villagesCount) {
		this.villagesCount = villagesCount;
	}

	public Alliance getAlliance() {
		return alliance;
	}

	public void setAlliance(Alliance alliance) {
		this.alliance = alliance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
