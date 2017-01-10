package ua.cv.tim.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.web.WebAppConfiguration;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.dao.hibernate.UserDao;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by Oleg on 04.01.2017.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class UserServiceImplTest {
    @Test
    public void getAllWithRoles() throws Exception {
        List<User> allWithRoles = userService.getAllWithRoles();
    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void getWithRolesById() throws Exception {
        User user = userService.getWithRolesById("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");

    }

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;
    @Test
    public void testGetCount() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        long sizeBefore=userService.getCount();
        User user=new User();
        user.setLogin("kk");
        List<Role> roles=new ArrayList<>();
        roles.add(Role.ADMIN);
        roles.add(Role.LEADER);
        user.setRoles(roles);
        user.setPassword("kkh");
        user.setUuid("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        user.setLastModified(new Date());
        user.setEmail("oh");
        user.setRoles(roles);
        userService.add(user);
        long sizeAfter=userService.getCount();
        assertEquals(sizeAfter-sizeBefore,1);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = userService.getById("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        user.setEmail("olleg12@ukr.net");
        userService.update(user);
    }

    @Test
    public void testDelete() throws Exception {
        long sizeBefore=userService.getCount();
        User user = userService.getById("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        User user1 = userService.getById("c804b1ac-2841-4369-9eca-9726a34585c2");
//        userService.delete(user1);
        userService.delete(user);
        long sizeAfter=userService.getCount();
        assertEquals(sizeBefore-sizeAfter,1);
    }

    @Test
    public void testGetById() throws Exception {
        User user = userService.getById("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");

    }






}