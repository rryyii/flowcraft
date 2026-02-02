package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

        when(flowteamService.createFlowTeam(any()))
                .thenReturn(true);

        mockMvc.perform(post("/flowteam")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(details)))
                .andExpect(status().is(201));
    }

    @Test
    public void deleteFlowTeamById() throws Exception {
        when(flowteamService.deleteFlowTeam(1L))
                .thenReturn(true);

        mockMvc.perform(delete("/flowteam/{id}", 1L))
                .andExpect(status().is(200));
    }

    @Test
    public void getFlowTeamById() throws Exception {
        FlowTeam team = new FlowTeam();
        team.setId(1L);

        when(flowteamService.getFlowTeam(1L))
                .thenReturn(team);

        mockMvc.perform(get("/flowteam/{id}/{activate}", 1L, true))
                .andExpect(status().is(200));
    }

}
