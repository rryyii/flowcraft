package server.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.FlowItemDTO;
import server.dto.FlowPriorityDTO;
import server.dto.FlowStatusDTO;
import server.model.FlowItem;
import server.model.Status;
import server.service.FlowItemService;

/**
 * Controller responsible for mapping endpoints for FlowItems.
 *
 * Handles basic validation and returns the corresponding ResponseEntity
 * with status code and return body.
 */
@RestController
@RequestMapping("flowitem")
public class FlowItemController {

    private final FlowItemService flowItemService;

    public FlowItemController(FlowItemService flowItemService) {
        this.flowItemService = flowItemService;
    }

    @PostMapping
    public ResponseEntity<String> createFlowItem(@Valid @RequestBody FlowItemDTO details) {
        if (flowItemService.createFlowItem(details)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<String> changeFlowItemStatus(@Valid @RequestBody FlowStatusDTO details) {
        if (flowItemService.changeFlowItemStatus(details)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/priority/{userId}")
    public ResponseEntity<String> changeFlowItemPriority(@Valid @RequestBody FlowPriorityDTO details,
                                                         @PathVariable Long userId) {
        if (flowItemService.changeFlowPriority(details, userId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlowItem> getFlowItem(@Positive @PathVariable Long id) {
        FlowItem item = flowItemService.getFlowItem(id);
        if (item != null) {
            return ResponseEntity.status(HttpStatus.OK).body(item);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> deleteFlowItem(@Positive @PathVariable Long id,
                                                 @Positive @PathVariable Long userId) {
        if (flowItemService.deleteFlowItem(id, userId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }



}
