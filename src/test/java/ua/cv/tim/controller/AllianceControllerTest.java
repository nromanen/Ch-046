package ua.cv.tim.controller;

import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.service.AllianceService;
import ua.cv.tim.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by mmaksymtc on 01.02.2017.
 */
public class AllianceControllerTest {
    @Mock
    AllianceService allianceService;
    @Mock
    UserService userService;
    @InjectMocks
    AllianceController allianceController;
    private MockMvc mockMvc;
    @Spy
    List<AllianceDTO> alliances = new ArrayList<>();
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(allianceController).build();
        alliances =getAllianceDTOsList();
    }

    @AfterMethod
    public void resetAllMocks(){
        Mockito.reset(allianceService,userService);
    }
    @Test
    public void listAllAlliancesTest() throws Exception {
        when(allianceService.getAll()).thenReturn(alliances);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/admin/allianceDTO")
                        .contentType(MediaType.APPLICATION_JSON);

       this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(content().json("[{'allianceUuid':'234-344e-de34d','leaderUuid':'null','name':'valhala','leaderLogin':'joe','leaderEmail':'joe@ukr.net'}]"))
                .andDo(MockMvcResultHandlers.print());

        verify(allianceService, times(1)).getAll();
    }
    @Test
    public void listAllAlliancesWithEmptyEntityTest() throws Exception {
        List<AllianceDTO> alliances = new ArrayList<>();
        when(allianceService.getAll()).thenReturn(alliances);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/admin/allianceDTO")
                        .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
        verify(allianceService, times(1)).getAll();
    }
//    @Test
//    public void createAllianceTest() throws Exception {
//        when(userService.isUnique(any(User.class))).thenReturn(false);
//        doNothing().when(allianceService).addAlliance(any(AllianceDTO.class));
//        when(allianceService.getByName(anyString()).getUuid()).thenReturn("777-ferf-rgftr-eeed");
//        MockHttpServletRequestBuilder builder =
//                MockMvcRequestBuilders.post("/admin/allianceDTO")
//                        .contentType(MediaType.APPLICATION_JSON);
//        this.mockMvc.perform(builder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        ArgumentCaptor<AllianceDTO> dtoCaptor = ArgumentCaptor.forClass(AllianceDTO.class);
//        verify(allianceService, times(1)).addAlliance(dtoCaptor.capture());
//    }

    private List<AllianceDTO>  getAllianceDTOsList(){
        AllianceDTO allianceDTO1 = new AllianceDTO();
        allianceDTO1.setAllianceUuid("234-344e-de34d");
        allianceDTO1.setLeaderLogin("joe");
        allianceDTO1.setName("valhala");
        allianceDTO1.setLeaderEmail("joe@ukr.net");
        alliances.add(allianceDTO1);
        return alliances;

    }
}
