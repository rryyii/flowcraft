package server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowTeamCreateDTO {
    @NotNull(message = "Must provide an ID when creating a FlowTeam")
    private Long id;

    @NotBlank(message = "A new Flowteam must have a name")
    private String teamName;
    private String teamDescription;
}
