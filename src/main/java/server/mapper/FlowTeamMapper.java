package server.mapper;

import org.mapstruct.Mapper;
import server.dto.FlowTeamCreateDTO;
import server.model.FlowTeam;

@Mapper(componentModel = "spring")
public interface FlowTeamMapper {

    FlowTeamCreateDTO toDTO(FlowTeam entity);
    FlowTeam toEntity(FlowTeamCreateDTO dto);

}

