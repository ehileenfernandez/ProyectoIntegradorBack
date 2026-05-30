package MallaDeTurnos.example.mallaDeTurnos.Mappers;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MCampaigns;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CampaignsMapper {
    MCampaigns toModel(DTOCampaigns dto);
    DTOCampaigns toDTO(MCampaigns model);
}
