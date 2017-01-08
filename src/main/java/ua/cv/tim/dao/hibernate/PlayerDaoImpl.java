package ua.cv.tim.dao.hibernate;

import org.springframework.stereotype.Repository;

import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;

@Repository("playerDao")
public class PlayerDaoImpl extends AbstractCrudDao<Player> implements PlayerDao {

}
