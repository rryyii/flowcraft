package server.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import server.dto.FlowItemDTO;
import server.dto.FlowPriorityDTO;
import server.dto.FlowStatusDTO;
import server.mapper.FlowItemMapper;
import server.model.FlowItem;
import server.model.Status;
import server.repository.FlowItemRepository;


@Service
public class FlowItemService {

    private static final Logger flowitemLogger = LoggerFactory.getLogger(FlowItemService.class);
    private final FlowItemRepository flowItemRepository;
    private final FlowItemMapper flowItemMapper;

    public FlowItemService(FlowItemRepository flowItemRepository, FlowItemMapper flowItemMapper) {
        this.flowItemRepository = flowItemRepository;
        this.flowItemMapper = flowItemMapper;
    }

    public boolean createFlowItem(FlowItemDTO details) {
        try {
            FlowItem newItem = flowItemMapper.toEntity(details);
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
            return switch (details.getStatus()) {
                case NEW -> {
                    item.setStatus(handleStatus(Status.NEW));
                    flowItemRepository.save(item);
                    yield true;
                }
                case IN_PROGRESS -> {
                    item.setStatus(handleStatus(Status.IN_PROGRESS));
                    flowItemRepository.save(item);
                    yield true;
                }
                case CANCELLED -> {
                    item.setStatus(Status.CANCELLED);
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

    public boolean changeFlowPriority(FlowPriorityDTO details) {
        try {
            FlowItem item = getFlowItem(details.getId());
            item.setPriority(details.getPriority());
            flowItemRepository.save(item);
            return true;
        } catch (Exception e) {
            flowitemLogger.error("Failed to change FlowItem's priority");
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

    private Status handleStatus(Status current) {
        if (current == Status.IN_PROGRESS) {
            return Status.COMPLETED;
        }
        if (current == Status.NEW) {
            return Status.IN_PROGRESS;
        }
        return null;
    }


}
