package ua.cv.tim.dao.hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.model.Army;

/**
 * Created by Serhii Starovoit on 1/5/2017 in 5:33 PM.
 */
@Repository
public class ArmyDaoImpl extends AbstractCrudDao<Army> implements ArmyDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Army entity) {
        this.hibernateTemplate.save(entity);
    }
    @Override
    public void update(Army entity) {
        this.hibernateTemplate.delete(entity);
    }

    @Override
    public void delete(Army entity) {
        this.hibernateTemplate.delete(entity);
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
