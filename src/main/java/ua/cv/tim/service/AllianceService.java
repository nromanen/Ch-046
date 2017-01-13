package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.model.*;
import ua.cv.tim.dto.AllianceDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 30.12.16.
 */

@Service("allianceService")
@Transactional
public class AllianceService {

    @Autowired
    private AllianceDao allianceDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlayerDao playerDao;

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
            allianceDTOS.add(new AllianceDTO(alliance.getUuid(), alliance.getName(), leader.getLogin(), leader.getEmail()));
        }
        return allianceDTOS;
    }

    public void addAlliane(AllianceDTO allianceDTO){
        Alliance alliance = new Alliance();
        alliance.setName(allianceDTO.getName());

        User user = new User();
        user.setLogin(allianceDTO.getLeaderLogin());
        user.setEmail(allianceDTO.getLeaderEmail());
        user.setPassword("test");
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
    }

    public String getIdByName(String name){
        return allianceDao.getIdByName(name);
    }

    public void updateAlliance(AllianceDTO allianceDTO){

        Alliance alliance = allianceDao.getById(allianceDTO.getUuid());

        alliance.setName(allianceDTO.getName());

        User leader = null;

        for(Player player : alliance.getPlayers()){
            if (player.getUser().getRoles().contains(Role.LEADER)){
                leader = player.getUser();
            }
        }

        leader.setLogin(allianceDTO.getLeaderLogin());
        leader.setPassword(allianceDTO.getLeaderEmail());

        allianceDao.update(alliance);
    }

    public void deleteAlliance(String  id){

        Alliance alliance = allianceDao.getById(id);

        System.out.println(alliance);


        if (alliance != null){
            for (Player player: alliance.getPlayers()){
                System.out.println("before delete");
                userDao.delete(player.getUser());
                System.out.println("after delete");
                //playerDao.delete(player);
            }
            allianceDao.delete(alliance);
        }
    }

    public Alliance getById(String  uuid){

        System.out.println(uuid);

        return allianceDao.getById(uuid);

    }
}
