package server.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.FlowuserDTO;
import server.model.Flowuser;
import server.service.FlowuserService;

import java.util.List;

@RestController
@RequestMapping("flowuser")
public class FlowuserController {

    private final FlowuserService flowuserService;

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

    @GetMapping("/flowusers")
    public ResponseEntity<List<Flowuser>> getFlowusers() {
        List<Flowuser> users = flowuserService.getFlowusers();
        if (users != null) {
            return ResponseEntity.status(200).body(users);
        }
        return ResponseEntity.status(400).build();
    }

}
