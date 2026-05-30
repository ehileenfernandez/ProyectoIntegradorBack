package MallaDeTurnos.example.mallaDeTurnos.Repositorio;

import MallaDeTurnos.example.mallaDeTurnos.Modelo.MUserCampaigns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserCampaigns extends JpaRepository<MUserCampaigns,Integer> {
    //Campañas de un usuario que esten activas las campañas
    List<MUserCampaigns> findByMUser_UserIdAndMCampaigns_Status(Integer userId,Boolean status1);
    //Usuarios de una campaña que esten activos
    List<MUserCampaigns>findByMCampaigns_CampaignIdAndMCampaigns_StatusAndMUser_StatusAndMUser_RoleIdIn(Integer campaignID,Boolean statusCampaign,Boolean statusUser,List<Integer> roleId);
    //Buscar usuarios asignados
    List<MUserCampaigns> findByMUser_UserId(Integer userId);
}
