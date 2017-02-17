package ua.cv.tim.service.impl;



import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.dao.AllianceDao;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.UserService;
import ua.cv.tim.utils.SendMail;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by mmaksymtc on 26.01.2017.
 */

public class AllianceServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(AllianceServiceImplTest.class);

    @Mock
    AllianceDao allianceDao;
    @Mock
    UserService userService;
    @Mock
    UserDao userDao;
    @Mock
    SendMail sendMail;
    @Mock
    MessageSource messageSource;
    @InjectMocks
    AllianceServiceImpl allianceService;
    @Spy
    List<Alliance> alliancesList = new ArrayList<>();
    @Captor
    ArgumentCaptor<Alliance> captor;
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        alliancesList= getAlliancesList();
    }
    @AfterMethod
    public void resetAllMocks(){
        Mockito.reset(allianceDao,userService,userDao,sendMail);
    }
    @Test
    public void testGetAll(){
        List<AllianceDTO> allianceDTOsList = new ArrayList<>();
        for(Alliance alliance:alliancesList){
            allianceDTOsList.add(convertAllianceToDTO(alliance));
        }
        when(allianceDao.getAll()).thenReturn(alliancesList);
        allianceService.getAll();
        verify(allianceDao, times(1)).getAll();
        assertEquals(allianceService.getAll(),allianceDTOsList);
    }

    @Test
    public void testAddAlliance() throws MessagingException {
        doNothing().when(allianceDao).add(any(Alliance.class));
        doNothing().when(userService).addUser(any(UserDTO.class));
        when(userService.getUserByUsername(anyString())).thenReturn(new User());
        AllianceDTO allianceDTO = new AllianceDTO("9876d81e-b4c7-440f-8f55-ea8938ewdf54", "1234d81e-b4c7-440f-8f55-ea8938ec0f37", "alalala", "newLeader", "newLeader@ukr.net");
        allianceService.addAlliance(allianceDTO);
        verify(allianceDao, times(1)).add(captor.capture());
        verify(userService, times(1)).addUser(any(UserDTO.class));
        assertEquals(captor.getValue().getName(), allianceDTO.getName());
    }

    @Test
    public void testUpdateAlliance() throws MessagingException {
        AllianceDTO allianceDTO = new AllianceDTO("7564d81e-b4c7-440f-8f55-ea8938ewdf54",
                "6666d81e-b4c7-440f-8f55-ea8938ewdf54", "TRATATAalalala",
                "allianceLeader", "newLeader@ukr.net");
        Alliance alliance = alliancesList.get(0);
        when(allianceDao.getById(anyString())).thenReturn(alliance);
        doNothing().when(allianceDao).update(any(Alliance.class));
        doNothing().when(sendMail).send(anyString(),anyString(),anyString());
        allianceService.updateAlliance(allianceDTO);
        verify(allianceDao, times(1)).update(captor.capture());
        assertEquals(captor.getValue().getName(), allianceDTO.getName());

    }

    @Test
    public void testDeleteAlliance(){
        Alliance alliance = alliancesList.get(0);
        when(allianceDao.getById(anyString())).thenReturn(alliance);
        doNothing().when(userDao).delete(any(User.class));
        doNothing().when(allianceDao).delete(any(Alliance.class));
        allianceService.deleteAlliance(anyString());
        verify(allianceDao, atLeastOnce()).delete(captor.capture());
        assertEquals(captor.getValue().getName(), alliance.getName());
    }
    @Test
    public void testGetById(){
        Alliance alliance = alliancesList.get(0);
        when(allianceDao.getById(anyString())).thenReturn(alliance);
        allianceService.getById(alliance.getUuid());
        verify(allianceDao, times(1)).getById(anyString());
        assertEquals(allianceService.getById(anyString()),alliance);

    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIsUniqueAllianceExpectException(){
        Alliance alliance = alliancesList.get(0);
        when(allianceDao.getByName(anyString(),anyString())).thenReturn(alliance);
        when(messageSource.getMessage(any(),any(),any())).thenReturn("Error message");
        allianceService.isUniqueAlliance(alliance.getName(), alliance.getUuid());
        verify(allianceDao, times(1)).getByName(captor.capture().getName(),captor.capture().getUuid());
        assertEquals(captor.getValue().getName(), alliance.getName());
        assertEquals(captor.getValue().getUuid(), alliance.getUuid());
    }
    @Test
    public void testIsUniqueAlliance(){
        Alliance alliance = alliancesList.get(0);
        when(allianceDao.getByName(anyString(),anyString())).thenReturn(null);
        allianceService.isUniqueAlliance(alliance.getName(),alliance.getUuid());
        verify(allianceDao, times(1)).getByName(alliance.getName(),alliance.getUuid());
        assertTrue(allianceService.isUniqueAlliance(alliance.getName(),alliance.getUuid()));
    }


    private List<Alliance> getAlliancesList(){
        Alliance alliance0= new Alliance();
        alliance0.setName("valhala");
        alliance0.setUuid("9999d81e-b4c7-440f-8f55-ea8938ewdf54");

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
        player0.setAlliance(alliance0);
        user0.setPlayer(player0);

        User user1=new User();
        user1.setLogin("JackDaniels");
        List<Role> roles1=new ArrayList<>();
        roles1.add(Role.ADMIN);
        user1.setRoles(roles1);
        user1.setPassword("khyg5DkO");
        user1.setUuid("11a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        user1.setLastModified(new Date());
        user1.setEmail("jackee@ukr.net");
        user1.setRoles(roles1);
        Player player1 = new Player();
        player1.setUser(user1);
        player1.setAlliance(alliance0);
        user1.setPlayer(player1);

        List<Player> playersList = new ArrayList<>();
        playersList.add(player0);
        playersList.add(player1);
        alliance0.setPlayers(playersList);
        alliancesList.add(alliance0);
        return  alliancesList;
    }

    private AllianceDTO convertAllianceToDTO(Alliance alliance){
        User leader = null;
        for(Player player : alliance.getPlayers()){
            if (player.getUser().getRoles().contains(Role.LEADER)){
                leader = player.getUser();
            }
        }
        AllianceDTO allianceDTO = new AllianceDTO(alliance.getUuid(), leader.getUuid(), alliance.getName(), leader.getLogin(), leader.getEmail());
         return allianceDTO;
    }
    }

