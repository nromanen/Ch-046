package ua.cv.tim.service;

import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.User;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by mmaksymtc on 05.01.2017.
 */

public interface UserService {

	User getUserByUsername(String username);
	void add(User user);
    void update(User user) throws MessagingException;
	void delete(User user);
	boolean isUnique(User user);
	User getWithRolesById(String id);
	List<User> getAllWithRoles();
	List<User> getAll();
	User getById(String id);
	void addUser(UserDTO member) throws MessagingException;
	List<UserDTO> getUsersByAlliance(String allianceName);
	void deleteById(String id);
	User getUserWithAlliance(String username);
	User getFullUserByUsername(String username);

	User getByMail(String email);
	void sendEmail(User user, String message) throws MessagingException;
	void createPasswordResetTokenForUser(User user, String token);
	boolean isToken(User user, String token);
	void updateUserPassword(User user) throws MessagingException;
	void deleteOldToken();
}
