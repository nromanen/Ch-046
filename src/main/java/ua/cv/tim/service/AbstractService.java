package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AbstractCrudDao;

/**
 * Created by Oleg on 05.01.2017.
 */
@Service
@Transactional
public abstract class AbstractService<E> implements ServiceInterface<E> {
    @Autowired
    AbstractCrudDao<E> abstractCrudDao;
    @Override
    public void add(E entity) {
         abstractCrudDao.add(entity);
    }

    @Override
    public void update(E entity) {
        abstractCrudDao.update(entity);
    }

    @Override
    public void delete(E entity) {
        abstractCrudDao.delete(entity);
    }

    @Override
    public E getById(String id) {
        return abstractCrudDao.getById(id);
    }
}
