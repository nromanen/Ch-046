package ua.cv.tim.dao.hibernate;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.ArmyDao;
import ua.cv.tim.model.Army;

/**
 * Created by Serhii Starovoit on 1/5/2017 in 5:33 PM.
 */
@Repository(value = "armyDao")
public class ArmyDaoImpl extends AbstractCrudDao<Army> implements ArmyDao {

    @Override
    public Army getArmyById(String uuid) {
        String request = "select a from Army a where a.uuid = :uuid";
        Query<Army> query = getCurrentSession().createQuery(request);
        query.setParameter("uuid", uuid);
        return query.getSingleResult();
    }

    public Army getById(String id) {
        return getCurrentSession().get(Army.class, id);
    }


}
