package server.dto;

import lombok.Data;

@Data
public class FlowuserDTO {
    private Long id;
    private String username;
    private String currentRole;
    private String currentClass;
}
