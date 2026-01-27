package server.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import server.repository.FlowItemRepository;

@Service
public class FlowItemService {

    private static final Logger flowitemLogger = LoggerFactory.getLogger(FlowItemService.class);
    private FlowItemRepository flowItemRepository;

    public FlowItemService(FlowItemRepository flowItemRepository) {
        this.flowItemRepository = flowItemRepository;
    }

}
