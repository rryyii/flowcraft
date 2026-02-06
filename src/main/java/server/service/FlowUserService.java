package server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import server.dto.FlowUserDTO;
import server.dto.FlowUserUpdateDTO;
import server.mapper.FlowUserMapper;
import server.model.FlowUser;
import server.repository.FlowUserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlowUserService {

    private static final Logger flowuserLogger = LoggerFactory.getLogger(FlowUserService.class);
    private final FlowUserRepository flowuserRepository;
    private final FlowUserMapper flowUserMapper;

    public FlowUserService(FlowUserRepository flowuserRepository, FlowUserMapper flowUserMapper) {
        this.flowuserRepository = flowuserRepository;
        this.flowUserMapper = flowUserMapper;
    }

    public boolean createFlowuser(FlowUserDTO details) {
        try {
            flowuserRepository.save(flowUserMapper.toEntity(details));
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

    public boolean updateFlowuser(Long id, FlowUserUpdateDTO details) {
        try {
            if (flowuserRepository.findById(id).isPresent()) {
                FlowUser user = flowuserRepository.findById(id).get();
                if (!(details.getMainRole() == null)) {
                    user.setMainRole(details.getMainRole());
                }
                if (!(details.getUsername() == null)) {
                    user.setUsername(details.getUsername());
                }
                if (!(details.getMainClass() == null)) {
                    user.setMainClass(details.getMainClass());
                }
                if(!(details.getTitle() == null)) {
                    user.setTitle(details.getTitle());
                }
                flowuserRepository.save(user);
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
