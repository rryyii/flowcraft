package server.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> deleteFlowTeam(@Positive @PathVariable Long id) {
        if (flowTeamService.deleteFlowTeam(id)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlowTeam> getFlowTeam(@Positive @PathVariable Long id) {
        FlowTeam team = flowTeamService.getFlowTeam(id);
        if (team != null) {
            return ResponseEntity.status(200).body(team);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<FlowItem>> getFlowTeamItems(@RequestParam boolean active, @Positive @PathVariable Long id) {
        if (active) {
            List<FlowItem> items = flowTeamService.getFlowTeamItems(id);
            return ResponseEntity.status(200).body(items);
        }
        return ResponseEntity.status(404).build();
    }

}
