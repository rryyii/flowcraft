package server.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import server.dto.FlowItemDTO;
import server.dto.FlowItemUpdateDTO;
import server.dto.FlowPriorityDTO;
import server.dto.FlowStatusDTO;
import server.mapper.FlowItemMapper;
import server.model.*;
import server.repository.FlowItemRepository;
import server.repository.FlowUserRepository;
import server.repository.ItemHistoryRepository;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service responsible for managing FlowItem functionalities.
 *
 * Handles creation, updating, deletion, and fetching of FlowItems
 */
@Service
public class FlowItemService {

    private static final Logger flowitemLogger = LoggerFactory.getLogger(FlowItemService.class);
    private final FlowItemRepository flowItemRepository;
    private final FlowItemMapper flowItemMapper;
    private final FlowUserRepository flowUserRepository;
    private final ItemHistoryRepository itemHistoryRepository;

    public FlowItemService(FlowItemRepository flowItemRepository, FlowItemMapper flowItemMapper,
                           FlowUserRepository flowUserRepository, ItemHistoryRepository itemHistoryRepository) {
        this.flowItemRepository = flowItemRepository;
        this.flowItemMapper = flowItemMapper;
        this.flowUserRepository = flowUserRepository;
        this.itemHistoryRepository = itemHistoryRepository;
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

    public boolean deleteFlowItem(Long id, Long userId) {
        try {
            FlowUser user = findFlowUser(userId);
            if (user.getTitle() == Title.MANAGER || user.getTitle() == Title.ADMIN) {
                flowItemRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            flowitemLogger.error("Failed to delete FlowItem at given Id");
            return false;
        }
    }

    public boolean changeFlowItemStatus(FlowStatusDTO details) {
        try {
            FlowUser user = findFlowUser(details.getUserId());
            if (user.getTitle() != Title.ADMIN && user.getTitle() != Title.MANAGER) {
                return false;
            }
            FlowItem item = getFlowItem(details.getId());
            Status requiredStatus = handleStatus(item.getStatus());
            if (details.getStatus() == Status.CANCELLED) {
                item.setStatus(Status.CANCELLED);
                flowItemRepository.save(item);
                saveHistory(item, user);
                return true;
            }
            if (requiredStatus == details.getStatus()) {
                item.setStatus(requiredStatus);
                flowItemRepository.save(item);
                saveHistory(item, user);
                return true;
            }
            return false;
        } catch (Exception e) {
            flowitemLogger.error("Failed to change FlowItem status");
            return false;
        }
    }

    public boolean changeFlowPriority(FlowPriorityDTO details, Long userId) {
        try {
            FlowUser user = findFlowUser(userId);

            if (user.getTitle() == Title.MANAGER || user.getTitle() == Title.ADMIN) {
                FlowItem item = getFlowItem(details.getId());
                item.setPriority(details.getPriority());
                flowItemRepository.save(item);
                saveHistory(item, user);
                return true;
            }
          return false;
        } catch (Exception e) {
            flowitemLogger.error("Failed to change FlowItem's priority");
            return false;
        }
    }

    public FlowItem getFlowItem(Long id) {
        try {
            Optional<FlowItem> item = flowItemRepository.findById(id);
            return item.orElse(null);
        } catch (Exception e) {
            flowitemLogger.error("Failed to get FlowItem at given Id");
            return null;
        }
    }

    public boolean assignOwner(FlowItemUpdateDTO details) {
        try {
            FlowUser requester = findFlowUser(details.getRequesterId());
            if (requester.getTitle() == Title.MANAGER || requester.getTitle() == Title.ADMIN) {
                FlowUser user = findFlowUser(details.getUserId());
                Optional<FlowItem> item = flowItemRepository.findById(details.getId());
                assert(item.isPresent());
                item.get().setOwner(user);
                flowItemRepository.save(item.get());
                saveHistory(item.get(), requester);
                return true;
            }
            return false;
        } catch (Exception e) {
            flowitemLogger.error("Failed to assign a new owner to the item");
            return false;
        }
    }

    public List<FlowItem> getItemsByOwner(Long id) {
        try {
            return flowItemRepository.findItemsByOwnerId(id);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private Status handleStatus(Status current) {
        if (current == Status.IN_PROGRESS) {
            return Status.COMPLETED;
        }
        if (current == Status.NEW) {
            return Status.IN_PROGRESS;
        }
        return Status.ERROR;
    }

    private FlowUser findFlowUser(Long id) {
        Optional<FlowUser> user = flowUserRepository.findById(id);
        return user.orElse(null);
    }

    private void saveHistory(FlowItem item, FlowUser user) {
        ItemHistory entry = new ItemHistory();
        entry.setName(item.getName());
        entry.setTimestamp(item.getLastupdatedTimestamp());
        entry.setItemId(item.getId());
        entry.setOwnerId(user.getId());
        entry.setChangedBy(user);
        entry.setDescription(item.getDescription());
        itemHistoryRepository.save(entry);
    }


}
