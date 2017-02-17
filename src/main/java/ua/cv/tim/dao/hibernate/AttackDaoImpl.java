package ua.cv.tim.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.AttackDao;
import ua.cv.tim.model.Attack;

import java.util.Date;
import java.util.List;

/**
 * Created by rmochetc on 06.02.2017.
 */
@Repository("attackDao")
public class AttackDaoImpl extends AbstractCrudDao<Attack> implements AttackDao {

    @Override
    public List<Attack> getAll() {
        Session session = getCurrentSession();
        Query query = session.createQuery("select a FROM Attack a");
        List<Attack> attacks = (List<Attack>) query.list();
        return attacks;
    }

    @Override
    public List<Attack> getActive() {
        Session session = getCurrentSession();
        Query query = session.createQuery("select a FROM Attack a where a.attackTime>:attackTime");
        Date date = new Date();
        query.setParameter("attackTime", date);
        List<Attack> attacks = (List<Attack>) query.list();
        return attacks;
    }

    @Override
    public List<Attack> getNotActive(){
        Session session = getCurrentSession();
        Query query = session.createQuery("select a FROM Attack a where a.attackTime<:attackTime");
        Date date = new Date();
        query.setParameter("attackTime", date);
        List<Attack> attacks = (List<Attack>) query.list();
        return attacks;
    }

    @Override
    public void deleteOldAttack() {

        Session session = getCurrentSession();
        Query query = session.createQuery("delete FROM Attack where attackTime < :thisTime");
        query.setParameter("thisTime", new Date());
        query.executeUpdate();
    }
}
