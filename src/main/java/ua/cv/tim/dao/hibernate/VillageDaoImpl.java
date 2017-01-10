package ua.cv.tim.dao.hibernate;

import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.Village;

/**
 * Created by Oleg on 05.01.2017.
 */
@Repository
public class VillageDaoImpl extends AbstractCrudDao<Village> implements VillageDao {
    @Override
    public Village getById(String id) {
        return  getCurrentSession().get(Village.class,id);
    }
}
