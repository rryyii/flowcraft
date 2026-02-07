package server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import server.model.Status;

@Getter
@Setter
public class FlowStatusDTO {
    @NotNull(message = "Must provide an id when changing a FlowItem's status")
    private Long id;
    @NotNull(message = "Must provide a new status")
    private Status status;
}
