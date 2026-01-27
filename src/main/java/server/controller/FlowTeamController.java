package server.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.FlowTeamService;

@RestController
@RequestMapping("flowteam")
public class FlowTeamController {

    private FlowTeamService flowTeamService;

    public FlowTeamController(FlowTeamService flowTeamService) {
        this.flowTeamService = flowTeamService;
    }

}
