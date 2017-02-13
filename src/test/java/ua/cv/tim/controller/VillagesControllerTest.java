package ua.cv.tim.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.configuration.HibernateConfiguration;
import ua.cv.tim.configuration.WebConfiguration;
import ua.cv.tim.configuration.WebSecurityConfiguration;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.UserService;
import ua.cv.tim.service.VillageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by okunetc on 08.02.2017.
 */

@ContextConfiguration(classes = {WebConfiguration.class, WebSecurityConfiguration.class, HibernateConfiguration.class})
@WebAppConfiguration
public class VillagesControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    VillageService villageService;

    @Mock
    UserService userService;

    @Autowired
    private WebApplicationContext context;


    @Autowired
    private FilterChainProxy springSecurityFilterChain;


    @InjectMocks
    VillagesController villagesController;

    private MockMvc mockMvc;
    @Spy
    List<Village> alliances = new ArrayList<>();

    @BeforeClass
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .addFilters(this.springSecurityFilterChain)
                .build();

    }

    @AfterMethod
    public void resetAllMocks() {
        Mockito.reset(villageService);
    }


    @Test
    public void testAddVillage() throws Exception {
        Village village = new Village();
        village.setName("Villkljkj");
        village.setPlayer(new Player());
        village.setxCoord((short) 58);
        village.setyCoord((short) 32);
        village.setArmies(new ArrayList<>());
        village.setIsCapital(true);
        village.setPopulation((short) 500);
        village.setWall((byte) 20);
        village.setUuid("0");


        when(userService.getUserByUsername(anyString())).thenReturn(new ua.cv.tim.model.User());

        doNothing().when(villageService).add(village);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/villagkjje")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(village))
                        .with(user("trinity").password("222").roles("ADMIN")).with(csrf());
        this.mockMvc.perform(builder)
                .andExpect(authenticated())
                .andExpect(MockMvcResultMatchers.status().isCreated());
        verify(villageService, times(1)).add(village);
//        verify(villageService,times(1))
    }


    @Test
    public void testUpdateVillage() throws Exception {
        Village village = new Village();
        village.setName("Villkljkj");
        village.setPlayer(new Player());
        village.setxCoord((short) 58);
        village.setyCoord((short) 32);
        village.setArmies(new ArrayList<>());
        village.setIsCapital(true);
//        village.setPopulation((short) 500);
        village.setWall((byte) 20);
        village.setUuid("0");
        when(villageService.getById("0")).thenReturn(village);
        when(villageService.isUnique(village)).thenReturn(true);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/village/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(village))
                        .with(user("trinity").password("222").roles("USER")).with(csrf());
//        this.mockMvc.perform(builder)
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(authenticated())
//                .andDo(MockMvcResultHandlers.print());
        mockMvc
                .perform(get("/village"))
                .andExpect(status().isOk());
//        verify(villageService, times(0)).update(village);
    }

    private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

}