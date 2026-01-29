package server.dto;

import lombok.Data;

@Data
public class FlowUserDTO {
    private Long id;
    private String username;
    private String mainRole;
    private String mainClass;
}
