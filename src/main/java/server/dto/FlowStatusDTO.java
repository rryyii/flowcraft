package server.dto;

import lombok.Getter;
import lombok.Setter;
import server.model.Status;

@Getter
@Setter
public class FlowStatusDTO {
    private Long id;
    private Status currentStatus;
    private Status nextStatus;
}
