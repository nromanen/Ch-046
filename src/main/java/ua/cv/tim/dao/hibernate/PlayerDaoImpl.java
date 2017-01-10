package ua.cv.tim.dao.hibernate;

import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.model.Player;

/**
 * Created by Admin on 09.01.17.
 */

@Repository("playerDao")
public class PlayerDaoImpl extends AbstractCrudDao<Player> implements PlayerDao {

}
