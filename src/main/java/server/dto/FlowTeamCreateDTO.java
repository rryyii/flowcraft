package server.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowTeamCreateDTO {
    private Long id;

    @NotBlank(message = "A new Flowteam must have a name")
    private String teamName;
    private String teamDescription;
}
