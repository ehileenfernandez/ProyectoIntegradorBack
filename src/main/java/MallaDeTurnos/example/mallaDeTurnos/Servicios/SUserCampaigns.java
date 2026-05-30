package MallaDeTurnos.example.mallaDeTurnos.Servicios;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOUserCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MRoles;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MUser;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MUserCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.ICampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IRoles;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IUser;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IUserCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Utilidades.EntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SUserCampaigns {
    @Autowired
    IUserCampaigns iUserCampaigns;
    @Autowired
    IRoles iRoles;
    @Autowired
    IUser iUser;
    @Autowired
    ICampaigns iCampaigns;
    @Autowired
    EntityResolver entityResolver;

    //Constructor

    public SUserCampaigns(EntityResolver entityResolver, ICampaigns iCampaigns, IRoles iRoles, IUser iUser, IUserCampaigns iUserCampaigns) {
        this.entityResolver = entityResolver;
        this.iCampaigns = iCampaigns;
        this.iRoles = iRoles;
        this.iUser = iUser;
        this.iUserCampaigns = iUserCampaigns;
    }


    //Agregar nueva relación usuaria, teniendo en cuenta que los users que no tienen permisos SOLO pueden
    //pertenecer a 1 campaña, dado que serían usuarios para agregar turno

    public List<MUserCampaigns> addUserCampaign(List<DTOUserCampaigns> dtoUserCampaigns) throws Exception{
        try{
            List<MUserCampaigns> mUserCampaigns = dtoUserCampaigns.stream()
                    .map(dtoUC->{
                        try {
                            return entityResolver.resolveUserCampaign(dtoUC);
                        } catch (Exception error) {
                            throw new RuntimeException(error.getMessage());
                        }
                    })
                    .toList();
            //Obtengo los roles que solamente pueden tener 1 campaña asignada
            List<MRoles> findRolesTeamShiftSchedule = iRoles.findRolesByAddShiftAndAddLateShiftAndAddChangesAndAddFullChanges(false,
                    false,false,false);
            List<Integer> rolesIdForShiftSchedules = findRolesTeamShiftSchedule.stream()
                    .map(MRoles::getRoleId)
                    .toList();
            Integer userId = mUserCampaigns.getFirst().getUserId();
            Optional<MUser> findUser = iUser.findById(userId);
            if(findUser.isEmpty()){
                throw new Exception("No se encuentra el usuario");
            } else {
                MUser currentUser = findUser.get();
                if(!currentUser.getStatus()){
                    throw new Exception("El usuario no se encuentra activo");
                } else {
                    if (rolesIdForShiftSchedules.contains(currentUser.getRoleId())){
                        List<MUserCampaigns> assignedCampaings = iUserCampaigns.findByMUser_UserId(currentUser.getUserId());
                        if (!assignedCampaings.isEmpty()){
                            throw new Exception("El usuario ya tiene 1 campaña asignada");
                        }
                        if (mUserCampaigns.size()>1){
                            throw new Exception("Este usuario solo puede tener 1 campaña asignada");
                        }
                        }
                    }
                }
            List<MUserCampaigns> userCampaignsToAdd = new ArrayList<>();

            for(MUserCampaigns userCampaigns: mUserCampaigns){
                Optional<MCampaigns> findCampaign = iCampaigns.findById(userCampaigns.getCampaignId());
                if (findCampaign.isEmpty()||!findCampaign.get().getStatus()){
                    continue;
                }
                userCampaignsToAdd.add(userCampaigns);
            }
            if (userCampaignsToAdd.isEmpty()){
                throw new Exception("Debe agregar una campaña valida para continuar");
            }
            return iUserCampaigns.saveAll(userCampaignsToAdd);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
    //Eliminar relaciones por ahora se utilizará para actualización y cambios de campaña
    public Boolean deleteUserCampaigns(List<Integer> userCampaignsIds) throws Exception{
        try{
            List<Integer> userCampaignsFound = userCampaignsIds.stream()
                    .filter(uc->{
                        Optional<MUserCampaigns> findUserCampaign = iUserCampaigns.findById(uc);
                        return findUserCampaign.isPresent();
                    })
                    .toList();
            if (userCampaignsFound.isEmpty()){
                throw new Exception("No existen campañas para borrar");
            }
            iUserCampaigns.deleteAllById(userCampaignsFound);
            return true;
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
    //Actualizar registros
    public List<MUserCampaigns> updateUserCampaigns(List<DTOUserCampaigns> dtoUserCampaigns) throws Exception{
        try{
            List<MUserCampaigns> mUserCampaigns = dtoUserCampaigns.stream()
                    .map(dtoUC->{
                        try {
                            return entityResolver.resolveUserCampaign(dtoUC);
                        } catch (Exception error) {
                            throw new RuntimeException(error.getMessage());
                        }
                    })
                    .toList();
            List<MUserCampaigns> userCampaignsFoundValid = mUserCampaigns.stream()
                    .filter(uc->{
                        Optional<MUserCampaigns> findUserCampaign = iUserCampaigns.findById(uc.getUserCampaignId());
                        Optional<MCampaigns> findValidCampaign = iCampaigns.findById(uc.getCampaignId());
                        return findUserCampaign.isPresent() && findValidCampaign.isPresent() && findValidCampaign.get().getStatus();
                    })
                    .toList();
            if (userCampaignsFoundValid.isEmpty()){
                throw new Exception("No hay campañas validas para actualizar");
            }
            //Obtengo los roles que solamente pueden tener 1 campaña asignada
            List<MRoles> findRolesTeamShiftSchedule = iRoles.findRolesByAddShiftAndAddLateShiftAndAddChangesAndAddFullChanges(false,
                    false,false,false);
            List<Integer> rolesIdForShiftSchedules = findRolesTeamShiftSchedule.stream()
                    .map(MRoles::getRoleId)
                    .toList();
            Integer userId = mUserCampaigns.getFirst().getUserId();
            Optional<MUser> findUser = iUser.findById(userId);

            if(findUser.isEmpty()){
                throw new Exception("No se encuentra el usuario");
            }

            MUser currentUser = findUser.get();

            if(!currentUser.getStatus()){
                throw new Exception("El usuario no se encuentra activo");
            }
            if (rolesIdForShiftSchedules.contains(currentUser.getRoleId())){
                if(userCampaignsFoundValid.size()>1){
                    throw new Exception("Este usuario solo puede tener 1 campaña asignada");
                }
            }
            //No es lo óptimo, pero en este caso vamos a borrar las relaciones que ya hay y dejar solamente estas.
            List<MUserCampaigns> userCampaignsSaved = iUserCampaigns.findByMUser_UserId(userId);

            if(userCampaignsSaved.isEmpty()){
                return iUserCampaigns.saveAll(userCampaignsFoundValid);
            }
            List<Integer> userCampaignsIdsToDelete = userCampaignsSaved.stream()
                    .map(MUserCampaigns::getUserCampaignId)
                    .toList();
            if (deleteUserCampaigns(userCampaignsIdsToDelete)){
                List<MUserCampaigns> newCampaigns = userCampaignsFoundValid.stream()
                        .map(uc -> {
                            MUserCampaigns newUc = new MUserCampaigns();
                            newUc.setmUser(uc.getmUser());
                            newUc.setmCampaigns(uc.getmCampaigns());
                            return newUc;
                        })
                        .toList();
                return iUserCampaigns.saveAll(newCampaigns);
            } else throw new Exception("Error al eliminar las campañas del usuario");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Obtener campañas activas asignadas a un usuario
    public List<MUserCampaigns> getUserCampaigns(Integer userId) throws Exception {
        try {
            List<MUserCampaigns> userCampaigns = iUserCampaigns
                    .findByMUser_UserIdAndMCampaigns_Status(userId, true);
            if (userCampaigns.isEmpty()) {
                throw new Exception("No hay campañas activas asignadas para este usuario");
            }
            return userCampaigns;
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

}
