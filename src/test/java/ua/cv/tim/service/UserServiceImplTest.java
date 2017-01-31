package ua.cv.tim.service;


import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.impl.UserServiceImpl;
import ua.cv.tim.utils.SendMail;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.Assert.*;
import static sun.audio.AudioPlayer.player;

/**
 * Created by mmaksymtc on 24.01.2017.
 */

public class UserServiceImplTest {

    @Mock
    UserDao userDao;
    @Mock
    AllianceDao allianceDao;
    @Mock
    PlayerDao playerDao;
    @Mock
    SendMail sendMail;
    @InjectMocks
    UserServiceImpl userService;
    @Captor
    ArgumentCaptor<User> captor;

    @Spy
    List<User> users = new ArrayList<User>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        users=getUserList();
    }

    @Test
    public void getUserByUsernameTest() {
       User user = users.get(0);
        when(userDao.getUserByUsername("neo")).thenReturn(user);
        assertEquals(userService.getUserByUsername("neo"), user);
        verify(userDao, times(1)).getUserByUsername("neo");
        assertNotNull(userService.getUserByUsername("neo"));
    }
    @Test
    public void addUserTest(){
        doNothing().when(userDao).add(any(User.class));
        userService.add(users.get(0));
        verify(userDao, times(1)).add(captor.capture());
        Assert.assertEquals(captor.getValue().getLogin(), "neo");
        Assert.assertEquals(2, users.size());
        verify(users, times(1)).add(any(User.class));
    }
    @Test
    public void deleteTest(){
        doNothing().when(userDao).delete(any(User.class));
        userService.delete(users.get(0));
        verify(userDao, times(1)).delete(any(User.class));
    }
    @Test
    public void getAllTest()
    {
        when(userDao.getAll()).thenReturn(users);
        Assert.assertEquals(userService.getAll(), users);
        verify(userDao, times(1)).getAll();
    }
    @Test
    public void updateTest(){
        User testUser = users.get(0);
        testUser.setLogin("updatedNeo");
        doNothing().when(userDao).update(any(User.class));
        userService.update(testUser);
        verify(userDao, times(1)).update(captor.capture());
        Assert.assertEquals(captor.getValue().getLogin(), "updatedNeo");
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void isUniqueTest(){
        User user = users.get(0);
        when(userDao.getUserByUsername("neo")).thenReturn(user);
        when(userDao.getByMail("neo@ukr.net")).thenReturn(user);
        assertFalse(userService.isUnique(user));
        verify(userDao, times(1)).getUserByUsername(anyString());
        verify(userDao, times(1)).getByMail(anyString());
    }

    @Test
    public void getWithRolesByIdTest(){
        User user = users.get(0);
        when(userDao.getWithRolesById(anyString())).thenReturn(user);
        assertEquals(userService.getWithRolesById("3455-ede34-de4dee-de34d"),user);
        verify(userDao, times(1)).getWithRolesById(anyString());
    }
    @Test
    public void getAllWithRolesTest(){
        when(userDao.getAllWithRoles()).thenReturn(users);
        assertEquals(userService.getAllWithRoles(),users);
        verify(userDao, times(1)).getAllWithRoles();
    }
    @Test
    public void getByIdTest(){
        User user = users.get(0);
        when(userDao.getById(anyString())).thenReturn(user);
        assertEquals(userService.getById("3455-ede34-de4dee-de34d"),user);
        verify(userDao, times(1)).getById(anyString());
    }
    @Test(expectedExceptions = MessagingException.class)
    public void addUserDTOTest() throws MessagingException {
        User user = new User();
        user.setLogin("Jonathan");
        user.setEmail("jonatahan@ukr.net");
        UserDTO member = new UserDTO(null, user.getLogin(), user.getEmail(),"valhala");
        Alliance alliance = new Alliance();
        alliance.setName("valhala");
        doNothing().when(userDao).add(any(User.class));
        when(allianceDao.getAllianceByName(anyString())).thenReturn(alliance);
        doNothing().when(playerDao).add(any(Player.class));
        //when(userService.sendEmail(user));
        userService.addUser(member);
        verify(userDao, times(1)).add(captor.capture());
//        verify(userService, times(1)).sendEmail(any(User.class));
        Assert.assertEquals(captor.getValue().getLogin(), "Jonathan");
        doThrow(RuntimeException.class).when(userService).sendEmail(user);
      //  verify(users, times(2)).add(any(User.class));
       // assertNotNull(userTestUtils.getUserByUsername("Jonathan"));
    }
    @Test
    public void getUserWithAllianceTest(){
        User user = users.get(0);
        when(userDao.getUserWithAlliance(anyString())).thenReturn(user);
        assertEquals(userService.getUserWithAlliance("valhala"),user);
        verify(userDao, times(1)).getUserWithAlliance(anyString());
    }
    @Test
    public void getUsersByAllianceTest(){

        when(userDao.getUsersByAlliance(anyString())).thenReturn(users);
        assertNotNull(userService.getUsersByAlliance("valhala"));
        verify(userDao, times(1)).getUsersByAlliance(anyString());
    }

    private List<User> getUserList(){
        User user0=new User();
        user0.setLogin("neo");
        List<Role> roles=new ArrayList<>();
        roles.add(Role.ADMIN);
        roles.add(Role.LEADER);
        user0.setRoles(roles);
        user0.setPassword("111");
        user0.setUuid("3455-ede34-de4dee-de34d");
        user0.setLastModified(new Date());
        user0.setEmail("neo@ukr.net");
        user0.setRoles(roles);
        Player player0 = new Player();
        player0.setUser(user0);
        Alliance alliance = new Alliance();
        alliance.setName("valhala");
        player0.setAlliance(alliance);
        user0.setPlayer(player0);

        User user1=new User();
        user1.setLogin("JackDaniels");
        List<Role> roles1=new ArrayList<>();
        roles1.add(Role.ADMIN);
        roles1.add(Role.LEADER);
        user1.setRoles(roles1);
        user1.setPassword("khyg5DkO");
        user1.setUuid("11a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        user1.setLastModified(new Date());
        user1.setEmail("jackee@ukr.net");
        user1.setRoles(roles1);
        Player player1 = new Player();
        player1.setUser(user1);
        player1.setAlliance(alliance);
        user1.setPlayer(player1);

        users.add(user0);
        users.add(user1);
        return users;
    }
}
