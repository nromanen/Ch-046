package ua.cv.tim.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
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
import ua.cv.tim.configuration.WebConfiguration;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.UserService;
import ua.cv.tim.service.VillageService;

import javax.servlet.Filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by okunetc on 08.02.2017.
 */

//@ContextConfiguration
//@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
//        DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        WithSecurityContextTestExecutionListener.class})
//@WithUserDetails
@ContextConfiguration(classes = {WebConfiguration.class})
@WebAppConfiguration
public class VillagesControllerTest extends AbstractTestNGSpringContextTests {
@Mock
    VillageService villageService;

     @Mock
     UserService userService;

    @Autowired
    private WebApplicationContext context;

    @InjectMocks
    VillagesController villagesController;

    private MockMvc mockMvc;
    @Spy
    List<Village> alliances = new ArrayList<>();
    @BeforeClass
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

    }
    @AfterMethod
    public void resetAllMocks(){
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
        doNothing().when(villageService).add(any(Village.class));
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/village/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(village));
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        ArgumentCaptor<Village> villageArgumentCaptor = ArgumentCaptor.forClass(Village.class);
//        verify(villageService, VerificationModeFactory.times(1)).add(village);
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
        village.setPopulation((short) 500);
        village.setWall((byte) 20);
        village.setUuid("0");
when(villageService.getById("0")).thenReturn(village);
when(villageService.isUnique(village)).thenReturn(false);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/village/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(village));
        this.mockMvc.perform(builder)
//                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
//        verify(villageService, times(0)).update(village);
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

}