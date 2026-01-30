package server.dto;

import lombok.Data;
import server.model.Status;

@Data
public class FlowStatusDTO {
    private Long id;
    private Status currentStatus;
    private Status nextStatus;
}
