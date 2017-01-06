package ua.cv.tim.dao;

import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.hibernate.VillageDao;
import ua.cv.tim.model.Village;

/**
 * Created by Oleg on 05.01.2017.
 */
@Repository
public class VillageDaoImpl extends AbstractCrudDao<Village> implements VillageDao {
    public VillageDaoImpl(Class<Village> clazz) {
        super(clazz);
    }
}
