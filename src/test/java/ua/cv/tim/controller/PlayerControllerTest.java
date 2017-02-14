package ua.cv.tim.controller;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.cv.tim.model.*;
import ua.cv.tim.service.PlayerService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by mmaksymtc on 06.02.2017.
 */
public class PlayerControllerTest {

    @Mock
    UserService userService;
    @Mock
    PlayerService playerService;
    @InjectMocks
    PlayerController playerController;
    private MockMvc mockMvc;
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    @Test
    public void testGetUsersVillages() throws Exception {
        ArgumentCaptor<String> playerCaptor = ArgumentCaptor.forClass(String.class);
        Player player = new Player();
        player.setUuid("333-222");
        List<Village> villages = new ArrayList<>();
        Village village0 = new Village();
        village0.setName("Che");
        village0.setIsCapital(true);
        villages.add(village0);
        player.setVillages(villages);
        when(playerService.getByIdWithVillages(anyString())).thenReturn(player);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/player/{id}/village",player.getUuid())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(villages));
        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.*.name").value(villages.get(0).getName()))
                .andExpect(jsonPath("$.*.isCapital").value(villages.get(0).getIsCapital()))
                .andDo(MockMvcResultHandlers.print());
        verify(playerService, times(1)).getByIdWithVillages(playerCaptor.capture());
        assertEquals(playerCaptor.getValue(),player.getUuid());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullPlayer() throws Exception {
        ArgumentCaptor<String> playerCaptor = ArgumentCaptor.forClass(String.class);
        Player player = getPlayer();
        when(playerService.getById(anyString())).thenReturn(null);
        playerController.deletePlayer(player.getUuid());
        verify(playerService, times(1)).getById(playerCaptor.capture());
        assertEquals(playerCaptor.getValue(),player.getUuid());
    }

//    @Test
//    public void testDeletePlayer() throws Exception {
//        Player player = getPlayer();
//        when(playerService.getById(anyString())).thenReturn(player);
//        doNothing().when(playerService).delete(any(Player.class));
//        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete( "/player/{id}",player.getUuid())
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(player));
//        this.mockMvc.perform(builder)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.uuid").value(player.getUuid()))
//                .andDo(MockMvcResultHandlers.print());
//        verify(playerService, times(1)).delete(any(Player.class));
//    }
    @Test
    public void testAddPlayer() throws Exception {
    doNothing().when(playerService).add(any(Player.class));

    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullPlayer() throws Exception {
        ArgumentCaptor<String> playerCaptor = ArgumentCaptor.forClass(String.class);
        Player player = getPlayer();
        when(playerService.getById(anyString())).thenReturn(null);
        playerController.updatePlayer(player.getUuid(), player);
        verify(playerService, times(1)).getById(playerCaptor.capture());
        assertEquals(playerCaptor.getValue(),player.getUuid());

    }

    private Player getPlayer() {
        Player player = new Player();
        User user = new User();
        player.setUuid("333-222");
        player.setRace(Race.GAULS);
        List<Village> villages = new ArrayList<>();
        Village village0 = new Village();
        village0.setName("Che");
        village0.setIsCapital(true);
        villages.add(village0);
        player.setVillages(villages);
        player.setUser(user);
        Alliance alliance = new Alliance();
        alliance.setName("ALALALA");
        player.setAlliance(alliance);
        return player;
    }
}