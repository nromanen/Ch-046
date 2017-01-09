package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.hibernate.AllianceDao;
import ua.cv.tim.dao.hibernate.PlayerDao;
import ua.cv.tim.dao.hibernate.UserDao;
import ua.cv.tim.mock.AllianceDTOMock;
import ua.cv.tim.mock.TaskMock;
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

    private String id;

    @Autowired
    private AllianceDao allianceDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlayerDao playerDao;





    public List<AllianceDTO> getAll(){

//        allianceDao.getAll();
        return AllianceDTOMock.getAlliances();
    }

    public void addAlliane(AllianceDTO allianceDTO){
        System.out.println("SERVICE ADD WORK");

//        AllianceDTOMock.addAlliance(allianceDTO);

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


        this.id = alliance.getUuid();
    }

    public void updateAlliance(AllianceDTO allianceDTO){


        Alliance alliance = allianceDao.getById(this.id);

        alliance.setName("NEWTESTUPDATENAME");

        alliance.getPlayers().get(0).getUser().setLogin("NEWTESTLOGIN");
        alliance.getPlayers().get(0).getUser().setPassword("EMAILNEWEST");

        allianceDao.update(alliance);



        //AllianceDTOMock.updateAlliances(allianceDTO);
    }

    public void deleteAlliance(String  id){

        Alliance alliance = allianceDao.getById(this.id);
        if (alliance != null){
            allianceDao.delete(alliance);
        }
       //AllianceDTOMock.delete(id);
    }

    public AllianceDTO getById(String  uuid){

        System.out.println(id);

        Alliance getAlliance = allianceDao.getById(this.id);

        System.out.println(getAlliance);

        return null;
    }



























//    @Autowired
////    @Qualifier("AllianceDao")
//    private AllianceDao allianceDao;
//
//    @Autowired
//    private UserService userService;
//
//    public void addAlliance(AllianceDTO allianceDTO) {
//        User leader = new User();
//        leader.setLogin(allianceDTO.getLeaderLogin());
//        leader.setEmail(allianceDTO.getLeaderEmail());
//        List<Role> roles = new ArrayList<>();
//        roles.add(Role.LEADER);
//        roles.add(Role.USER);
//        leader.setPassword("kjdhcbnsmxmojads");
//
//
//        userService.addUser(leader);
//
//        Alliance alliance = new Alliance();
//        alliance.setName(allianceDTO.getName());
//
//        List<User> players = new ArrayList<>();
//        alliance.setUsers(players);
//        allianceDao.add(alliance);
//    }
//
//    public void updateAlliance(AllianceDTO alliance) {
//
////        User user = userService.getByName(alliance.getLeaderLogin());
////        user.setEmail(alliance.getLeaderEmail());
////        user.setLogin(alliance.getLeaderLogin());
////        userService.updateUser(user);
//
////        Alliance alliance1 = allianceDao.getByName(alliance.getName());
////        alliance1.setName(alliance.getName());
////        allianceDao.update(alliance1);
//    }
//
//    public void deleteAlliance(Alliance alliance) {
//        allianceDao.delete(alliance);
//    }
//
//    public Alliance getById(long id) {
//        return allianceDao.getById(id);
//    }

}
