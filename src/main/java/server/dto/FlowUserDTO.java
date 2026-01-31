package server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowUserDTO {

    @Positive
    private Long id;

    @NotBlank(message="Flowuser name shouldn't be blank")
    private String username;

    private String mainRole;
    private String mainClass;
}
