package ua.cv.tim.service;

import ua.cv.tim.model.User;

import java.util.List;

/**
 * Created by Oleg on 05.01.2017.
 */
public interface UserService extends ServiceInterface<User> {
    long getCount();
     User getWithRolesById(String id);
    List<User> getAllWithRoles();
    List<User> getAll();
}
