package ua.cv.tim.service.impl;

import org.mockito.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Created by mmaksymtc on 10.02.2017.
 */
public class PlayerServiceImplTest {

    @Mock
    private PlayerDao playerDao;
    @InjectMocks
    private PlayerServiceImpl playerServiceImpl;

    private Player testPlayer;

    @Captor
    ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testPlayer = new Player();
        User user = new User();
        testPlayer.setUuid("333-222");
        testPlayer.setRace(Race.GAULS);
        List<Village> villages = new ArrayList<>();
        Village village0 = new Village();
        village0.setName("Che");
        village0.setIsCapital(true);
        villages.add(village0);
        testPlayer.setVillages(villages);
        testPlayer.setUser(user);
        Alliance alliance = new Alliance();
        alliance.setName("ALALALA");
        testPlayer.setAlliance(alliance);
    }

    @Test
    public void testAdd() throws Exception {
        doNothing().when(playerDao).add(any(Player.class));
        playerServiceImpl.add(testPlayer);
        verify(playerDao,times(1)).add(playerCaptor.capture());
        assertEquals(playerCaptor.getValue().getUuid(),testPlayer.getUuid());
    }

    @Test
    public void testGetById() throws Exception {
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        String id = "1221-3232";
        when(playerDao.getById(anyString())).thenReturn(testPlayer);
        playerServiceImpl.getById(id);
        verify(playerDao,times(1)).getById(stringCaptor.capture());
        assertEquals(stringCaptor.getValue(),id);
    }

    @Test
    public void testGetByIdWithVillages() throws Exception {
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        String id = "111-222";
        when(playerDao.getByIdWithVillages(anyString())).thenReturn(testPlayer);
        playerServiceImpl.getByIdWithVillages(id);
        verify(playerDao,times(1)).getByIdWithVillages(stringCaptor.capture());
        assertEquals(stringCaptor.getValue(),id);
        assertTrue(playerDao.getByIdWithVillages(id).getVillages().equals(testPlayer.getVillages()));
    }

    @Test
    public void testUpdate() throws Exception {
        Player player = testPlayer;
        player.setUuid("1111-4321");
        doNothing().when(playerDao).update(any(Player.class));
        playerServiceImpl.update(player);
        verify(playerDao, times(1)).update(playerCaptor.capture());
        assertEquals(playerCaptor.getValue().getUuid(), player.getUuid());
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(playerDao).delete(any(Player.class));
        playerServiceImpl.delete(testPlayer);
        verify(playerDao, times(1)).delete(playerCaptor.capture());
        assertEquals(playerCaptor.getValue().getUuid(), testPlayer.getUuid());
    }

    @Test
    public void testGetPlayersByAllianceWithVillages() throws Exception {
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        String allianceName = "alala";
        List<Player> players = new ArrayList<>();
        players.add(testPlayer);
        when(playerDao.getPlayersByAllianceWithVillages(anyString())).thenReturn(players);
        playerServiceImpl.getPlayersByAllianceWithVillages(allianceName);
        verify(playerDao, times(1)).getPlayersByAllianceWithVillages(stringCaptor.capture());
        assertEquals(stringCaptor.getValue(),allianceName);

    }

}