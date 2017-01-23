package ua.cv.tim.dao.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.Player;



@Repository("playerDao")
public class PlayerDaoImpl extends AbstractCrudDao<Player> implements PlayerDao {
    @Autowired
    VillageDao villageDao;

    @Override
    public Player getById(String id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Player WHERE id=:id");
        query.setParameter("id", id);
        Player player = (Player) query.uniqueResult();
        System.out.println("DAO: " + player);
        return player;

//        return getCurrentSession().get(Player.class, id);
    }

    @Override
    public Player getByIdWithVillages(String id) {
        Player player = getById(id);
        Hibernate.initialize(player.getVillages());
        return player;
    }
}
