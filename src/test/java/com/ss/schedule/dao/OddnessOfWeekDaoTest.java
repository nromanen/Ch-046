package com.ss.schedule.dao;

import com.ss.schedule.model.OddnessOfWeek;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by oleg on 01.12.16.
 */
public class OddnessOfWeekDaoTest {
    @Test
    public void testAddTestData() throws Exception {
      oddnessOfWeekDao.addTestData();
        assertEquals(OddnessOfWeek.values().length,oddnessOfWeekDao.getAll().size());
    }

    private OddnessOfWeekDao oddnessOfWeekDao = new OddnessOfWeekDao();
    @Test
    public void testGetAll() throws Exception {
        int size = oddnessOfWeekDao.getAll().size();
        int length = OddnessOfWeek.values().length;
        assertEquals(size,length);
    }

    @Test
    public void testGetById() throws Exception {
        OddnessOfWeek byId = oddnessOfWeekDao.getById(3);
        assertEquals(byId,OddnessOfWeek.ALL);
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {

    }

}