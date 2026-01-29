package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import server.controller.FlowItemController;
import server.service.FlowItemService;

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

    }

    @Test
    public void changeFlowItemStatus() throws Exception {

    }

    @Test
    public void getFlowItemById() throws Exception {

    }

    @Test
    public void deleteFlowItemById() throws Exception {

    }


}
