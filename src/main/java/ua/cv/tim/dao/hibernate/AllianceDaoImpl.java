package ua.cv.tim.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.service.AllianceService;

import javax.persistence.OrderBy;
import java.util.List;

/**
 * Created by rmochetc on 03.01.2017.
 */

@Repository("allianceDao")
public class AllianceDaoImpl extends AbstractCrudDao<Alliance>  implements AllianceDao {

    @Override
    public Alliance getById(String id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Alliance WHERE id=:id");
        query.setParameter("id", id);
        Alliance alliance = (Alliance) query.uniqueResult();
        return alliance;
    }


    @Override
    public List<Alliance> getAll() {
        Session session = getCurrentSession();
        Query query = session.createQuery("select a FROM Alliance a order by a.name");
        List<Alliance> alliances = (List<Alliance>) query.list();
        return alliances;
    }


    @Override
    public Alliance getByName(String name, String uuid) {

        Session session = getCurrentSession();
        Query query = null;
        if (uuid != null) {
            query = session.createQuery("select a from Alliance a where a.name = :name and a.uuid != :uuid");
            query.setParameter("name", name);
            query.setParameter("uuid", uuid);
        } else {
            query = session.createQuery("select a from Alliance a where a.name = :name");
            query.setParameter("name", name);
        }
        Alliance alliance = (Alliance) query.uniqueResult();
        System.out.println(alliance);
        return alliance;
    }

    @Override
    public Alliance getAllianceByName(String allianceName) {
        Query<Alliance> query = getCurrentSession().createQuery("select a from Alliance a where a.name = :name");
        query.setParameter("name", allianceName);
        return query.getSingleResult();
    }
}
