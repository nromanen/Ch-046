package ua.cv.tim.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Player implements Serializable {

	@Id
	@JoinColumn(name = "user_uuid")
	@OneToOne(targetEntity = User.class)
	private User user;

	@Enumerated(EnumType.STRING)
	private Race race;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Village> villages;

	@ManyToOne
	@JoinColumn(name = "alliance_id")
	private Alliance alliance;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public Alliance getAlliance() {
		return alliance;
	}

	public void setAlliance(Alliance alliance) {
		this.alliance = alliance;
	}
}
