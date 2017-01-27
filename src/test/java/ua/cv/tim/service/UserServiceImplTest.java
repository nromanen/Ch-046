package ua.cv.tim.service;

import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.WebConfigurationTest;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.utils.UserServiceTestUtils;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by mmaksymtc on 24.01.2017.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfigurationTest.class})
public class UserServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    UserService userService;

    @Autowired
    private UserServiceTestUtils userTestUtils;

    @Spy
    List<User> users = new ArrayList<User>();

    @BeforeClass
    public void setUp(){
        users=getUserList();
    }

    @Test
    public void getUserByUsernameTest() {
        assertNotNull(userService.getUserByUsername("neo"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addUserTest(){
        userService.add(users.get(1));
        User testUser = userTestUtils.getUserByUsername(users.get(1).getLogin());
        assertEquals(userTestUtils.getUsersList().size(),4);
        assertNotNull(userTestUtils.getById(testUser.getUuid()));
    }
    @Transactional
    @Rollback(true)
    @Test
    public void deleteByIdTest(){
        User testUser = userTestUtils.getUserByUsername("neo");
        userService.deleteById(testUser.getUuid());
        assertEquals(userTestUtils.getUsersList().size(),2);
        assertNull(userTestUtils.getById(testUser.getUuid()));
    }
    @Test
    public void getAllTest(){
        assertEquals(userService.getAll().size(),3);
    }

    @Transactional
    @Rollback(true)
    @Test
    public void updateTest(){
        User testUser = userTestUtils.getUserByUsername("neo");
        testUser.setLogin("updatedNeo");
        userService.update(testUser);
        assertEquals(userTestUtils.getById(testUser.getUuid()),testUser);
    }

    @Test
    public void isUniqueTest(){
        User user0=new User();
        user0.setLogin("neo");
        List<Role> roles=new ArrayList<>();
        roles.add(Role.ADMIN);
        roles.add(Role.LEADER);
        user0.setRoles(roles);
        user0.setPassword("111");
        user0.setUuid("7564d81e-b4c7-440f-8f55-ea8938ec0f37");
        user0.setLastModified(new Date());
        user0.setEmail("neo@ukr.net");
        user0.setRoles(roles);
        //User testUser = userTestUtils.getUsersList().get(0);
        //logger.info("Unique user {}  ",user0);
        //User testUser2 = getUserList().get(1);
        assertTrue(userService.isUnique(user0));
        //assertTrue(userService.isUnique(testUser2));
    }
    @Test
    public void getCountTest() {
        assertEquals(userService.getCount(),3);
    }
    @Test
    public void getWithRolesByIdTest(){
        User testUser = userTestUtils.getUserByUsername("neo");
        assertEquals(userService.getWithRolesById(testUser.getUuid()),testUser);
    }
    @Test
    public void getAllWithRolesTest(){
        assertEquals(userService.getAllWithRoles().size(),3);
        assertNotNull(userService.getAllWithRoles().get(0).getRoles());
    }
    @Test
    public void getByIdTest(){
        assertEquals(userService.getById("7564d81e-b4c7-440f-8f55-ea8938ec0f37"),userTestUtils.getUserByUsername("neo"));
        assertNull(userService.getById("64d81e-b4c7-440f-8f55-ea8938ec0f37"));
    }
    @Transactional
    @Rollback(true)
    @Test
    public void addAllianceMemberTest() throws MessagingException {
        UserDTO member = new UserDTO(null, "Jonathan", "jonatahan@ukr.net","valhala");
        userService.addUser(member);
        assertNotNull(userTestUtils.getUserByUsername("Jonathan"));
    }
    @Test
    public void getUsersByAllianceTest(){
        assertEquals(userService.getUsersByAlliance("valhala").get(0).getLogin(),"neo");
        assertEquals(userService.getUsersByAlliance("valhala").size(),1);
    }


    private List<User> getUserList(){
        User user0=new User();
        user0.setLogin("JimBean");
        List<Role> roles=new ArrayList<>();
        roles.add(Role.ADMIN);
        roles.add(Role.LEADER);
        user0.setRoles(roles);
        user0.setPassword("kkhsj0~fkO");
        user0.setLastModified(new Date());
        user0.setEmail("johnee@ukr.net");
        user0.setRoles(roles);

        User user1=new User();
        user1.setLogin("JackDaniels");
        List<Role> roles1=new ArrayList<>();
        roles1.add(Role.ADMIN);
        roles1.add(Role.LEADER);
        user1.setRoles(roles1);
        user1.setPassword("khyg5DkO");
       // user1.setUuid("11a66a3f-551d-4320-a6d0-9fd4fb6ff2e7");
        user1.setLastModified(new Date());
        user1.setEmail("jackee@ukr.net");
        user1.setRoles(roles1);

        users.add(user0);
        users.add(user1);
        return users;
    }
}
