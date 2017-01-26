package ua.cv.tim.service;

import java.util.List;

/**
 * Created by mmaksymtc on 05.01.2017.
 */

import ua.cv.tim.dto.UserInfoDTO;
import ua.cv.tim.model.User;

import javax.mail.MessagingException;

/**
 * Created by vyach on 03.01.2017.
 */
public interface UserService {

	User getUserByUsername(String username);



	void add(User user);

	void update(User user);
	void delete(User user);
	boolean isUnique(User user);
	long getCount();
	User getWithRolesById(String id);
	List<User> getAllWithRoles();
	List<User> getAll();
	User getById(String id);
    void addAllianceMember(UserInfoDTO member) throws MessagingException;
    List<UserInfoDTO> getUsersByAlliance(String allianceName);
    void deleteById(String id);

}
