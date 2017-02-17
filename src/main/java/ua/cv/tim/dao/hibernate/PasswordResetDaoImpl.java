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
        Query<PasswordResetToken> query = getCurrentSession().createQuery("select t from PasswordResetToken t where t.user = :user and t.token = :token");
        query.setParameter("user", user);
        query.setParameter("token", token);
        PasswordResetToken passwordResetToken = query.uniqueResult();

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

        Query<PasswordResetToken> query =  getCurrentSession().createQuery("select t from PasswordResetToken t where t.user = :user");
        query.setParameter("user", user);
        PasswordResetToken passwordResetToken = query.uniqueResult();
        return passwordResetToken;
    }

    @Override
    public void deleteByUser(User user) {
        Query query = getCurrentSession().createQuery("delete FROM PasswordResetToken where user =:user");
        query.setParameter("user", user);
        query.executeUpdate();
    }

    @Override
    public void deleteOldToken() {
        Query query = getCurrentSession().createQuery("delete FROM PasswordResetToken where expiryDate <:date");
        query.setParameter("date", new Date());
        query.executeUpdate();
    }
}
