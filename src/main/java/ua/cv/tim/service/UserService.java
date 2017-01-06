package ua.cv.tim.service;

import ua.cv.tim.model.User;

/**
 * Created by Oleg on 05.01.2017.
 */
public interface UserService extends ServiceInterface<User> {
    long getCount();
}
