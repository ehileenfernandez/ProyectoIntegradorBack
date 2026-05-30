package MallaDeTurnos.example.mallaDeTurnos.Servicios;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.CampaignsMapper;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.ICampaigns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SCampaigns {
    @Autowired
    ICampaigns iCampaigns;
    @Autowired
    CampaignsMapper campaignsMapper;
    //Constructor


    public SCampaigns(CampaignsMapper campaignsMapper, ICampaigns iCampaigns) {
        this.campaignsMapper = campaignsMapper;
        this.iCampaigns = iCampaigns;
    }

    //Obtener todas las campañas
    public List<MCampaigns> getCampaigns() throws Exception{
        try{
            return iCampaigns.findAll();
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Obtener info de la campaña
    public MCampaigns getCampaignInfo(Integer campaignId) throws Exception{
        try {
            Optional<MCampaigns> findCampaign = iCampaigns.findById(campaignId);
            if (findCampaign.isPresent()){
                return findCampaign.get();
            } throw new Exception("No se encuentra la campaña");
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
    //Obtener campañas asignadas a 1 usuario
    public List<MCampaigns> getCampaingsById(List<Integer> campaignIds)throws Exception{
        try {
            List<MCampaigns> campaignsFound = new ArrayList<>();
            for(Integer campaignId:campaignIds){
                Optional <MCampaigns> campaignFound = iCampaigns.findById(campaignId);
                campaignFound.ifPresent(campaignsFound::add);
            }
            return campaignsFound;
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    //Agregar Nueva campaña
    public MCampaigns addNewCampaign(DTOCampaigns dtoCampaigns) throws Exception{
        try{
            MCampaigns mCampaigns = campaignsMapper.toModel(dtoCampaigns);
            return iCampaigns.save(mCampaigns);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Actualizar campaña
    public MCampaigns updateCampaign (Integer campaignID, DTOCampaigns dtoCampaigns) throws Exception{
        try{
            MCampaigns mCampaigns = campaignsMapper.toModel(dtoCampaigns);
            Optional<MCampaigns> findCampaign = iCampaigns.findById(campaignID);
            if (findCampaign.isPresent()){
                MCampaigns campaingFound = findCampaign.get();
                //Actualizar Registro
                campaingFound.setCampaignName(mCampaigns.getCampaignName());
                campaingFound.setStatus(mCampaigns.getStatus());
                //Guardar registro
                return iCampaigns.save(campaingFound);
            } else throw new Exception("No se encuentra la campaña");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
}
