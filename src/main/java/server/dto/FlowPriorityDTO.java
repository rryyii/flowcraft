package server.dto;

import server.model.Priority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowPriorityDTO {

    private Long id;
    private Priority priority;

}
