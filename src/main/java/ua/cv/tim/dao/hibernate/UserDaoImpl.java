package ua.cv.tim.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
//import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.model.User;

/**
 * Created by mmaksymtc on 03.01.2017.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {

    @Override
    public User getById(String uuid) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM User WHERE id=:id");
        query.setParameter("id", uuid);
        User user = (User) query.uniqueResult();
        return user;
                    
    }

	@Override
	public List<User> getAll() {
		Session session = getCurrentSession();
        Query query = session.createQuery("FROM User ");
        List<User> users = (List<User>) query.getResultList();
		return users;
	}
	
	   public User getByMail(String mail) {
	        Session session = getCurrentSession();
	        Query query = session.createQuery("FROM User WHERE email=:mail");
	        query.setParameter("mail", mail);
	        User user = (User) query.getSingleResult();
	        return user;	                    
	    }
}