package server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowItemUpdateDTO {
    private Long id;
    private Long requesterId;
    private Long userId;
}
