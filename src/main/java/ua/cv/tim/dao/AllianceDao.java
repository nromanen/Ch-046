package ua.cv.tim.dao;

import ua.cv.tim.model.Alliance;

import java.util.List;

/**
 * Created by Admin on 30.12.16.
 */

public interface AllianceDao extends CrudDao<Alliance> {
    Alliance getById(String id);
    List<Alliance> getAll();
    Alliance getByName(String name, String uuid);


    Alliance getAllianceByName(String allianceName);
}

