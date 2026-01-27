package server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import server.repository.FlowTeamRepository;

@Service
public class FlowTeamService {

    private static final Logger flowteamLogger = LoggerFactory.getLogger(FlowTeamService.class);
    private FlowTeamRepository flowTeamRepository;

    public FlowTeamService(FlowTeamRepository flowTeamRepository) {
        this.flowTeamRepository = flowTeamRepository;
    }
}
