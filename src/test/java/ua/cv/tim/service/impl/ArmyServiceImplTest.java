package ua.cv.tim.service.impl;

import org.mockito.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.dao.ArmyDao;
import ua.cv.tim.model.Army;
import ua.cv.tim.model.UnitType;
import ua.cv.tim.model.Village;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Created by mmaksymtc on 10.02.2017.
 */
public class ArmyServiceImplTest {
    @Mock
    private ArmyDao armyDao;
    @InjectMocks
    ArmyServiceImpl armyServiceImpl;
    @Captor
    ArgumentCaptor<Army> armyCaptor = ArgumentCaptor.forClass(Army.class);

    private Army testArmy;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testArmy = new Army();
        testArmy.setType(UnitType.Axeman);
        testArmy.setCount(10);
        testArmy.setUuid("123-321");
        Village village = new Village();
        village.setName("TestVillage");
        testArmy.setOwningVillage(village);
    }
    @Test
    public void testAdd() throws Exception {
        doNothing().when(armyDao).add(any(Army.class));
        armyServiceImpl.add(testArmy);
        verify(armyDao,times(1)).add(armyCaptor.capture());
        assertEquals(armyCaptor.getValue().getUuid(),testArmy.getUuid());
    }

    @Test
    public void testUpdate() throws Exception {
        testArmy.setUuid("4556-321-kfjfr");
        doNothing().when(armyDao).update(any(Army.class));
        armyServiceImpl.update(testArmy);
        verify(armyDao,times(1)).update(armyCaptor.capture());
        assertEquals(armyCaptor.getValue().getUuid(),testArmy.getUuid());
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(armyDao).delete(any(Army.class));
        armyServiceImpl.delete(testArmy);
        verify(armyDao,times(1)).delete(armyCaptor.capture());
        assertEquals(armyCaptor.getValue().getType(),testArmy.getType());
    }

    @Test
    public void testGetArmyById() throws Exception {
        String armyUUID = "435-565-767";
        ArgumentCaptor<String> armyUUIDCaptor = ArgumentCaptor.forClass(String.class);
        when(armyDao.getArmyById(anyString())).thenReturn(testArmy);
        armyServiceImpl.getArmyById(armyUUID);
        verify(armyDao,times(1)).getArmyById(armyUUIDCaptor.capture());
        assertEquals(armyUUIDCaptor.getValue(),armyUUID);
    }

    @Test
    public void testGetById() throws Exception {
        String armyUUID = "435-565-767";
        ArgumentCaptor<String> armyUUIDCaptor = ArgumentCaptor.forClass(String.class);
        when(armyDao.getById(anyString())).thenReturn(testArmy);
        armyServiceImpl.getById(armyUUID);
        verify(armyDao,times(1)).getById(armyUUIDCaptor.capture());
        assertEquals(armyUUIDCaptor.getValue(),armyUUID);
    }

}