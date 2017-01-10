package ua.cv.tim.service;

/**
 * Created by Oleg on 05.01.2017.
 */
public interface ServiceInterface<E> {

    void add(E entity);
    void update(E entity);
    void delete(E entity);
    E getById(String id);

}
