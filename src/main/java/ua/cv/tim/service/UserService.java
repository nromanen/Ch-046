package ua.cv.tim.service;

import java.util.List;

/**
 * Created by mmaksymtc on 05.01.2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dao.hibernate.UserDaoImpl;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Race;
import ua.cv.tim.model.User;

import ua.cv.tim.model.User;

/**
 * Created by vyach on 03.01.2017.
 */
public interface UserService {

	User getUserByUsername(String username);

	void add(UserDTO userDTO);

	List<User> getAll();

	void update(User user);

	void delete(User user);

	boolean isUnique(User user);
}
