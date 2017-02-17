package ua.cv.tim.dao.hibernate;

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
        Query<AttackArchive> query = getCurrentSession().createQuery("select aa from AttackArchive aa");
        return query.list();
    }

    @Override
    public AttackArchive getById(String id) {
        Query<AttackArchive> query = getCurrentSession().createQuery("select aa from AttackArchive aa where aa.uuid=:uuid");
        query.setParameter("uuid", id);
        return query.uniqueResult();
    }
}
