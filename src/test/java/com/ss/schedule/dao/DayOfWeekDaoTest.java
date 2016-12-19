package com.ss.schedule.dao;

import com.ss.schedule.model.DayOfWeek;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by oleg on 01.12.16.
 */
public class DayOfWeekDaoTest {
    DayOfWeekDao dayOfWeekDao=new DayOfWeekDao();
    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {

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

    @Test
    public void testAddTestData() throws Exception {
        dayOfWeekDao.addTestData();
        assertEquals(dayOfWeekDao.getAll().size(), DayOfWeek.values().length);
    }

}