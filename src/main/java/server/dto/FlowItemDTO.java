package server.dto;

import lombok.Data;
import server.model.FlowTeam;
import server.model.Status;

import java.time.LocalDateTime;

@Data
public class FlowItemDTO {

    private Long id;
    private String name;
    private String description;
    private Status status;
    private LocalDateTime deadline;
    private FlowTeam team;

}
