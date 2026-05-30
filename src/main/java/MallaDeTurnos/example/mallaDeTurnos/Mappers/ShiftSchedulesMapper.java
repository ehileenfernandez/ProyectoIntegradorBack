package MallaDeTurnos.example.mallaDeTurnos.Mappers;


import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOShiftSchedules;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MShiftSchedules;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftSchedulesMapper {
    MShiftSchedules toModel(DTOShiftSchedules dto);
    DTOShiftSchedules toDTO(MShiftSchedules model);
}
