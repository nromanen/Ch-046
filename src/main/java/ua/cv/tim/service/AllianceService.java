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

	void deleteAlliance(String id);

	Alliance getById(String uuid);

	Alliance getByName(String name);

	void updateAlliance(AllianceDTO allianceDTO) throws MessagingException;

	boolean isUniqueAlliance(String name, String uuid);
}
