package ua.cv.tim.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.cv.tim.dao.AbstractCrudDao;
import ua.cv.tim.dao.PasswordResetDao;
import ua.cv.tim.model.PasswordResetToken;
import ua.cv.tim.model.User;

import java.util.Date;

/**
 * Created by rmochetc on 08.02.2017.
 */
@Repository("passwordResetDao")
public class PasswordResetDaoImpl extends AbstractCrudDao<PasswordResetToken> implements PasswordResetDao {
    @Override
    public boolean isToken(User user, String token) {
        Session session = getCurrentSession();
        Query query = null;

        query = session.createQuery("select t from PasswordResetToken t where t.user = :user and t.token = :token");
        query.setParameter("user", user);
        query.setParameter("token", token);

        PasswordResetToken passwordResetToken = (PasswordResetToken) query.uniqueResult();
        System.out.println(passwordResetToken);

        if (passwordResetToken == null){
            return false;
        }
        if (!passwordResetToken.getExpiryDate().after(new Date())){
            return false;
        }
        return true;
    }

    @Override
    public PasswordResetToken getByUser(User user) {
        Session session = getCurrentSession();
        Query query = null;

        query = session.createQuery("select t from PasswordResetToken t where t.user = :user");
        query.setParameter("user", user);

        PasswordResetToken passwordResetToken = (PasswordResetToken) query.uniqueResult();

        return passwordResetToken;
    }

    @Override
    public void deleteByUser(User user) {
        Session session = getCurrentSession();
        Query query = session.createQuery("delete PasswordResetToken where user =:user");
        query.setParameter("user", user);

        int result = query.executeUpdate();

        if (result > 0) {
            System.out.println("Expensive products was removed");
        }
    }

    @Override
    public void deleteOldToken() {
        Session session = getCurrentSession();
        Query query = session.createQuery("delete PasswordResetToken where expiryDate <:date");
        query.setParameter("date", new Date());

        int result = query.executeUpdate();

        if (result > 0) {
            System.out.println("Expensive products was removed");
        }
    }
}
