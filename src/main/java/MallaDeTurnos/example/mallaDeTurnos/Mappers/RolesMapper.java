package MallaDeTurnos.example.mallaDeTurnos.Mappers;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTORoles;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MRoles;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    MRoles toModel(DTORoles dto);
    DTORoles toDTO(MRoles model);
}
