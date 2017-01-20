package ua.cv.tim.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.dto.UserInfoDTO;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.utils.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * Created by Oleg on 04.01.2017.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private AllianceDao allianceDao;

    @Autowired
    private SendMail sendMail;

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username, null);
    }

    @Override
    public void add(UserDTO userDTO) throws MessagingException {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        if (userDTO.getRole() != null) {
            roles.add(Role.LEADER);
        }
        user.setRoles(roles);
        userDao.add(user);
        Player player = new Player();
        player.setUser(user);
        user.setPlayer(player);
        playerDao.add(player);
        sendEmail(user);

    }

    @Override
    public boolean isUnique(User user) {


        if (userDao.getUserByUsername(user.getLogin(), user.getUuid()) !=null) {
            return false;
        }
        if(userDao.getByMail(user.getEmail(), user.getUuid())!=null ) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public long getCount() {
        return userDao.getCount();
    }

    @Override
    public User getWithRolesById(String id) {
        return userDao.getWithRolesById(id);
    }

    @Override
    public List<User> getAllWithRoles() {
        return userDao.getAllWithRoles();
    }

    @Override
    public User getById(String id) {
        return userDao.getById(id);
    }

    public void deleteById(String id) {
        User byId = userDao.getById(id);
        userDao.delete(byId);
    }

    public List<UserInfoDTO> getUsersByAlliance(String allianceName) {
        List<User> users = userDao.getUsersByAlliance(allianceName);
        List<UserInfoDTO> allianceUsers = new ArrayList<>();
        for (User user : users) {
            allianceUsers.add(new UserInfoDTO(user.getUuid(), user.getLogin(), user.getEmail(),
                    user.getPlayer().getAlliance().getName()));
        }
        return allianceUsers;
    }

    public void addAllianceMember(UserInfoDTO member) throws MessagingException {
        User user = new User();
        user.setLogin(member.getLogin());
        user.setEmail(member.getEmail());
        user.setPassword(generatePassword(10));
        //user.setPassword("hello"); // todo auto generate password
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        if (member.getRole() != null) {
            roles.add(Role.LEADER);
        }
        user.setRoles(roles);
        userDao.add(user);

        Player player = new Player();
        player.setUser(user);
        player.setAlliance(allianceDao.getAllianceByName(member.getAlliance()));
        player.getAlliance().setName(member.getAlliance());
        user.setPlayer(player);
        playerDao.add(player);
        sendEmail(user);
    }


    @Override
    public void add(User user) {
        // TODO Auto-generated method stub

    }

    public void sendEmail(User user) throws MessagingException {
        try {
            sendMail.send(user.getEmail(), "Travian user's info", "Your login is" + user.getLogin() + " and password: "
                    + user.getPassword() + "  role " + user.getRoles());
            logger.info("Password {} has been sent on user's e-mail {}", user.getPassword(), user.getEmail());
        } catch (MessagingException e) {
            logger.error("The e-mail {} hasn't been sent {}", user.getEmail(), e);
        }
    }

    private String generatePassword(int length) {
        String symbols = "][{}()~!@#$%^&*.,`";
        String numbers = "0123456789";
        String upperalphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] password = new char[length];
        Random random = new Random();
        for (int i = 0; i < length - 2; i++) {
            if (i % 2 == 0) {
                password[i] = upperalphabet.charAt(random.nextInt(upperalphabet.length()));
            } else {
                password[i] = lowerAlphabet.charAt(random.nextInt(lowerAlphabet.length()));
            }
        }
        password[length - 2] = numbers.charAt(random.nextInt(numbers.length()));
        password[length - 1] = symbols.charAt(random.nextInt(symbols.length()));
        for (int i = 0; i < password.length; i++) {
            int randomIndex = (int) (Math.random() * password.length);
            char temp = password[i];
            password[i] = password[randomIndex];
            password[randomIndex] = temp;
        }
        logger.info("Auto-generated password is {}", new String(password));
        return new String(password);


    }
}
