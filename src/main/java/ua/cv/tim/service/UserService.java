package ua.cv.tim.service;

import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.User;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {

	User getUserByUsername(String username);

	void add(User user);

	void update(User user);

	void delete(User user);

	boolean isUnique(User user);

	boolean isUserUnique(UserDTO member);

	long getCount();

	User getWithRolesById(String id);

	List<User> getAllWithRoles();

	List<User> getAll();

	User getById(String id);

	void addUser(UserDTO member) throws MessagingException;

	List<UserDTO> getUsersByAlliance(String allianceName);

	void deleteById(String id);

	User getUserWithAlliance(String username);
}
