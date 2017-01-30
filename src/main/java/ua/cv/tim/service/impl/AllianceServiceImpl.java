package ua.cv.tim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.AllianceService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.utils.SendMail;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 17.01.2017.
 */
@Service(value = "allianceService")
@Transactional
public class AllianceServiceImpl implements AllianceService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AllianceDao allianceDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private UserService userService;

    @Autowired
    private SendMail sendMail;


    public List<AllianceDTO> getAll(){
        List<Alliance> alliances = allianceDao.getAll();
        List<AllianceDTO> allianceDTOS = new ArrayList<>();

        for (Alliance alliance: alliances){
            User leader = null;
            for(Player player : alliance.getPlayers()){
                if (player.getUser().getRoles().contains(Role.LEADER)){
                    leader = player.getUser();
                }
            }
            allianceDTOS.add(new AllianceDTO(alliance.getUuid(), leader.getUuid(), alliance.getName(), leader.getLogin(), leader.getEmail()));
        }
        logger.info("Alliances: {}", allianceDTOS);
        return allianceDTOS;
    }

    public void addAlliance(AllianceDTO allianceDTO){

        logger.info("Adding new alliance: {}", allianceDTO);
        Alliance alliance = new Alliance();
        alliance.setName(allianceDTO.getName());

        User user = new User();
        user.setLogin(allianceDTO.getLeaderLogin());
        user.setEmail(allianceDTO.getLeaderEmail());
        user.setPassword("111");
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.LEADER);
        user.setRoles(roles);

        userDao.add(user);

        Player player = new Player();
        player.setUser(user);
        playerDao.add(player);
        user.setPlayer(player);
        List<Player> players = new ArrayList<>();
        players.add(player);
        alliance.setPlayers(players);
        allianceDao.add(alliance);
        player.setAlliance(alliance);

        try {
            sendMail.send(user.getEmail(), "Travian user's info",
                    "Your login is" + user.getLogin() + " and password: " + user.getPassword() + "  role " + user.getRoles());
            logger.info("Password {} has been sent on user's e-mail {}", user.getPassword(), user.getEmail());
        } catch (MessagingException e) {
            logger.error("The e-mail hasn't been sent {}", e);
        }

        logger.info("New alliance added successfully: {}", allianceDTO);
    }

    public String getIdByName(String name){
        return allianceDao.getIdByName(name);
    }

    public void updateAlliance(AllianceDTO allianceDTO){

        logger.info("Updating alliance: {}", allianceDTO);
        Alliance alliance = allianceDao.getById(allianceDTO.getAllianceUuid());
        alliance.setName(allianceDTO.getName());

        User leader = null;
        for(Player player : alliance.getPlayers()){
            if (player.getUser().getRoles().contains(Role.LEADER)){
                leader = player.getUser();
            }
        }
        leader.setLogin(allianceDTO.getLeaderLogin());
        leader.setEmail(allianceDTO.getLeaderEmail());

        try {
            sendMail.send(leader.getEmail(), "Travian user's info",
                    "Your login is" + leader.getLogin() + " and password: " + leader.getPassword() + "  role " + leader.getRoles());
            logger.info("Password {} has been sent on user's e-mail {}", leader.getPassword(), leader.getEmail());
        } catch (MessagingException e) {
            logger.error("The e-mail hasn't been sent {}", e);
        }
        allianceDao.update(alliance);

        logger.info("Alliance updated successfully: {}", allianceDTO);
    }

    public void deleteAlliance(String  id){

        logger.info("Deleting alliance id = {}", id);
        Alliance alliance = allianceDao.getById(id);
        if (alliance != null){
            for (Player player: alliance.getPlayers()){
                userDao.delete(player.getUser());
            }
            allianceDao.delete(alliance);
            logger.info("Alliance deleted successfully: {}", alliance);
        }
    }

    public Alliance getById(String  uuid){
        return allianceDao.getById(uuid);
    }

    public boolean isUniqueAlliance(String name, String uuid){

        if(allianceDao.getByName(name, uuid)!=null ) {
            return false;
        }
        return true;

    }
}
