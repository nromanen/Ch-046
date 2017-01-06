package ua.cv.tim.dao.hibernate;

import java.util.List;

import ua.cv.tim.dao.CrudDao;
import ua.cv.tim.model.User;

/**
 * Created by vyach on 29.12.2016.
 */
public interface UserDao extends CrudDao<User> {

	  User getById(String uuid);
	  
	  List<User> getAll();
	  
	  User getByMail(String mail);
	
}
