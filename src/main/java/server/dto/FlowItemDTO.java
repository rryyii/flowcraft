package server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import server.model.FlowTeam;
import server.model.Status;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlowItemDTO {

    private Long id;

    @NotBlank(message = "Flowitem name shouldn't be blank")
    private String name;

    private String description;

    private Status status;

    @NotNull(message = "Flowitem must have a set deadline")
    private LocalDateTime deadline;

    @NotNull(message = "Flowitem must have a set team")
    private FlowTeam team;

}
