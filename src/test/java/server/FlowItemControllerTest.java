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
import server.controller.FlowItemController;
import server.dto.FlowItemDTO;
import server.dto.FlowPriorityDTO;
import server.dto.FlowStatusDTO;
import server.model.FlowItem;
import server.model.FlowTeam;
import server.model.Priority;
import server.model.Status;
import server.service.FlowItemService;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests the return status codes of all FLowItem controller endpoints.
 */
@WebMvcTest(FlowItemController.class)
@AutoConfigureMockMvc
public class FlowItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FlowItemService flowitemService;

    @Test
    public void createFlowItem() throws Exception {
        FlowItemDTO details = new FlowItemDTO();
        details.setDeadline(LocalDateTime.now());
        details.setTeam(new FlowTeam());
        details.setName("test");

        when(flowitemService.createFlowItem(any()))
                .thenReturn(true);

        mockMvc.perform(post("/flowitem")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(details)))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void changeFlowItemStatus() throws Exception {
        FlowStatusDTO info = new FlowStatusDTO();
        info.setId(1L);
        info.setStatus(Status.COMPLETED);
        when(flowitemService.changeFlowItemStatus(any()))
                .thenReturn(true);

        mockMvc.perform(put("/flowitem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(info)))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void changeFlowItemPriority() throws Exception {
        FlowPriorityDTO info = new FlowPriorityDTO();
        info.setId(1L);
        info.setPriority(Priority.CRITICAL);
        when(flowitemService.changeFlowPriority(any(), eq(1L)))
                .thenReturn(true);

        mockMvc.perform(put("/flowitem/priority/{userId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(info)))
                .andExpect(status().is(HttpStatus.OK.value()));
    }


    @Test
    public void deleteFlowItemById() throws Exception {
        when(flowitemService.deleteFlowItem(1L, 1L))
                .thenReturn(true);

        mockMvc.perform(delete("/flowitem/{id}/{userId}", 1L, 1L))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void getFlowItemById() throws Exception {
        FlowItem item = new FlowItem();
        item.setId(1L);

        when(flowitemService.getFlowItem(1L))
                .thenReturn(item);

        mockMvc.perform(get("/flowitem/{id}", 1L))
                .andExpect(status().is(HttpStatus.OK.value()));
    }


}
