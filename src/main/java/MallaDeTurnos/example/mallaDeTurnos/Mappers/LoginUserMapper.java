package MallaDeTurnos.example.mallaDeTurnos.Mappers;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOLoginUser;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MLoginUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginUserMapper {
    MLoginUser toModel (DTOLoginUser dto);
    DTOLoginUser toDTO (MLoginUser model);
}
