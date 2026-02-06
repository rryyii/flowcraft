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
import java.util.List;

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
            if (user == null) return false;
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

    public boolean removeFlowUser(Long id, Long userId) {
        try {
            FlowUser user = findFlowUser(userId);
            if (user == null) return false;

            if (user.getTitle() == Title.ADMIN || user.getTitle() == Title.MANAGER) {
                FlowUser userRemove = flowUserRepository.getReferenceById(id);
                userRemove.setMainTeam(null);
                flowUserRepository.save(userRemove);
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
            if (user == null) return false;

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
            if (flowTeamRepository.findById(id).isPresent()) {
                return flowTeamRepository.findById(id).get();
            }
            return null;
        } catch (Exception e) {
            flowteamLogger.error("Failed to fetch FlowTeam according to Id");
            return null;
        }
    }

    public List<FlowItem> getFlowTeamItems(Long id, Long userId) {
        try {
            FlowUser user = findFlowUser(userId);
            if (user == null) return null;

            if (user.getTitle() == Title.ADMIN || user.getTitle() == Title.MANAGER || user.getTitle() == Title.MEMBER) {
                return flowTeamRepository.getReferenceById(id).getItems();
            }
            return null;
        } catch (Exception e) {
            flowteamLogger.error("Failed to fetch all FlowTeam's Flowitems");
            return null;
        }
    }

    private FlowUser findFlowUser(Long id) {
        if (flowUserRepository.findById(id).isPresent()) {
            return flowUserRepository.findById(id).get();
        }
        return null;
    }


}
