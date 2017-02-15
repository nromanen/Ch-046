package ua.cv.tim.service;

import ua.cv.tim.dto.AttackDTO;
import ua.cv.tim.model.Attack;

import java.util.List;

/**
 * Created by rmochetc on 22.01.2017.
 */
public interface AttackService {

    List<Attack> getNotActive();

    void addAttack(AttackDTO attack);

    void deleteAttack(Attack attack);

    List<AttackDTO> getActive();

    void deleteOldAttack();
}
