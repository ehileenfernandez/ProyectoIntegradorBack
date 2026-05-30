package MallaDeTurnos.example.mallaDeTurnos.Mappers;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOUser;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    MUser toModel(DTOUser dto);
    DTOUser toDTO(MUser model);
}
