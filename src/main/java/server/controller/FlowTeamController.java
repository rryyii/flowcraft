package server.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.FlowTeamCreateDTO;
import server.model.FlowItem;
import server.model.FlowTeam;
import server.service.FlowTeamService;

import java.util.List;

@RestController
@RequestMapping("/flowteam")
public class FlowTeamController {

    private final FlowTeamService flowTeamService;

    public FlowTeamController(FlowTeamService flowTeamService) {
        this.flowTeamService = flowTeamService;
    }

    @PostMapping
    public ResponseEntity<String> createFlowTeam(@Valid @RequestBody FlowTeamCreateDTO details) {
        if (flowTeamService.createFlowTeam(details)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> deleteFlowTeam(@Positive @PathVariable Long id,
                                                 @Positive @PathVariable Long userId) {
        if (flowTeamService.deleteFlowTeam(id, userId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/member/{id}/{userId}")
    public ResponseEntity<String> removeFlowUser(@Positive @PathVariable Long id,
                                                 @Positive @PathVariable Long userId) {
        if (flowTeamService.removeFlowUser(id, userId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlowTeam> getFlowTeam(@Positive @PathVariable Long id) {
        FlowTeam team = flowTeamService.getFlowTeam(id);
        if (team != null) {
            return ResponseEntity.status(HttpStatus.OK).body(team);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}/{userId}/{active}")
    public ResponseEntity<List<FlowItem>> getFlowTeamItems(@PathVariable boolean active,
                                                           @Positive @PathVariable Long id,
                                                           @PathVariable Long userId) {
        if (active) {
            List<FlowItem> items = flowTeamService.getFlowTeamItems(id, userId);
            return ResponseEntity.status(HttpStatus.OK).body(items);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
