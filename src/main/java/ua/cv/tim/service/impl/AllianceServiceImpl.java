package ua.cv.tim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.dto.UserDTO;
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

    public void addAlliance(AllianceDTO allianceDTO) throws MessagingException {

        logger.info("Adding new alliance: {}", allianceDTO);
        Alliance alliance = new Alliance();
        alliance.setName(allianceDTO.getName());
        allianceDao.add(alliance);
        UserDTO user = new UserDTO(null,allianceDTO.getLeaderLogin(), allianceDTO.getLeaderEmail(), allianceDTO.getName(), true);
        userService.addUser(user);
        allianceDTO.setLeaderUuid(userService.getUserByUsername(allianceDTO.getLeaderLogin()).getUuid());
        logger.info("New alliance added successfully: {}", allianceDTO);
    }

    public void updateAlliance(AllianceDTO allianceDTO) throws MessagingException {

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
        allianceDao.update(alliance);
        userService.sendEmail(leader,"Alliance " + allianceDTO.getName() + " updated successfully. Your are leader of alliance, your login is "
                + leader.getLogin() + ".");
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

    @Override
    public Alliance getByName(String name) {
        return allianceDao.getByName(name, null);
    }

    public boolean isUniqueAlliance(String name, String uuid){

        if(allianceDao.getByName(name, uuid)!=null ) {
            throw new IllegalArgumentException("Alliance with entered name already exists.");
        }
        return true;

    }
}
