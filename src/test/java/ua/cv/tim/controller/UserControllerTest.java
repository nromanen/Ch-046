package ua.cv.tim.controller;

import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.WebConfiguration;
import ua.cv.tim.configuration.WebSecurityConfiguration;
import ua.cv.tim.dto.UserDTO;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.UserService;
import ua.cv.tim.utils.TestUtil;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by mmaksymtc on 03.02.2017.
 */


@ContextConfiguration(classes = {WebSecurityConfiguration.class,/*WebSecurityInitializer.class,*/ WebConfiguration.class})
@WebAppConfiguration
public class UserControllerTest  {

    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;
    private MockMvc mockMvc;
    @Captor
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
    @Spy
    List<User> usersList = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        usersList = getUsersList();
    }

    @AfterMethod
    public void resetAllMocks() {
        Mockito.reset(userService);
    }


    @Test
    public void testGetAllUsersWhereEmptyList() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        List<User> testUsersList = new ArrayList<>();
        when(userService.getAllWithRoles()).thenReturn(testUsersList);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user/userList");
        this.mockMvc.perform(builder)
                .andExpect(status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
        verify(userService, times(1)).getAllWithRoles();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        when(userService.getAllWithRoles()).thenReturn(usersList);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/user/userList")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(usersList));

        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.*.login").value(usersList.get(0).getLogin()))
                .andExpect(jsonPath("$.*.email").value(usersList.get(0).getEmail()))
                .andDo(MockMvcResultHandlers.print());
        verify(userService, times(1)).getAllWithRoles();
    }

    @Test
    public void testGetByIdWhereUserIsNull() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        User testUser = new User();
        when(userService.getWithRolesById(anyString())).thenReturn(testUser);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/user/user/{id}", testUser.getUuid());

        this.mockMvc.perform(builder)
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetById() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        User user = usersList.get(0);
        when(userService.getWithRolesById(anyString())).thenReturn(user);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/user/user/{id}", user.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(user));

        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.uuid", is(user.getUuid())))
                .andExpect(jsonPath("$.login", is(user.getLogin())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAddUser() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        ArgumentCaptor<UserDTO> userDTOCaptor = ArgumentCaptor.forClass(UserDTO.class);
        User user = usersList.get(0);
        UserDTO testUserDTO = new UserDTO();
        testUserDTO.setLogin("testLogin");
        testUserDTO.setEmail("anymail@mail.ua");
        testUserDTO.setUuid("3434-3434-tete");
        when(userService.isUnique(any(User.class))).thenReturn(true);
        doNothing().when(userService).addUser(any(UserDTO.class));
        when(userService.getUserByUsername(anyString())).thenReturn(user);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(testUserDTO));
        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.login", is(testUserDTO.getLogin())))
                .andExpect(jsonPath("$.email", is(testUserDTO.getEmail())))
                .andDo(MockMvcResultHandlers.print());
        verify(userService, times(1)).addUser(userDTOCaptor.capture());
        Assert.assertEquals(userDTOCaptor.getValue().getUuid(), user.getUuid());

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddUserWhereUserIsNotUnique() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        UserDTO testUserDTO = new UserDTO();
        testUserDTO.setLogin("testLogin");
        when(userService.isUnique(any(User.class))).thenThrow(IllegalArgumentException.class);
        userController.addUser(testUserDTO);
        verify(userService, atLeastOnce()).isUnique(userCaptor.capture());
        Assert.assertEquals(userCaptor.getValue().getLogin(), testUserDTO.getLogin());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullUser() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        ArgumentCaptor<String> userCaptor = ArgumentCaptor.forClass(String.class);
        User user = usersList.get(0);
        when(userService.getById(anyString())).thenReturn(null);
        userController.deleteUser(user.getUuid());
        verify(userService, atLeastOnce()).getById(userCaptor.capture());
        Assert.assertEquals(userCaptor.getValue(), user.getUuid());
    }

    @Test
    public void testDeleteUser() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        ArgumentCaptor<String> userCaptor = ArgumentCaptor.forClass(String.class);
        User user = usersList.get(0);
        when(userService.getById(anyString())).thenReturn(user);
        doNothing().when(userService).deleteById(anyString());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/user/delete/{id}", user.getUuid());

        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(userService, atLeastOnce()).deleteById(userCaptor.capture());
       Assert.assertEquals(userCaptor.getValue(), user.getUuid());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullUser() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        UserDTO testUserDTO = new UserDTO();
        testUserDTO.setUuid("3232-koko");
        when(userService.getById(anyString())).thenReturn(null);
        userController.updateUser(testUserDTO.getUuid(), testUserDTO);
        verify(userService, times(1)).getById(anyString());
    }

    @Test
    public void testUpdateUser() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        UserDTO testUserDTO = new UserDTO();
        testUserDTO.setUuid("3232-koko");
        testUserDTO.setEmail("test@mail.ua");
        testUserDTO.setLogin("testDTO");
        User user = usersList.get(0);
        when(userService.getById(anyString())).thenReturn(user);
        when(userService.isUnique(any(User.class))).thenReturn(true);
        doNothing().when(userService).update(any(User.class));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/user/update/{id}", testUserDTO.getUuid(), testUserDTO)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(testUserDTO));
        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.login").value(testUserDTO.getLogin()))
                .andExpect(jsonPath("$.email", is(testUserDTO.getEmail())))
                .andDo(MockMvcResultHandlers.print());
        verify(userService, times(1)).update(userCaptor.capture());
        Assert.assertEquals(userCaptor.getValue().getUuid(), user.getUuid());

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNotUniqueUser() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        UserDTO testUserDTO = new UserDTO();
        testUserDTO.setUuid("3232-koko");
        testUserDTO.setEmail("test@mail.ua");
        testUserDTO.setLogin("testDTO");
        User user = usersList.get(0);
        when(userService.getById(anyString())).thenReturn(user);
        when(userService.isUnique(any(User.class))).thenThrow(IllegalArgumentException.class);
        userController.updateUser(testUserDTO.getUuid(), testUserDTO);
        verify(userService, times(1)).isUnique(userCaptor.capture());
        Assert.assertEquals(userCaptor.getValue().getUuid(), testUserDTO.getUuid());
    }

    private List<User> getUsersList() {
        User user0 = new User();
        user0.setUuid("1234-5678-asdf");
        user0.setEmail("mockito@org.ua");
        user0.setLogin("mocker-pocker");
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        user0.setRoles(roles);
        usersList.add(user0);
        return usersList;
    }
}



