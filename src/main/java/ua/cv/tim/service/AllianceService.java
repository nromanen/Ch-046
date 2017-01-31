package ua.cv.tim.service;

import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.model.Alliance;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by Admin on 30.12.16.
 */

public interface AllianceService {

	List<AllianceDTO> getAll();

	void addAlliance(AllianceDTO allianceDTO) throws MessagingException;

	String getIdByName(String name);

	void deleteAlliance(String id);

	Alliance getById(String uuid);

	void updateAlliance(AllianceDTO allianceDTO);

	boolean isUniqueAlliance(String name, String uuid);
}
