package server.dto;

import jakarta.validation.constraints.NotNull;
import server.model.Priority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowPriorityDTO {

    @NotNull(message = "Must provide an Id when changing a FlowItem's priority")
    private Long id;
    @NotNull(message = "Must provide a new priority level")
    private Priority priority;

}
