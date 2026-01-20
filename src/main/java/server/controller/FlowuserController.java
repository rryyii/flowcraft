package server.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.FlowuserDTO;
import server.service.FlowuserService;

@RestController
@RequestMapping("flowuser")
class FlowuserController {

    private FlowuserService flowuserService;

    public FlowuserController(FlowuserService flowuserService) {
        this.flowuserService = flowuserService;
    }

    @PostMapping("/createFlowuser")
    public ResponseEntity<String> createFlowuser(@RequestBody FlowuserDTO newUser) {
        if (newUser != null) {
            if (flowuserService.createFlowuser(newUser)) {
                return ResponseEntity.status(201).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
