package MallaDeTurnos.example.mallaDeTurnos.Mappers;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOAbsenceReasons;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MAbsenceReasons;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbsenceReasonsMapper {
    MAbsenceReasons toModel(DTOAbsenceReasons dto);
    DTOAbsenceReasons toDTO(MAbsenceReasons model);
}
