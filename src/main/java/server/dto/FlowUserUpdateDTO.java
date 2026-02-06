package server.dto;

import lombok.Getter;
import lombok.Setter;
import server.model.Title;

@Getter
@Setter
public class FlowUserUpdateDTO {

    private String mainClass;
    private String mainRole;
    private String username;
    private Title title;

}
