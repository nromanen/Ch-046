package ua.cv.tim.dao.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.Village;

import java.util.Collections;


/**
 * Created by Oleg on 05.01.2017.
 */
@Repository
public class VillageDaoImpl extends AbstractCrudDao<Village> implements VillageDao {
    @Override
    public Village getById(String id) {
        Village village = getCurrentSession().get(Village.class, id);
        Hibernate.initialize(village.getArmies());
        Collections.sort(village.getArmies());
        return village;
    }

    @Override
    public Village getByCoordinates(short xCoord, short yCoord) {
        Query query=getCurrentSession().createQuery("FROM Village Where xCoord=:x AND yCoord=:y");
        query.setParameter("x",xCoord);
        query.setParameter("y",yCoord);
        Village village = (Village) query.uniqueResult();
        System.out.println();
        return village;
    }

    @Override
    public Village getByName(String name) {
        Query<Village> query=getCurrentSession().createQuery("From Village WHERE name=:name");
        query.setParameter("name",name);
        Village village = query.uniqueResult();
        return village;
    }
}
