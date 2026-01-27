package server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.FlowItemService;

@RestController
@RequestMapping("flowitem")
public class FlowItemController {

    private FlowItemService flowItemService;

    public FlowItemController(FlowItemService flowItemService) {
        this.flowItemService = flowItemService;
    }

    
}
