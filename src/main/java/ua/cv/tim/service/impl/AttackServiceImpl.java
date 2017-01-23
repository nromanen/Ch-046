package ua.cv.tim.service.impl;

import ua.cv.tim.model.Attack;
import ua.cv.tim.service.AttackService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 22.01.2017.
 */
public class AttackServiceImpl implements AttackService {

    private List<Attack> attacks = new ArrayList<>();


    @Override
    public List<Attack> getAll() {
        return null;
    }

    @Override
    public void addAttack(Attack attack) {

    }

    @Override
    public void deleteAAttack(String id) {

    }

    @Override
    public Attack getById(String uuid) {
        return null;
    }

    @Override
    public void updateAAttack(Attack attack) {

    }
}
