package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.hibernate.AllianceDao;
import ua.cv.tim.mock.AllianceDTOMock;
import ua.cv.tim.mock.TaskMock;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.Task;
import ua.cv.tim.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 30.12.16.
 */

@Service("allianceService")
@Transactional
public class AllianceService {





    public List<AllianceDTO> getAll(){
        return AllianceDTOMock.getAlliances();
    }

    public void addAlliane(AllianceDTO allianceDTO){
        System.out.println("SERVICE ADD WORK");

        AllianceDTOMock.addAlliance(allianceDTO);
    }

//    public void updateAlliance(AllianceDTO allianceDTO){
//        AllianceDTOMock.updateAlliances(allianceDTO);
//    }
//
//    public void deleteAlliance(String  id){
//       AllianceDTOMock.delete(id); ;
//    }
//
//    public AllianceDTO getById(String  id){
//        return AllianceDTOMock.getById(id);
//    }



























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
