package server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.FlowItemDTO;
import server.dto.FlowStatusDTO;
import server.model.FlowItem;
import server.model.Status;
import server.service.FlowItemService;

@RestController
@RequestMapping("flowitem")
public class FlowItemController {

    private final FlowItemService flowItemService;

    public FlowItemController(FlowItemService flowItemService) {
        this.flowItemService = flowItemService;
    }

    @PostMapping("/createFlowItem")
    public ResponseEntity<String> createFlowItem(@RequestBody FlowItemDTO details) {
        if (flowItemService.createFlowItem(details)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/changeFlowItemStatus")
    public ResponseEntity<String> changeFlowItemStatus(@RequestBody FlowStatusDTO details) {
        if (flowItemService.changeFlowItemStatus(details)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getFlowItem/{id}")
    public ResponseEntity<FlowItem> getFlowItem(@PathVariable Long id) {
        if (id > 0) {
            FlowItem item = flowItemService.getFlowItem(id);
            if (item != null) {
                return ResponseEntity.status(200).body(item);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/deleteFlowItem/{id}")
    public ResponseEntity<String> deleteFlowItem(@PathVariable Long id) {
        if (id > 0) {
            if (flowItemService.deleteFlowItem(id)) {
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }



}
