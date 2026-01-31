package server.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import server.dto.FlowItemDTO;
import server.dto.FlowStatusDTO;
import server.model.FlowItem;
import server.model.Status;
import server.repository.FlowItemRepository;

import java.time.Instant;

@Service
public class FlowItemService {

    private static final Logger flowitemLogger = LoggerFactory.getLogger(FlowItemService.class);
    private final FlowItemRepository flowItemRepository;

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

    public boolean changeFlowItemStatus(FlowStatusDTO details) {
        try {
            FlowItem item = getFlowItem(details.getId());
            return switch (details.getCurrentStatus()) {
                case NEW -> {
                    item.setStatus(handleStatus(Status.NEW, details.getNextStatus()));
                    flowItemRepository.save(item);
                    yield true;
                }
                case IN_PROGRESS -> {
                    item.setStatus(handleStatus(Status.IN_PROGRESS, details.getNextStatus()));
                    flowItemRepository.save(item);
                    yield true;
                }
                case COMPLETED -> {
                    item.setStatus(handleStatus(Status.COMPLETED, details.getNextStatus()));
                    flowItemRepository.save(item);
                    yield true;
                }
                case CANCELLED -> {
                    item.setStatus(handleStatus(Status.CANCELLED, details.getNextStatus()));
                    flowItemRepository.save(item);
                    yield true;
                }
                default -> false;
            };
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

    private Status handleStatus(Status current, Status next) {
        if (current == Status.IN_PROGRESS && next == Status.COMPLETED) {
            return Status.COMPLETED;
        }
        if (current == Status.IN_PROGRESS && next == Status.CANCELLED) {
            return Status.CANCELLED;
        }
        if (current == Status.NEW && next == Status.IN_PROGRESS) {
            return Status.IN_PROGRESS;
        }
        if (current == Status.NEW && next == Status.CANCELLED) {
            return Status.CANCELLED;
        }
        return null;
    }



}
