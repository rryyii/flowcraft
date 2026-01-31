package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import server.controller.FlowUserController;
import server.dto.FlowUserDTO;
import server.service.FlowUserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlowUserController.class)
@AutoConfigureMockMvc
public class FlowUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FlowUserService flowuserService;

    @Test
    void createFlowuser() throws Exception {
        FlowUserDTO info = new FlowUserDTO();
        info.setUsername("Waruia");
        info.setMainClass("Warrior");
        info.setMainRole("DPS");

        when(flowuserService.createFlowuser(any()))
                .thenReturn(true);

        mockMvc.perform(post("/flowuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(info)))
                .andExpect(status().is(201));
    }

    @Test
    void createFlowuserError() throws Exception {
        mockMvc.perform(post("/flowuser")
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
        mockMvc.perform(get("/flowuser"))
                .andExpect(status().is(200));
    }

}
