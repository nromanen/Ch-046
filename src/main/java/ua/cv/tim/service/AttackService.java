package ua.cv.tim.service;

import ua.cv.tim.model.Attack;

import java.util.List;

/**
 * Created by rmochetc on 22.01.2017.
 */
public interface AttackService {

    public List<Attack> getAll();

    public void addAttack(Attack attack);


    public void deleteAAttack(String  id);

    public Attack getById(String  uuid);

    public void updateAAttack(Attack attack);
}
