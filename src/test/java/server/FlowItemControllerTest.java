package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import server.controller.FlowItemController;
import server.dto.FlowItemDTO;
import server.dto.FlowStatusDTO;
import server.model.FlowItem;
import server.model.FlowTeam;
import server.service.FlowItemService;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .andExpect(status().is(201));
    }

    @Test
    public void changeFlowItemStatus() throws Exception {
        FlowStatusDTO info = new FlowStatusDTO();
        when(flowitemService.changeFlowItemStatus(any()))
                .thenReturn(true);

        mockMvc.perform(put("/flowitem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(info)))
                .andExpect(status().is(200));
    }


    @Test
    public void deleteFlowItemById() throws Exception {
        when(flowitemService.deleteFlowItem(1L))
                .thenReturn(true);

        mockMvc.perform(delete("/flowitem/{id}", 1L))
                .andExpect(status().is(200));
    }

    @Test
    public void getFlowItemById() throws Exception {
        FlowItem item = new FlowItem();
        item.setId(1L);

        when(flowitemService.getFlowItem(1L))
                .thenReturn(item);

        mockMvc.perform(get("/flowitem/{id}", 1L))
                .andExpect(status().is(200));
    }


}
