package server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import server.model.Title;

@Getter
@Setter
public class FlowUserDTO {

    @Positive
    private Long id;

    @NotBlank(message="Flowuser name shouldn't be blank")
    private String username;

    private String mainRole;
    private String mainClass;
    private Title title;
}
