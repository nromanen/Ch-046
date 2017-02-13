package ua.cv.tim.dao.hibernate;

import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.AttackArchiveDao;
import ua.cv.tim.model.AttackArchive;

/**
 * Created by rmochetc on 13.02.2017.
 */

@Repository("AttackArchiveDao")
public class AttackArchiveDaoImpl extends AbstractCrudDao<AttackArchive> implements AttackArchiveDao {

}
