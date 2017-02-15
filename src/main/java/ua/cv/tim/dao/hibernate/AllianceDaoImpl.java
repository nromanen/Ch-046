package ua.cv.tim.dao.hibernate;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.model.Alliance;

import java.util.List;

/**
 * Created by rmochetc on 03.01.2017.
 */

@Repository("allianceDao")
public class AllianceDaoImpl extends AbstractCrudDao<Alliance>  implements AllianceDao {

    @Override
    public Alliance getById(String id) {
        Query query = getCurrentSession().createQuery("FROM Alliance WHERE id=:id");
        query.setParameter("id", id);
        return (Alliance) query.uniqueResult();
    }


    @Override
    public List<Alliance> getAll() {
        Query query = getCurrentSession().createQuery("select a FROM Alliance a order by a.name");
        return (List<Alliance>) query.list();
    }


    @Override
    public Alliance getByName(String name, String uuid) {
        Query query = null;
        if (uuid != null) {
            query = getCurrentSession().createQuery("select a from Alliance a where a.name = :name and a.uuid != :uuid");
            query.setParameter("name", name);
            query.setParameter("uuid", uuid);
        } else {
            query = getCurrentSession().createQuery("select a from Alliance a where a.name = :name");
            query.setParameter("name", name);
        }
        Alliance alliance = (Alliance) query.uniqueResult();
        return alliance;
    }

    @Override
    public Alliance getAllianceByName(String allianceName) {
        Query<Alliance> query = getCurrentSession().createQuery("select a from Alliance a where a.name = :name");
        query.setParameter("name", allianceName);
        return query.getSingleResult();
    }
}
