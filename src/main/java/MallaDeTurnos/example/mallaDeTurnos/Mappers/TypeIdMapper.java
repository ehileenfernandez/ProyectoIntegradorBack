package MallaDeTurnos.example.mallaDeTurnos.Mappers;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOTypeId;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MTypeId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeIdMapper {
    MTypeId toModel(DTOTypeId dto);
    DTOTypeId toDTO(MTypeId model);
}
