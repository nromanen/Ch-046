package ua.cv.tim.service.impl;

import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.dao.VillageDao;
import ua.cv.tim.model.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Created by okunetc on 07.02.2017.
 */
public class VillageServiceImpTest {

    @InjectMocks
    VillageServiceImp villageServiceImp;
    @Mock
    private VillageDao villageDao;
    @Captor
    ArgumentCaptor<Village> captor;
    @Captor
    ArgumentCaptor<Short> shortArgumentCaptor;
    @Spy
    List<Village> villages=new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        villages = getVillagesList();
    }

    @AfterMethod
    public void resetAllMocks() {
        Mockito.reset(villageDao);
    }

    @Test
    public void testAdd() throws Exception {
        Village village = new Village();
        doNothing().when(villageDao).add(village);
        villageServiceImp.add(village);
        verify(villageDao, times(1)).add(captor.capture());
        assertEquals(captor.getValue(),village);
    }

    @Test
    public void testUpdate() throws Exception {
        doNothing().when(villageDao).update(any());
        Village village=villages.get(0);
        village.setName("new name");
        villageServiceImp.update(village);
        verify(villageDao,times(1)).update(captor.capture());
        assertEquals(captor.getValue().getName(),"new name");
    }

    @Test
    public void testDelete() throws Exception {
        Village village=villages.get(0);
        doNothing().when(villageDao).delete(any());
        villageServiceImp.delete(village);
        verify(villageDao,times(1)).delete(captor.capture());
        assertEquals(captor.getValue(),village);
    }

    @Test
    public void testGetById() throws Exception {
        Village village=villages.get(0);
       when(villageDao.getById(anyString())).thenReturn(village);
       villageServiceImp.getById("village1");
        verify(villageDao,times(1)).getById(anyString());
        assertEquals(villageServiceImp.getById("village1"),village);
    }

    @Test
    public void testGetByCoordinates() throws Exception {
        Village village=villages.get(0);
        when(villageDao.getByCoordinates(anyShort(),anyShort())).thenReturn(village);
        assertEquals(villageServiceImp.getByCoordinates(village.getxCoord(),village.getyCoord()),village);
        verify(villageDao,times(1)).getByCoordinates(anyShort(),anyShort());
    }

    @Test
    public void testIsUnique() throws Exception {
          Village village=villages.get(0);
          when(villageDao.getByName(village.getName())).thenReturn(village);
          when(villageDao.getByCoordinates(village.getxCoord(),village.getyCoord())).thenReturn(village);
          assertTrue(villageServiceImp.isUnique(village));
          verify(villageDao,times(1)).getByName(village.getName());
          verify(villageDao,times(1)).getByCoordinates(village.getxCoord(),village.getyCoord());
    }

    @Test
    public void testGetByName() throws Exception {
        Village village=villages.get(0);
        when(villageDao.getByName(village.getName())).thenReturn(village);
        assertEquals(villageServiceImp.getByName(village.getName()),village);
        verify(villageDao,times(1)).getByName(village.getName());

    }

    private List<Village> getVillagesList() {
        List<Village> villages = new ArrayList<>();
        Alliance alliance = new Alliance();
        alliance.setName("valhala");
        User user = new User();
        user.setEmail("olleg12@ukr.net");
        user.setLogin("oleg");
        user.setPassword("oleg");
        Player player = new Player();
        player.setUser(user);
        player.setAlliance(alliance);
        player.setVillages(villages);
        player.setRace(Race.GAULS);

        Village village = new Village();
        village.setName("Village");
        village.setPlayer(new Player());
        village.setxCoord((short) 58);
        village.setyCoord((short) 32);
        village.setArmies(new ArrayList<>());
        village.setIsCapital(true);
        village.setPopulation((short) 500);
        village.setWall((byte) 20);
        village.setUuid("0");
        villages.add(village);

        Village village1 = new Village();
        village1.setName("Village1");
        village1.setPlayer(new Player());
        village1.setxCoord((short) 5);
        village1.setyCoord((short) 3);
        village1.setArmies(new ArrayList<>());
        village1.setIsCapital(true);
        village1.setPopulation((short) 50);
        village1.setWall((byte) 2);
        village.setUuid("1");

        villages.add(village);
        villages.add(village1);

        return villages;
    }

}