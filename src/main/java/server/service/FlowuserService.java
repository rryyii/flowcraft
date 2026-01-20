package server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import server.dto.FlowuserDTO;
import server.model.Flowuser;
import server.repository.FlowuserRepository;

import java.util.Date;

@Service
public class FlowuserService {

    private static final Logger flowuserLogger = LoggerFactory.getLogger(FlowuserService.class);
    private FlowuserRepository flowuserRepository;

    public FlowuserService(FlowuserRepository flowuserRepository) {
        this.flowuserRepository = flowuserRepository;
    }

    public boolean createFlowuser(FlowuserDTO details) {
        try {
            Flowuser newUser =  new Flowuser();
            newUser.setUsername(details.getUsername());
            newUser.setDateJoined(new Date());
            newUser.setMainRole(details.getCurrentRole());
            newUser.setMainClass(details.getCurrentClass());
            flowuserRepository.save(newUser);
            return true;
        } catch (Exception e) {
            flowuserLogger.error("Failed to create a new flowuser.");
            return false;
        }
    }
}
