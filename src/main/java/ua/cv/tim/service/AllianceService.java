package ua.cv.tim.service;

import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.model.Alliance;

import java.util.List;

/**
 * Created by Admin on 30.12.16.
 */


public interface AllianceService {

    public List<AllianceDTO> getAll();

    public void addAlliance(AllianceDTO allianceDTO);

    String getIdByName(String name);

    public void deleteAlliance(String  id);

    public Alliance getById(String  uuid);

    public void updateAlliance(AllianceDTO allianceDTO);

    public boolean isUniqueAlliance(String name, String uuid);
}
