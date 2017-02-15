package ua.cv.tim.dao;

import ua.cv.tim.model.Attack;

import java.util.List;

/**
 * Created by rmochetc on 06.02.2017.
 */
public interface AttackDao extends CrudDao<Attack>{

    List<Attack> getAll();

    List<Attack> getActive();

    List<Attack> getNotActive();

    void deleteOldAttack();

}
