package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import server.controller.FlowuserController;
import server.dto.FlowuserDTO;
import server.service.FlowuserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlowuserController.class)
@AutoConfigureMockMvc
public class FlowuserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FlowuserService flowuserService;

    @Test
    void createFlowuser() throws Exception {
        FlowuserDTO info = new FlowuserDTO();
        info.setUsername("Waruia");
        info.setCurrentClass("Warrior");
        info.setCurrentRole("DPS");
        mockMvc.perform(post("/flowuser/createFlowuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(info)))
                .andExpect(status().is(201));
    }

    @Test
    void createFlowuserError() throws Exception {
        mockMvc.perform(post("/flowuser/createFlowuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is(400));
    }

    @Test
    void deleteFlowuser() throws Exception {

    }

    @Test
    void deleteFlowuserError() throws Exception {

    }

    @Test
    void getFlowusers() throws Exception {
        mockMvc.perform(get("/flowuser/flowusers"))
                .andExpect(status().is(200));
    }

}
