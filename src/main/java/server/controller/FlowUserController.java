package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.FlowUserDTO;
import server.model.FlowUser;
import server.service.FlowUserService;

import java.util.List;

@RestController
@RequestMapping("/flowuser")
public class FlowUserController {

    private final FlowUserService flowuserService;
    private static final Logger logger = LoggerFactory.getLogger(FlowUserController.class);

    public FlowUserController(FlowUserService flowuserService) {
        this.flowuserService = flowuserService;
    }


    @PostMapping("/createFlowuser")
    public ResponseEntity<String> createFlowuser(@RequestBody FlowUserDTO newUser) {
        if (flowuserService.createFlowuser(newUser)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.badRequest().body("Failed to create FlowUser");
    }

    @DeleteMapping("/deleteFlowuser/{id}")
    public ResponseEntity<String> deleteFlowuser(@PathVariable Long id) {
        if (id > 0) {
            if (flowuserService.deleteFlowuser(id)) {
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/updateFlowuser/{id}")
    public ResponseEntity<String> updateFlowuser(@PathVariable Long id, @RequestParam String details) {
        if (id > 0 && (details != null)) {
            if (flowuserService.updateFlowuser(id, details)) {
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/flowusers")
    public ResponseEntity<List<FlowUser>> getFlowusers() {
        List<FlowUser> users = flowuserService.getFlowusers();
        if (users != null) {
            return ResponseEntity.status(200).body(users);
        }
        return ResponseEntity.badRequest().build();
    }

}
