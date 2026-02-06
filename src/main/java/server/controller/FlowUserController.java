package server.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.FlowUserDTO;
import server.dto.FlowUserUpdateDTO;
import server.model.FlowUser;
import server.service.FlowUserService;

import java.util.List;

/**
 * Controller responsible for mapping endpoints for FlowUsers.
 *
 * Handles basic validation and returns the corresponding ResponseEntity
 * with status code and return body.
 */
@RestController
@RequestMapping("/flowuser")
public class FlowUserController {

    private final FlowUserService flowuserService;
    private static final Logger logger = LoggerFactory.getLogger(FlowUserController.class);

    public FlowUserController(FlowUserService flowuserService) {
        this.flowuserService = flowuserService;
    }


    @PostMapping
    public ResponseEntity<String> createFlowuser(@Valid @RequestBody FlowUserDTO newUser) {
        if (flowuserService.createFlowuser(newUser)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().body("Failed to create FlowUser");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlowuser(@Positive @PathVariable Long id) {
        if (flowuserService.deleteFlowuser(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFlowuser(@Positive @PathVariable Long id,
                                                 @NotNull @RequestBody FlowUserUpdateDTO details) {
        if (flowuserService.updateFlowuser(id, details)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<FlowUser>> getFlowusers() {
        List<FlowUser> users = flowuserService.getFlowusers();
        if (users != null) {
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
