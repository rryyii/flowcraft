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
import server.service.FlowTeamService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        mockMvc.perform(post("/flowteam/createFlowTeam")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(details)))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteFlowTeamById() throws Exception {

    }

    @Test
    public void getFlowTeamById() throws Exception {

    }

}
