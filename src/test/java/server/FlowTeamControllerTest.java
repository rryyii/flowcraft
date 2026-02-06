package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import server.controller.FlowTeamController;
import server.dto.FlowTeamCreateDTO;
import server.model.FlowTeam;
import server.service.FlowTeamService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlowTeamController.class)
@AutoConfigureMockMvc
public class FlowTeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FlowTeamService flowteamService;

    @Test
    public void createFlowTeam() throws Exception {
        FlowTeamCreateDTO details = new FlowTeamCreateDTO();
        details.setTeamName("Test Team");
        details.setId(1L);

        when(flowteamService.createFlowTeam(any()))
                .thenReturn(true);

        mockMvc.perform(post("/flowteam")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(details)))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void deleteFlowTeamById() throws Exception {
        when(flowteamService.deleteFlowTeam(1L, 1L))
                .thenReturn(true);

        mockMvc.perform(delete("/flowteam/{id}/{userId}", 1L, 1L))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void removeFlowTeamUser() throws Exception {
        when(flowteamService.removeFlowUser(1L, 1L))
                .thenReturn(true);

        mockMvc.perform(delete("/flowteam/member/{id}/{userId}", 1L, 1L))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void getFlowTeamById() throws Exception {
        FlowTeam team = new FlowTeam();
        team.setId(1L);

        when(flowteamService.getFlowTeam(1L))
                .thenReturn(team);

        mockMvc.perform(get("/flowteam/{id}", 1L, true))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void getFlowTeamItems() throws Exception {
        mockMvc.perform(get("/flowteam/{id}/{userId}/{active}", 1L, 1L, true))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

}
