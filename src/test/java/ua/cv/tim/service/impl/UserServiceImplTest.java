package ua.cv.tim.service.impl;


import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.*;
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
    @AfterMethod
    public void resetAllMocks(){
        Mockito.reset(allianceDao,playerDao,userDao,sendMail);
    }

    @Test
    public void testGetUserByUsername() {
       User user = users.get(0);
        when(userDao.getUserByUsername("neo")).thenReturn(user);
        assertEquals(userService.getUserByUsername("neo"), user);
        verify(userDao, times(1)).getUserByUsername("neo");
        assertNotNull(userService.getUserByUsername("neo"));
    }
    @Test
    public void testAddUser(){
        User user = users.get(0);
        doNothing().when(userDao).add(any(User.class));
        userService.add(user);
        verify(userDao, times(1)).add(captor.capture());
        Assert.assertEquals(captor.getValue().getLogin(), "neo");
    }
    @Test
    public void testDelete(){
        doNothing().when(userDao).delete(any(User.class));
        userService.delete(users.get(0));
        verify(userDao, times(1)).delete(any(User.class));
    }
    @Test
    public void testGetAll()
    {
        when(userDao.getAll()).thenReturn(users);
        Assert.assertEquals(userService.getAll(), users);
        verify(userDao, times(1)).getAll();
    }
    @Test
    public void testUpdate() throws MessagingException {
        User testUser = users.get(0);
        testUser.setLogin("updatedNeo");
        doNothing().when(userDao).update(any(User.class));
        doNothing().when(sendMail).send(anyString(),anyString(),anyString());
        userService.update(testUser);
        verify(userDao, times(1)).update(captor.capture());
        Assert.assertEquals(captor.getValue().getLogin(), "updatedNeo");
    }

    @Test
    public void testIsUnique(){
        User user = users.get(0);
        User newUser = users.get(1);
        when(userDao.getUserByUsername("neo")).thenReturn(user);
        when(userDao.getByMail("neo@ukr.net")).thenReturn(user);
        assertTrue(userService.isUnique(newUser));
        verify(userDao, times(1)).getUserByUsername(anyString());
        verify(userDao, times(1)).getByMail(anyString());
    }

    @Test
    public void testGetWithRolesById(){
        User user = users.get(0);
        when(userDao.getWithRolesById(anyString())).thenReturn(user);
        assertEquals(userService.getWithRolesById("3455-ede34-de4dee-de34d"),user);
        verify(userDao, times(1)).getWithRolesById(anyString());
    }
    @Test
    public void testGetAllWithRoles(){
        when(userDao.getAllWithRoles()).thenReturn(users);
        assertEquals(userService.getAllWithRoles(),users);
        verify(userDao, times(1)).getAllWithRoles();
    }
    @Test
    public void testGetById(){
        User user = users.get(0);
        when(userDao.getById(anyString())).thenReturn(user);
        assertEquals(userService.getById("3455-ede34-de4dee-de34d"),user);
        verify(userDao, times(1)).getById(anyString());
    }
    @Test
    public void testAddUserDTO() throws MessagingException {
        UserDTO member = new UserDTO(null, "Jonathan", "jonatahan@ukr.net","valhala");
        Alliance alliance = new Alliance();
        alliance.setName("valhala");
        doNothing().when(userDao).add(any(User.class));
        when(allianceDao.getAllianceByName(anyString())).thenReturn(alliance);
        doNothing().when(playerDao).add(any(Player.class));
        doNothing().when(sendMail).send(anyString(),anyString(),anyString());
        userService.addUser(member);
        verify(userDao, times(1)).add(captor.capture());
        Assert.assertEquals(captor.getValue().getLogin(), "Jonathan");

    }
    @Test
    public void tetsGetUserWithAlliance(){
        User user = users.get(0);
        when(userDao.getUserWithAlliance(anyString())).thenReturn(user);
        assertEquals(userService.getUserWithAlliance("valhala"),user);
        verify(userDao, times(1)).getUserWithAlliance(anyString());
    }
    @Test
    public void testGetUsersByAlliance(){

        when(userDao.getUsersByAlliance(anyString())).thenReturn(users);
        assertNotNull(userService.getUsersByAlliance("valhala"));
        verify(userDao, times(1)).getUsersByAlliance(anyString());
    }
    @Test
    public void testGetFullUserByUsername() {
        List<Village> villages=new ArrayList<>();
        Village village0 = new Village();
        village0.setName("Transilvania");
        village0.setxCoord((short) 300);
        village0.setyCoord((short) 177);
        village0.setPopulation((short)33);
        village0.setIsCapital(true);
        List<Army> armies = new ArrayList<>();
        Army army0 = new Army();
        army0.setOwningVillage(village0);
        army0.setType(UnitType.Axeman);
        army0.setCount(300);
        armies.add(army0);
        village0.setArmies(armies);
        villages.add(village0);
        User user = users.get(0);
        user.getPlayer().setVillages(villages);
        when(userDao.getFullUserByUsername(anyString())).thenReturn(user);
        assertEquals(userService.getFullUserByUsername("neo").toString(),user.toString());
        verify(userDao, times(1)).getFullUserByUsername(anyString());
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
