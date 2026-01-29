package server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import server.dto.FlowUserDTO;
import server.model.FlowUser;
import server.repository.FlowUserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlowUserService {

    private static final Logger flowuserLogger = LoggerFactory.getLogger(FlowUserService.class);
    private final FlowUserRepository flowuserRepository;

    public FlowUserService(FlowUserRepository flowuserRepository) {
        this.flowuserRepository = flowuserRepository;
    }

    public boolean createFlowuser(FlowUserDTO details) {
        try {
            FlowUser newUser =  new FlowUser();
            newUser.setUsername(details.getUsername());
            newUser.setDateJoined(LocalDate.now());
            newUser.setMainRole(details.getMainRole());
            newUser.setMainClass(details.getMainClass());
            flowuserRepository.save(newUser);
            return true;
        } catch (Exception e) {
            flowuserLogger.error("Failed to create a new flowuser.");
            return false;
        }
    }

    public boolean deleteFlowuser(Long id) {
        try {
            flowuserRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            flowuserLogger.error("Failed to delete a flowuser.");
            return false;
        }
    }

    public boolean updateFlowuser(Long id, String details) {
        try {
            if (flowuserRepository.findById(id).isPresent()) {
                FlowUser user = flowuserRepository.findById(id).get();
                return switch (details) {
                    case "class" -> {
                        user.setMainClass("hold");
                        yield true;
                    }
                    case "role" -> {
                        user.setMainRole("hold");
                        yield true;
                    }
                    case "username" -> {
                        user.setUsername("hold");
                        yield true;
                    }
                    case "team" -> {
                        user.setMainTeam(null);
                        yield true;
                    }
                    default -> false;
                };
            }
            return true;
        } catch (Exception e) {
            flowuserLogger.error("Failed to update/edit the flowuser's information");
            return false;
        }
    }

    public List<FlowUser> getFlowusers() {
        try {
            return flowuserRepository.findAll();
        } catch (Exception e) {
            flowuserLogger.error("Failed to retrieve all Flowusers");
            return null;
        }
    }
}
