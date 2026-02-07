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

/**
 * Controller responsible for mapping endpoints for FlowTeams.
 *
 * Handles basic validation and returns the corresponding ResponseEntity
 * with status code and return body.
 */
@RestController
@RequestMapping("/flowteam")
public class FlowTeamController {

    private final FlowTeamService flowTeamService;

    public FlowTeamController(FlowTeamService flowTeamService) {
        this.flowTeamService = flowTeamService;
    }

    /**
     * Creates a new FlowTeam according to a valid request.
     * @param details DTO containing relevant properties of a new FlowTeam
     * @return ResponseEntity with relevant status code (200 or 400)
     */
    @PostMapping
    public ResponseEntity<String> createFlowTeam(@Valid @RequestBody FlowTeamCreateDTO details) {
        if (flowTeamService.createFlowTeam(details)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Deletes a FlowTeam given an existing FlowTeam's id.
     * @param id Long value for an existing id.
     * @param userId Long value for the requesting user's id.
     * @return ResponseEntity with relevant status code (200 or 400)
     */
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> deleteFlowTeam(@Positive @PathVariable Long id,
                                                 @Positive @PathVariable Long userId) {
        if (flowTeamService.deleteFlowTeam(id, userId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Removes an existing user from a FlowTeam given valid ids.
     * @param id Long value for the id of the FlowTeam
     * @param userId Long value for the requesting user's id.
     * @return ResponseEntity with relevant status code (200 or 400)
     */
    @DeleteMapping("/member/{id}/{userId}")
    public ResponseEntity<String> removeFlowUser(@Positive @PathVariable Long id,
                                                 @Positive @PathVariable Long userId) {
        if (flowTeamService.removeFlowUser(id, userId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Retrieves a FlowTeam given an existing and valid FlowTeam id.
     * @param id Long value for the id of the FlowTeam
     * @return ResponseEntity contaning the relevant status code (200 or 404) and the FlowTeam entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<FlowTeam> getFlowTeam(@Positive @PathVariable Long id) {
        FlowTeam team = flowTeamService.getFlowTeam(id);
        if (team != null) {
            return ResponseEntity.status(HttpStatus.OK).body(team);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Retrieves all existing and active FlowTeam's items.
     * @param active Boolean for considering if the endpoint is active
     * @param id Long value for the  id of the FlowTeam
     * @param userId Long value for the requesting user's id.
     * @return ResponseEntity contaning the relevant status code (200 or 404) and a Collection of FLowItems
     */
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
