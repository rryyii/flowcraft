package server.mapper;

import org.mapstruct.Mapper;
import server.dto.FlowUserDTO;
import server.model.FlowUser;

@Mapper(componentModel = "spring")
public interface FlowUserMapper {

    FlowUserDTO toDTO(FlowUser entity);
    FlowUser toEntity(FlowUserDTO dto);

}
