package ua.cv.tim.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.AttackArchiveDao;
import ua.cv.tim.model.AttackArchive;

import java.util.List;

/**
 * Created by rmochetc on 13.02.2017.
 */

@Repository("AttackArchiveDao")
public class AttackArchiveDaoImpl extends AbstractCrudDao<AttackArchive> implements AttackArchiveDao {

    @Override
    public List<AttackArchive> getAll() {
        Query query = getCurrentSession().createQuery("select aa from AttackArchive aa");
        return (List<AttackArchive>) query.list();
    }

    @Override
    public AttackArchive getById(String id) {
        Query query = getCurrentSession().createQuery("select aa from AttackArchive aa where aa.uuid=:uuid");
        query.setParameter("uuid", id);
        return (AttackArchive) query.uniqueResult();
    }
}
