package ua.cv.tim.dao;

import ua.cv.tim.model.PasswordResetToken;
import ua.cv.tim.model.User;

/**
 * Created by rmochetc on 08.02.2017.
 */
public interface PasswordResetDao extends CrudDao<PasswordResetToken> {

    boolean isToken(User user, String token);
    PasswordResetToken getByUser(User user);
    void deleteByUser(User user);
    void deleteOldToken();

}
