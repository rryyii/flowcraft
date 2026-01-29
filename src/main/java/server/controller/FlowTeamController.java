package server.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.FlowTeamCreateDTO;
import server.model.FlowTeam;
import server.service.FlowTeamService;

@RestController
@RequestMapping("flowteam")
public class FlowTeamController {

    private final FlowTeamService flowTeamService;

    public FlowTeamController(FlowTeamService flowTeamService) {
        this.flowTeamService = flowTeamService;
    }

    @PostMapping("/createFlowTeam")
    public ResponseEntity<String> createFlowTeam(@RequestBody FlowTeamCreateDTO details) {
        if (flowTeamService.createFlowTeam(details)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/deleteFlowTeam/{id}")
    public ResponseEntity<String> deleteFlowTeam(@PathVariable Long id) {
        if (id > 0) {
            if (flowTeamService.deleteFlowTeam(id)) {
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getFlowTeam/{id}")
    public ResponseEntity<FlowTeam> getFlowTeam(@PathVariable Long id) {
        if (id > 0) {
            FlowTeam team = flowTeamService.getFlowTeam(id);
            if (team != null) {
                return ResponseEntity.status(200).body(team);
            }
        }
        return ResponseEntity.badRequest().build();
    }

}
