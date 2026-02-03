package server.mapper;

import org.mapstruct.Mapper;
import server.dto.FlowItemDTO;
import server.model.FlowItem;

@Mapper(componentModel = "spring")
public interface FlowItemMapper {

    FlowItemDTO toDto(FlowItem entity);
    FlowItem toEntity(FlowItemDTO dto);

}
