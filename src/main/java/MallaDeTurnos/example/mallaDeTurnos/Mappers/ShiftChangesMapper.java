package MallaDeTurnos.example.mallaDeTurnos.Mappers;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOShiftChanges;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MShiftChanges;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftChangesMapper {
    MShiftChanges toModel(DTOShiftChanges dto);
    DTOShiftChanges toDTO(MShiftChanges model);
}
