package ua.cv.tim.dao.hibernate;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.model.User;

/**
 * Created by vyach on 03.01.2017.
 */

@Repository(value = "userDao")
public class HUserDao extends AbstractCrudDao<User> implements UserDao<User> {

	@Override
	public User getUserByUsername(String username) {
		String request = "select u from User u where u.login = :login";
		Query<User> query = getCurrentSession().createQuery(request);
		query.setParameter("login", username);
		return query.getSingleResult();
	}
}
