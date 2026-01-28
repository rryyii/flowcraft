package server.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import server.dto.FlowItemDTO;
import server.model.FlowItem;
import server.model.Status;
import server.repository.FlowItemRepository;

import java.time.Instant;

@Service
public class FlowItemService {

    private static final Logger flowitemLogger = LoggerFactory.getLogger(FlowItemService.class);
    private FlowItemRepository flowItemRepository;

    public FlowItemService(FlowItemRepository flowItemRepository) {
        this.flowItemRepository = flowItemRepository;
    }

    public boolean createFlowItem(FlowItemDTO details) {
        try {
            FlowItem newItem = new FlowItem();
            newItem.setCreatedTimestamp(Instant.now());
            newItem.setName(details.getName());
            newItem.setDeadline(details.getDeadline());
            newItem.setTeam(details.getTeam());
            newItem.setDescription(details.getDescription());
            newItem.setStatus(Status.NEW);
            flowItemRepository.save(newItem);
            return true;
        } catch (Exception e) {
            flowitemLogger.error("Failed to create a new FlowItem");
            return false;
        }
    }

    public boolean deleteFlowItem(Long id) {
        try {
            flowItemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            flowitemLogger.error("Failed to delete FlowItem at given Id");
            return false;
        }
    }

    public boolean changeFlowItemStatus(Status details) {
        try {
            return true;
        } catch (Exception e) {
            flowitemLogger.error("Failed to change FlowItem status");
            return false;
        }
    }

    public FlowItem getFlowItem(Long id) {
        try {
            return flowItemRepository.getReferenceById(id);
        } catch (Exception e) {
            flowitemLogger.error("Failed to get FlowItem at given Id");
            return null;
        }
    }



}
