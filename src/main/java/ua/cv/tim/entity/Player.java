package main.java.ua.cv.travian.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Formula;

@Entity
public class Player extends UuidEntity {

	@Enumerated(EnumType.STRING)
	private Race race;
	@Column(nullable = false, unique = true)
	private String login;
	@Column(nullable = false)
	private String password;

	@Formula(value = "(select count(v.uuid) from village v where v.player_uuid=uuid)")
	private Integer villagesCount;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
	private List<Village> villages = new ArrayList<Village>();

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
