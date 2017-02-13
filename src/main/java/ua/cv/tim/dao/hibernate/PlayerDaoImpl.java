package ua.cv.tim.dao.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;

import java.util.List;


@Repository("playerDao")
public class PlayerDaoImpl extends AbstractCrudDao<Player> implements PlayerDao {

	private static final Logger log = LoggerFactory.getLogger(PlayerDaoImpl.class);

	@Autowired
	VillageDao villageDao;

	@Override
	public Player getById(String id) {
		Query query = getCurrentSession().createQuery("FROM Player WHERE id=:id");
		query.setParameter("id", id);
		Player player = (Player) query.uniqueResult();
		return player;
	}

	@Override
	public Player getByIdWithVillages(String id) {
		Player player = getById(id);
		Hibernate.initialize(player.getVillages());
		List<Village> villages = player.getVillages();
		Hibernate.initialize(player.getUser().getRoles());
		for(Village village:villages)
			Hibernate.initialize(village.getArmies());
		return player;
	}

	@Override
	public List<Player> getPlayersByAllianceWithVillages(String allianceName) {
		String request = "select p from Player p where p.alliance.name = :name";
		Query<Player> query = getCurrentSession().createQuery(request);
		query.setParameter("name", allianceName);
		List<Player> players = query.list();
		for (Player player : players) {
			initializePlayerVillages(player);
		}
		return players;
	}

	private void initializePlayerVillages(Player player) {
		for (Village village : player.getVillages()) {
			Hibernate.initialize(village.getArmies());
			Hibernate.initialize(village.getArmyRequests());
		}
		Hibernate.initialize(player.getVillages());
	}
}
