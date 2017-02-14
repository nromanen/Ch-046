package ua.cv.tim.dao;

import ua.cv.tim.model.AttackArchive;

import java.util.List;

/**
 * Created by rmochetc on 13.02.2017.
 */
public interface AttackArchiveDao  extends CrudDao<AttackArchive>  {

    List<AttackArchive> getAll();
    AttackArchive getById(String id);
}
