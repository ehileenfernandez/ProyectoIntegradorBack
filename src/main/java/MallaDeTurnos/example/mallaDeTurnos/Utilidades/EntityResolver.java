package MallaDeTurnos.example.mallaDeTurnos.Utilidades;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOShiftChanges;
import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOShiftSchedules;
import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOUserCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.ShiftChangesMapper;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.ShiftSchedulesMapper;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.UserCampaignsMapper;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.*;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityResolver {
    @Autowired
    IRoles iRoles;
    @Autowired
    ITypeId iTypeId;
    @Autowired
    IUser iUser;
    @Autowired
    ICampaigns iCampaigns;
    @Autowired
    IApprovals iApprovals;
    @Autowired
    IShiftSchedules iShiftSchedules;
    @Autowired
    ShiftChangesMapper shiftChangesMapper;
    @Autowired
    ShiftSchedulesMapper shiftSchedulesMapper;
    @Autowired
    UserCampaignsMapper userCampaignsMapper;

    public MRoles resolveRole(Integer id) throws Exception {
        return iRoles.findById(id)
                .orElseThrow(() -> new Exception("Rol no encontrado"));
    }
    public MTypeId resolveTypeId(Integer id) throws Exception {
        return iTypeId.findById(id)
                .orElseThrow(() -> new Exception("Tipo de ID no encontrado"));
    }

    public MUser resolveUser(Integer id) throws Exception {
        return iUser.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
    }

    public MCampaigns resolveCampaign(Integer id) throws Exception {
        return iCampaigns.findById(id)
                .orElseThrow(() -> new Exception("Campaña no encontrada"));
    }

    public MApprovals resolveApproval(Integer id) throws Exception {
        return iApprovals.findById(id)
                .orElseThrow(() -> new Exception("Aprobación no encontrada"));
    }

    public MShiftSchedules resolveShift(Integer id) throws Exception {
        return iShiftSchedules.findById(id)
                .orElseThrow(() -> new Exception("Turno no encontrado"));
    }

    public MShiftChanges resolveShiftChange(DTOShiftChanges dtoShiftChanges) throws Exception{
        MShiftChanges sc = shiftChangesMapper.toModel(dtoShiftChanges);
        if(dtoShiftChanges.getShiftId()!=null){
            sc.setmShiftSchedules(resolveShift(dtoShiftChanges.getShiftId()));
        }
        if(dtoShiftChanges.getApproveId()!=null){
            sc.setmApprovals(resolveApproval(dtoShiftChanges.getApproveId()));
        }
        if(dtoShiftChanges.getApproverId()!=null){
            sc.setmApprover(resolveUser(dtoShiftChanges.getApproverId()));
        }
        if(dtoShiftChanges.getCampaignId()!=null){
            sc.setmCampaigns(resolveCampaign(dtoShiftChanges.getCampaignId()));
        }
        if(dtoShiftChanges.getUserId()!=null){
            sc.setmUser(resolveUser(dtoShiftChanges.getUserId()));
        }
        return sc;
    }

    public MShiftSchedules resolveShiftSchedule(DTOShiftSchedules dtoShiftSchedules) throws Exception{
        MShiftSchedules ss = shiftSchedulesMapper.toModel(dtoShiftSchedules);
        ss.setmUser(resolveUser(dtoShiftSchedules.getUserId()));
        ss.setmCampaigns(resolveCampaign(dtoShiftSchedules.getCampaignId()));
        ss.setTeamLeader(resolveUser(dtoShiftSchedules.getTeamLeaderId()));
        return ss;
    }

    public MUserCampaigns resolveUserCampaign(DTOUserCampaigns dtoUserCampaigns) throws Exception{
        MUserCampaigns uc = userCampaignsMapper.toModel(dtoUserCampaigns);
        uc.setmUser(resolveUser(dtoUserCampaigns.getUserId()));
        uc.setmCampaigns(resolveCampaign(dtoUserCampaigns.getCampaignId()));
        return uc;
    }
}
