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
import server.controller.FlowUserController;
import server.dto.FlowUserDTO;
import server.dto.FlowUserUpdateDTO;
import server.service.FlowUserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests the return status codes of all FLowUser controller endpoints.
 */
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
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    void createFlowuserError() throws Exception {
        mockMvc.perform(post("/flowuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void updateFlowuser() throws Exception {
        FlowUserUpdateDTO details = new FlowUserUpdateDTO();
        details.setUsername("new username");

        when(flowuserService.updateFlowuser(eq(1L), any()))
                .thenReturn(true);

        mockMvc.perform(put("/flowuser/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(details)))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    void deleteFlowuser() throws Exception {
        when(flowuserService.deleteFlowuser(1L))
                .thenReturn(true);

        mockMvc.perform(delete("/flowuser/{id}", 1L))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    void deleteFlowuserError() throws Exception {
        when (flowuserService.deleteFlowuser(1L))
                .thenReturn(true);

        mockMvc.perform(delete("/flowuser/{id}", 2L))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void getFlowusers() throws Exception {
        mockMvc.perform(get("/flowuser"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }


}
