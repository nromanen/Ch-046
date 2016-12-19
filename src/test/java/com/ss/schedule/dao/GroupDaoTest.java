package com.ss.schedule.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.institute.Util;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subgroup;
import com.ss.schedule.model.Subject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by oleg on 03.12.16.
 */
public class GroupDaoTest {
    GroupDao groupDao;
    @BeforeTest
    public void setUp() throws Exception {
        groupDao=new GroupDao();
    }

    @Test
    public void testGetAll() throws Exception {
        List<Group> all =
                groupDao.getAll();
    }

    @Test
    public void testGetById() throws Exception {
//        Group byId = groupDao.getById(390);
//        System.out.println(byId);
//        Group group = groupDao.getById(495);
//        List<Subgroup> subgroupById = groupDao.getSubgroupsOfGroup(group);
//        List<Group> groupsOfStream = new Groups_subjectsDao().getGroupsOfStream(new JdbcSubjectDao().getById(121));
        groupDao.getStudentCommunityById(1061);
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        List<Subject> subjectList=new JdbcSubjectDao().getAll();
        InputOutputJson<List<Group>> groupInputOutputJson=new InputOutputJson<>(new TypeReference<List<Group>>() {
        });
        List<Group> groups = groupInputOutputJson.readFromFile("/home/oleg/Рабочий стол/Ch-046 (copy)" +
                " (другая копия)/src/test/resources/group1.json");
        List<Subject> subjects = new JdbcSubjectDao().getAll();

       Util util =new Util();
        util.setGroups(groups);
        util.setSubjects(subjects);

//        for (Group gr: util.getGroups()
//             ) {
//                gr.setSubjects(subjects);
//        }
//        util.setGroups(groupDao.getAll());
//
//        util.getGroupsSubgroupsStreams();
        for (Group group: util.getGroups()) {
            groupDao.add(group);
        }

//        for (Group group:util.getGroups()){
//            groupDao.addSubgroupsOfGroup(group);
//        }
    }

}