package ua.cv.tim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AttackArchiveDao;
import ua.cv.tim.model.AttackArchive;
import ua.cv.tim.service.AttackArchiveService;

/**
 * Created by rmochetc on 13.02.2017.
 */

@Service (value = "attackArchiveService")
@Transactional
public class AttackArchiveServiceImpl implements AttackArchiveService {

    @Autowired
    AttackArchiveDao attackArchiveDao;

    @Override
    public void add(AttackArchive attackArchive) {
        attackArchiveDao.add(attackArchive);
        System.out.println("AttackArchiveService work" + attackArchive);
    }
}
