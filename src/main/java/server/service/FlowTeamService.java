package server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import server.dto.FlowTeamCreateDTO;
import server.model.FlowItem;
import server.model.FlowTeam;
import server.repository.FlowTeamRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlowTeamService {

    private static final Logger flowteamLogger = LoggerFactory.getLogger(FlowTeamService.class);
    private final FlowTeamRepository flowTeamRepository;

    public FlowTeamService(FlowTeamRepository flowTeamRepository) {
        this.flowTeamRepository = flowTeamRepository;
    }

    public boolean createFlowTeam(FlowTeamCreateDTO details) {
        try {
            FlowTeam newTeam = new FlowTeam();
            newTeam.setTeamName(details.getTeamName());
            newTeam.setDateCreated(LocalDate.now());
            newTeam.setItems(new ArrayList<>());
            flowTeamRepository.save(newTeam);
            return true;
        } catch (Exception e) {
            flowteamLogger.error("Failed to create a new FlowTeam");
            return false;
        }
    }

    public boolean deleteFlowTeam(Long id) {
        try {
            flowTeamRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            flowteamLogger.error("Failed to delete FlowTeam");
            return false;
        }
    }

    public FlowTeam getFlowTeam(Long id) {
        try {
            return flowTeamRepository.getReferenceById(id);
        } catch (Exception e) {
            flowteamLogger.error("Failed to fetch FlowTeam according to Id");
            return null;
        }
    }

    public List<FlowItem> getFlowTeamItems(Long id) {
        try {
            return flowTeamRepository.getReferenceById(id).getItems();
        } catch (Exception e) {
            flowteamLogger.error("Failed to fetch all FlowTeam's Flowitems");
            return null;
        }
    }


}
