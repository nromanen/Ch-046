package ua.cv.tim.controller;


import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.dto.AllianceDTO;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.model.User;
import ua.cv.tim.service.AllianceService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


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
    List<AllianceDTO> allianceDTOS = new ArrayList<>();
    @Captor
    ArgumentCaptor<AllianceDTO> dtoCaptor = ArgumentCaptor.forClass(AllianceDTO.class);

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(allianceController).build();
        allianceDTOS =getAllianceDTOsList();
    }

    @AfterMethod
    public void resetAllMocks(){
        Mockito.reset(allianceService,userService);
    }
    @Test
    public void testListAllAlliances() throws Exception {
       List<AllianceDTO> allianceDTOS2 = new ArrayList<>();
        AllianceDTO allianceDTO1 = new AllianceDTO();
        allianceDTO1.setAllianceUuid("234-344e-de34d");
        allianceDTO1.setLeaderLogin("joe");
        allianceDTO1.setName("valhala");
        allianceDTO1.setLeaderEmail("joe@ukr.net");
        allianceDTOS2.add(allianceDTO1);
        when(allianceService.getAll()).thenReturn(allianceDTOS2);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/admin/allianceDTO")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(allianceDTOS2));

       this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.*.allianceUuid").value(allianceDTO1.getAllianceUuid()))
                .andExpect(jsonPath("$.*.name").value(allianceDTO1.getName()))
                .andDo(MockMvcResultHandlers.print());
        verify(allianceService, times(1)).getAll();
    }
    @Test
    public void testListAllAlliancesWithEmptyList() throws Exception {
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
    @Test
    public void testCreateAlliance() throws Exception {
        String updatedUUid = "3234-3434-3434";
        AllianceDTO allianceDTO = allianceDTOS.get(0);
        when(userService.isUnique(any(User.class))).thenReturn(true);
        when(allianceService.isUniqueAlliance(anyString(), isNull())).thenReturn(true);
        doNothing().when(allianceService).addAlliance(any(AllianceDTO.class));
        Alliance alliance = new Alliance();
        alliance.setUuid(updatedUUid);
        when(allianceService.getByName(anyString())).thenReturn(alliance);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/admin/allianceDTO")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(allianceDTO));
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.allianceUuid", is(updatedUUid)))
                .andExpect(jsonPath("$.name", is(allianceDTO.getName())))
                .andDo(MockMvcResultHandlers.print());
        verify(allianceService, times(1)).addAlliance(dtoCaptor.capture());
        Assert.assertEquals(dtoCaptor.getValue().getAllianceUuid(), updatedUUid);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateAllianceWhereUserNotUnique() throws Exception {
        when(userService.isUnique(any(User.class))).thenThrow(IllegalArgumentException.class);
        AllianceDTO allianceDTO = allianceDTOS.get(0);
        allianceController.createAlliance(allianceDTO);
      verify(userService, atLeastOnce()).isUnique(any(User.class));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateAllianceWhereAllianceNotUnique() throws Exception {
        AllianceDTO allianceDTO = allianceDTOS.get(0);
        when(userService.isUnique(any(User.class))).thenReturn(true);
        when(allianceService.isUniqueAlliance(anyString(), isNull())).thenThrow(IllegalArgumentException.class);
        allianceController.createAlliance(allianceDTO);
        verify(allianceService, atLeastOnce()).isUniqueAlliance(dtoCaptor.capture().getName(), isNull());
       Assert.assertEquals(dtoCaptor.getValue().getAllianceUuid(), allianceDTO.getAllianceUuid());
    }

    @Test
    public void testUpdateAlliance() throws Exception {
        AllianceDTO allianceDTO = allianceDTOS.get(0);
        Alliance alliance = new Alliance();
        when(allianceService.getById(anyString())).thenReturn(alliance);
        when(userService.isUnique(any(User.class))).thenReturn(true);
        when(allianceService.isUniqueAlliance(anyString(), anyString())).thenReturn(true);
        doNothing().when(allianceService).updateAlliance(any(AllianceDTO.class));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/admin/allianceDTO/{id}",allianceDTO.getAllianceUuid())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(allianceDTO));
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.allianceUuid", is(allianceDTO.getAllianceUuid())))
                .andExpect(jsonPath("$.name", is(allianceDTO.getName())))
                .andDo(MockMvcResultHandlers.print());
        verify(allianceService, times(1)).updateAlliance(dtoCaptor.capture());
        Assert.assertEquals(dtoCaptor.getValue().getAllianceUuid(), allianceDTO.getAllianceUuid());
    }

    @Test
    public void testUpdateAllianceWithNotFoundStatus() throws Exception {
        AllianceDTO allianceDTO = allianceDTOS.get(0);
        when(allianceService.getById(anyString())).thenReturn(null);
        allianceController.updateAlliance(allianceDTO.getAllianceUuid(),allianceDTO);
       MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/admin/allianceDTO/{id}",allianceDTO.getAllianceUuid())
               .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content(TestUtil.convertObjectToJsonBytes(allianceDTO));
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateAllianceWhereUserNotUnique() throws Exception {
        Alliance alliance = new Alliance();
        when(allianceService.getById(anyString())).thenReturn(alliance);
        when(userService.isUnique(any(User.class))).thenThrow(IllegalArgumentException.class);
        AllianceDTO allianceDTO = allianceDTOS.get(0);
        allianceController.updateAlliance(allianceDTO.getAllianceUuid(),allianceDTO);
        verify(userService, atLeastOnce()).isUnique(any(User.class));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateAllianceWhereAllianceNotUnique() throws Exception {
        Alliance alliance = new Alliance();
        when(allianceService.getById(anyString())).thenReturn(alliance);
        AllianceDTO allianceDTO = allianceDTOS.get(0);
        when(userService.isUnique(any(User.class))).thenReturn(true);
        when(allianceService.isUniqueAlliance(anyString(), anyString())).thenThrow(IllegalArgumentException.class);
        allianceController.updateAlliance(allianceDTO.getAllianceUuid(),allianceDTO);
        verify(allianceService, atLeastOnce()).isUniqueAlliance(dtoCaptor.capture().getName(),anyString());
        Assert.assertEquals(dtoCaptor.getValue().getAllianceUuid(), allianceDTO.getAllianceUuid());
        Assert.assertEquals(dtoCaptor.getValue().getName(), allianceDTO.getName());
    }
    @Test
    public void testDeleteAllianceWithNullId() throws Exception {
        AllianceDTO allianceDTO = allianceDTOS.get(0);
        when(allianceService.getById(anyString())).thenReturn(null);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/admin/allianceDTO/{id}",allianceDTO.getAllianceUuid());

        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testDeleteAlliance() throws Exception {
        Alliance alliance = new Alliance();
        alliance.setUuid("2323-2323-2323");
        when(allianceService.getById(anyString())).thenReturn(alliance);
        doNothing().when(allianceService).deleteAlliance(anyString());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/admin/allianceDTO/{id}",alliance.getUuid());

        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
        verify(allianceService, times(1)).deleteAlliance(anyString());
    }

    private List<AllianceDTO>  getAllianceDTOsList(){
        AllianceDTO allianceDTO1 = new AllianceDTO();
        allianceDTO1.setAllianceUuid("234-344e-de34d");
        allianceDTO1.setLeaderLogin("joe");
        allianceDTO1.setName("valhala");
        allianceDTO1.setLeaderEmail("joe@ukr.net");
        allianceDTOS.add(allianceDTO1);
        return allianceDTOS;
    }

}
