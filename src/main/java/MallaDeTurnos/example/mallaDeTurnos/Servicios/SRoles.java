package MallaDeTurnos.example.mallaDeTurnos.Servicios;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTORoles;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.RolesMapper;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MRoles;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SRoles {
    @Autowired
    IRoles iRoles;
    @Autowired
    RolesMapper rolesMapper;
    //Constructor


    public SRoles(IRoles iRoles, RolesMapper rolesMapper) {
        this.iRoles = iRoles;
        this.rolesMapper = rolesMapper;
    }

    //Obtener todos los roles
    public List<MRoles> getRoles () throws Exception{
        try{
            return iRoles.findAll();
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Agregar nuevo rol
    public MRoles addNewRole (DTORoles dtoRoles) throws Exception{
        try{
            MRoles mRoles = rolesMapper.toModel(dtoRoles);
            return iRoles.save(mRoles);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Obtener un rol
    public MRoles getRoleById(Integer rolId) throws Exception{
        try {
            Optional <MRoles> mRol = iRoles.findById(rolId);
            if (mRol.isPresent()){
                return mRol.get();
            } else throw new Exception("No existe el rol");

        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    //Actualizar roles
    public MRoles updateRole(Integer roleId,DTORoles dtoRoles) throws Exception{
        try{
            MRoles mRoles = rolesMapper.toModel(dtoRoles);
            Optional<MRoles> findRole = iRoles.findById(roleId);
            if (findRole.isPresent()){
                //Actualizar registro
                MRoles roleFound = findRole.get();
                roleFound.setRoleName(mRoles.getRoleName());
                roleFound.setAddChanges(mRoles.getAddChanges());
                roleFound.setAddFullChanges(mRoles.getAddFullChanges());
                roleFound.setAddShift(mRoles.getAddShift());
                roleFound.setAddLateShift(mRoles.getAddLateShift());
                //Guardar cambios
                return iRoles.save(roleFound);
            } else throw new Exception("No se encuentra el rol");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
}
