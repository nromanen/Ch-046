package ua.cv.tim.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.model.Alliance;

import java.util.List;

/**
 * Created by rmochetc on 03.01.2017.
 */

@Repository("allianceDao")
public class AllianceDaoImpl extends AbstractCrudDao<Alliance>  implements AllianceDao{

    @Override
    public Alliance getById(String id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Alliance WHERE id=:id");
        query.setParameter("id", id);
        Alliance alliance = (Alliance) query.uniqueResult();
        System.out.println(alliance);
        return alliance;
    }


    @Override
    public List<Alliance> getAll() {
        Session session = getCurrentSession();
        Query query = session.createQuery("select a FROM Alliance a");
        List<Alliance> alliances = (List<Alliance>) query.list();
        System.out.println(alliances);
        return alliances;
    }
}
