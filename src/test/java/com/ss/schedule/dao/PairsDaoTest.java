package com.ss.schedule.dao;

import com.ss.schedule.model.Pair;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by oleg on 01.12.16.
 */
public class PairsDaoTest {


    @Test
    public void testGetEntitiIdByName() throws Exception {
        long first = pairsDao.getEntitiIdByName("FIRST");
        assertEquals(first,14);
    }


    PairsDao pairsDao=new PairsDao();
    @Test
    public void testGetAll() throws Exception {

        assertEquals(Pair.values().length,pairsDao.getAll().size());
    }

    @Test
    public void testGetById() throws Exception {
        Pair byId = pairsDao.getById(14);
        assertEquals(byId.name(),Pair.FIRST.name());
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
        //pairsDao.addTestData();

    }



}