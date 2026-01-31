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
import server.service.FlowItemService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        when(flowitemService.createFlowItem(any()))
                .thenReturn(true);

        mockMvc.perform(post("/flowitem/createFlowItem")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(details)))
                .andExpect(status().is(201));
    }

    @Test
    public void changeFlowItemStatus() throws Exception {

    }


    @Test
    public void deleteFlowItemById() throws Exception {

    }

    @Test
    public void getFlowItemById() throws Exception {
    }


}
