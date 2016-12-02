package com.ss.schedule.dao;

import com.ss.schedule.model.TimeTable;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by oleg on 01.12.16.
 */
public class TimeTableDaoTEST {
    TimeTableDao tableDaoTEST=new TimeTableDao();

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {
        TimeTable byId = tableDaoTEST.getById(1);
        System.out.println(byId);
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        List<TimeTable> all = tableDaoTEST.getAll();
        System.out.println(all);
    }

    @Test
    public void testGetTimetable() throws Exception {

    }

}