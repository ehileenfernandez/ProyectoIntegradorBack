package MallaDeTurnos.example.mallaDeTurnos.Mappers;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOApprovals;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MApprovals;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApprovalsMapper {
    MApprovals toModel(DTOApprovals dto);
    DTOApprovals toDTO(MApprovals model);
}
