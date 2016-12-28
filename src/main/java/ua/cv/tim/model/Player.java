package ua.cv.tim.model;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@Enumerated(EnumType.STRING)
	private Race race;

	@Formula(value = "(select count(v.uuid) from village v where v.player_uuid=uuid)")
	private Integer villagesCount;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
	private List<Village> villages = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getVillagesCount() {
		return villagesCount;
	}

	public void setVillagesCount(Integer villagesCount) {
		this.villagesCount = villagesCount;
	}
}
