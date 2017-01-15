package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.configuration.WebConfiguration;
import ua.cv.tim.dao.UserDao;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by okunetc on 12.01.2017.
 */

@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfiguration.class, WebConfiguration.class})
public class UserServiceImplTest  extends AbstractTestNGSpringContextTests {

    @Test
    public void getAllWithRoles() throws Exception {
        List<User> allWithRoles = userService.getAllWithRoles();
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = userService.getAll();
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

        User user = new User();
        user.setUuid("1e2381a4-6e22-457f-875d-932f546b1c08");
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("olleg12@ukr.net");
        userService.update(user);

    }

    @Test
    public void testDelete() throws Exception {
        long sizeBefore=userService.getCount();
        User user = new User();
//        User user1=new User();
//        user.setLogin("login");
//        user.setPassword("password");
//        user.setEmail("olleg12@ukr.net");
        user.setUuid("6341319a-bcfe-46a4-9bde-4a3e3a2519f6");
        userService.deleteById(user.getUuid());
        long sizeAfter=userService.getCount();
        //assertEquals(sizeBefore-sizeAfter,1);
    }

    @Test
    public void testGetById() throws Exception {
        User user = userService.getById("06a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");

    }


}