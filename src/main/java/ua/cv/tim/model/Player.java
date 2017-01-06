package ua.cv.tim.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Player implements Serializable {

	@Id
	@JoinColumn(name = "user_uuid")
	@OneToOne(targetEntity = User.class)
	private User user;

	public void setUser(User userUuid) {
		this.user = userUuid;
	}

	@ManyToOne
	@JoinColumn(name = "alliance_id")
	private Alliance alliance;
	
	@Enumerated(EnumType.STRING)
	private Race race;

	
	//Formula(value = "(select count(v.uuid) from village v where v.player_uuid=uuid)")
	@Transient
	private Integer villagesCount;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Village> villages;

	public User getUser() {
		return user;
	}

	/*public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}*/

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
}
