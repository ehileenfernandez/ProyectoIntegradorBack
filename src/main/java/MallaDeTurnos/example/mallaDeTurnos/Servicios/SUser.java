    package MallaDeTurnos.example.mallaDeTurnos.Servicios;

    import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOUser;
    import MallaDeTurnos.example.mallaDeTurnos.Mappers.UserMapper;
    import MallaDeTurnos.example.mallaDeTurnos.Modelo.MRoles;
    import MallaDeTurnos.example.mallaDeTurnos.Modelo.MUser;
    import MallaDeTurnos.example.mallaDeTurnos.Modelo.MUserCampaigns;
    import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IRoles;
    import MallaDeTurnos.example.mallaDeTurnos.Repositorio.ITypeId;
    import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IUser;
    import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IUserCampaigns;
    import MallaDeTurnos.example.mallaDeTurnos.Utilidades.EntityResolver;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class SUser {
        @Autowired
        IUser iUser;
        @Autowired
        IRoles iRoles;
        @Autowired
        IUserCampaigns iUserCampaigns;
        @Autowired
        UserMapper userMapper;
        @Autowired
        ITypeId iTypeId;
        @Autowired
        EntityResolver entityResolver;
        //Constructor


        public SUser(EntityResolver entityResolver, IRoles iRoles, ITypeId iTypeId, IUser iUser, IUserCampaigns iUserCampaigns, UserMapper userMapper) {
            this.entityResolver = entityResolver;
            this.iRoles = iRoles;
            this.iTypeId = iTypeId;
            this.iUser = iUser;
            this.iUserCampaigns = iUserCampaigns;
            this.userMapper = userMapper;
        }

        //Retornar todos los usuarios
        public List<MUser> getUsers () throws Exception{
            try{
                return iUser.findAll();
            } catch (Exception error) {
                throw new Exception(error);
            }
        }
        //Retornar lista de usuarios por IDs

        public List<MUser> getUsersByIds(List<Integer> usersIds) throws Exception{
            try{
                return iUser.findAllById(usersIds);
            } catch (Exception error) {
                throw new Exception(error.getMessage());
            }
        }

        //Retornar 1 usuario

        public Optional<MUser> getUserById(Integer userId) throws Exception{
            try {
                return iUser.findById(userId);
            } catch (Exception error) {
                throw new Exception(error.getMessage());
            }
        }

        //Retornar el equipo de un TL que estan activos
        public List<MUser> getActiveTeam (Integer teamLeaderId) throws Exception{
            try{
                return iUser.findByTeamLeaderIdAndStatus(teamLeaderId,true);
            } catch (Exception error) {
                throw new Exception(error);
            }
        }

        //Retornar todos los usuarios de un equipo que se le deben cargar malla
        public List<MUser> getTeamShiftSchedule (Integer campaignId) throws Exception{
            try{
                List<MRoles> findRolesTeamShiftSchedule = iRoles.findRolesByAddShiftAndAddLateShiftAndAddChangesAndAddFullChanges(false,
                        false,false,false);
                if (findRolesTeamShiftSchedule.isEmpty()){
                    throw new Exception("No hay equipo con roles para agregar turnos");
                } else {
                    List<Integer> rolesIdForShiftSchedules = findRolesTeamShiftSchedule.stream()
                            .map(MRoles::getRoleId)
                            .toList();
                    List<MUser> usersActiveRoles = iUser.findByStatusAndRoleIdIn(true,rolesIdForShiftSchedules);
                    if (usersActiveRoles.isEmpty()){
                        throw new Exception("No hay equipo para agregar turnos");
                    } else {
                        List<Integer> usersIdActiveRoles = usersActiveRoles.stream()
                                .map(MUser::getUserId)
                                .toList();
                        List<MUserCampaigns> userCampaignsRole = iUserCampaigns.findByMCampaigns_CampaignIdAndMCampaigns_StatusAndMUser_StatusAndMUser_RoleIdIn(campaignId,
                                true,true,rolesIdForShiftSchedules);
                        if (!userCampaignsRole.isEmpty()){
                            List<Integer> usersIdCampaigns = userCampaignsRole.stream()
                                    .map(MUserCampaigns::getUserId)
                                    .toList();
                            List<MUser> usersCampaingShiftSchedule = iUser.findAllById(usersIdCampaigns);
                            if (usersCampaingShiftSchedule.isEmpty()){
                                throw new Exception("No hay equipo para agregar turnos");
                            } else {
                                return usersCampaingShiftSchedule;
                            }
                        } else throw new Exception("No hay usuarios para cargar turnos");
                    }
                }
            } catch (Exception error) {
                throw new Exception(error);
            }
        }
        //Crear nuevo usuario
        public MUser addNewUser(DTOUser dtoUser) throws Exception{
            try{
                MUser mUser = userMapper.toModel(dtoUser);
                mUser.setmRoles(iRoles.findById(dtoUser.getRoleId()).orElseThrow(()->new Exception("Rol no encontrado")));
                mUser.setmTypeId(iTypeId.findById(dtoUser.getIdTypeId())
                        .orElseThrow(() -> new Exception("Tipo de ID no encontrado")));
                if (dtoUser.getTeamLeaderId() != null) {
                    mUser.setTeamLeader(iUser.findById(dtoUser.getTeamLeaderId())
                            .orElseThrow(() -> new Exception("Team leader no encontrado")));
                }
                return iUser.save(mUser);
            } catch (Exception error) {
                throw new Exception(error);
            }
        }
        //Actualizar información de usuario
        public MUser updateUser(Integer userId, DTOUser dtoUser) throws Exception {
            try {
                Optional<MUser> findUser = iUser.findById(userId);
                if (findUser.isPresent()) {
                    MUser userFound = findUser.get();
                    userFound.setUserFullName(dtoUser.getUserFullName());
                    userFound.setNumberId(dtoUser.getNumberId());
                    userFound.setStatus(dtoUser.getStatus());
                    userFound.setmRoles(entityResolver.resolveRole(dtoUser.getRoleId()));
                    userFound.setmTypeId(entityResolver.resolveTypeId(dtoUser.getIdTypeId()));
                    if (dtoUser.getTeamLeaderId() != null) {
                        userFound.setTeamLeader(entityResolver.resolveUser(dtoUser.getTeamLeaderId()));
                    }
                    return iUser.save(userFound);
                } else throw new Exception("No se encuentra el usuario");
            } catch (Exception error) {
                throw new Exception(error);
            }
        }
    }
