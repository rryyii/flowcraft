package server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import server.dto.FlowTeamCreateDTO;
import server.model.FlowTeam;
import server.repository.FlowTeamRepository;

@Service
public class FlowTeamService {

    private static final Logger flowteamLogger = LoggerFactory.getLogger(FlowTeamService.class);
    private FlowTeamRepository flowTeamRepository;

    public FlowTeamService(FlowTeamRepository flowTeamRepository) {
        this.flowTeamRepository = flowTeamRepository;
    }

    public boolean createFlowTeam(FlowTeamCreateDTO details) {
        try {
            return true;
        } catch (Exception e) {
            flowteamLogger.error("Failed to create a new FlowTeam");
            return false;
        }
    }

    public boolean deleteFlowTeam(Long id) {
        try {
            return true;
        } catch (Exception e) {
            flowteamLogger.error("Failed to delete FlowTeam");
            return false;
        }
    }

    public FlowTeam getFlowTeam(Long id) {
        try {
            return null;
        } catch (Exception e) {
            flowteamLogger.error("Failed to fetch FlowTeam according to Id");
            return null;
        }
    }


}
