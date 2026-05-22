package server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import server.dto.FlowTeamCreateDTO;
import server.mapper.FlowTeamMapper;
import server.model.FlowItem;
import server.model.FlowTeam;
import server.model.FlowUser;
import server.model.Title;
import server.repository.FlowTeamRepository;
import server.repository.FlowUserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service responsible for managing FlowTeam functionalities.
 *
 * Handles creation, updating, deletion and removal of FlowUsers, and fetching of FlowTeams
 * and any ownership of FlowItems.
 */
@Service
public class FlowTeamService {

    private static final Logger flowteamLogger = LoggerFactory.getLogger(FlowTeamService.class);
    private final FlowTeamRepository flowTeamRepository;
    private final FlowTeamMapper flowTeamMapper;
    private final FlowUserRepository flowUserRepository;

    public FlowTeamService(FlowTeamRepository flowTeamRepository, FlowTeamMapper flowTeamMapper,
                           FlowUserRepository flowUserRepository) {
        this.flowTeamRepository = flowTeamRepository;
        this.flowTeamMapper = flowTeamMapper;
        this.flowUserRepository = flowUserRepository;
    }

    public boolean createFlowTeam(FlowTeamCreateDTO details) {
        try {
            FlowUser user = findFlowUser(details.getId());
            if (user.getTitle() == Title.ADMIN || user.getTitle() == Title.MANAGER) {
                flowTeamRepository.save(flowTeamMapper.toEntity(details));
                return true;
            }
            return false;
        } catch (Exception e) {
            flowteamLogger.error("Failed to create a new FlowTeam");
            return false;
        }
    }

    public boolean removeFlowUser(Long teamId, Long userId, Long requestId) {
        try {
            FlowUser requester = findFlowUser(requestId);
            if (requester.getTitle() == Title.ADMIN || requester.getTitle() == Title.MANAGER) {
                FlowUser userRemove = findFlowUser(userId);
                Optional<FlowTeam> team = flowTeamRepository.findById(teamId);
                if (team.isEmpty()) {
                    return false;
                }
                team.get().getUsers().remove(userRemove);
                flowTeamRepository.save(team.get());
                return true;
            }
            return false;
        } catch (Exception e) {
            flowteamLogger.error("Failed to remove user from FlowTeam");
            return false;
        }
    }

    public boolean deleteFlowTeam(Long id, Long userId) {
        try {
            FlowUser user = findFlowUser(userId);
            if (user.getTitle() == Title.ADMIN || user.getTitle() == Title.MANAGER) {
                flowTeamRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            flowteamLogger.error("Failed to delete FlowTeam");
            return false;
        }
    }

    public FlowTeam getFlowTeam(Long id) {
        try {
            Optional<FlowTeam> team = flowTeamRepository.findById(id);
            return team.orElse(null);
        } catch (Exception e) {
            flowteamLogger.error("Failed to fetch FlowTeam according to Id");
            return null;
        }
    }

    public boolean addFlowUser(Long id, Long userId, Long requesterId) {
        try {
            FlowUser user = findFlowUser(userId);
            FlowUser requester = findFlowUser(requesterId);
            if (requester.getTitle() == Title.ADMIN || requester.getTitle() == Title.MANAGER) {
                Optional<FlowTeam> team = flowTeamRepository.findById(id);
                if (team.isEmpty()) {
                    return false;
                }
                team.get().getUsers().add(user);
                flowTeamRepository.save(team.get());
                return true;
            }
            return false;
        } catch (Exception e) {
            flowteamLogger.error("Failed to add new FlowUser to team");
            return false;
        }
    }

    public List<FlowItem> getFlowTeamItems(Long id, Long userId) {
        try {
            FlowUser user = findFlowUser(userId);
            if (user.getTitle() == Title.ADMIN || user.getTitle() == Title.MANAGER || user.getTitle() == Title.MEMBER) {
                return flowTeamRepository.getReferenceById(id).getItems();
            }
            return null;
        } catch (Exception e) {
            flowteamLogger.error("Failed to fetch all FlowTeam's Flowitems");
            return Collections.emptyList();
        }
    }

    private FlowUser findFlowUser(Long id) {
        Optional<FlowUser> user = flowUserRepository.findById(id);
        return user.orElse(null);
    }

}
