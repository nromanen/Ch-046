package ua.cv.tim.dao.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.CrudDao;
import ua.cv.tim.model.Alliance;

/**
 * Created by Admin on 30.12.16.
 */

public interface AllianceDao extends CrudDao<Alliance>{


 Alliance getById(long id);

 String getLeaderId(long allianceId);



}
