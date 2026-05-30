package MallaDeTurnos.example.mallaDeTurnos.Mappers;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOUserCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MUserCampaigns;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCampaignsMapper {
    MUserCampaigns toModel(DTOUserCampaigns dto);
    DTOUserCampaigns toDTO(MUserCampaigns model);
}
