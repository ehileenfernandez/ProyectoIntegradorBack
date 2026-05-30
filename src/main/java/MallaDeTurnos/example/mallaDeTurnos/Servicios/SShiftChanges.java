package MallaDeTurnos.example.mallaDeTurnos.Servicios;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOShiftChanges;
import MallaDeTurnos.example.mallaDeTurnos.DTO.ShiftChangesRequest;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.ShiftChangesMapper;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MShiftChanges;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IShiftChanges;
import MallaDeTurnos.example.mallaDeTurnos.Utilidades.EntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SShiftChanges {
    @Autowired
    IShiftChanges iShiftChanges;
    @Autowired
    ShiftChangesMapper shiftChangesMapper;
    @Autowired
    EntityResolver entityResolver;

    //Constructor


    public SShiftChanges(EntityResolver entityResolver, IShiftChanges iShiftChanges, ShiftChangesMapper shiftChangesMapper) {
        this.entityResolver = entityResolver;
        this.iShiftChanges = iShiftChanges;
        this.shiftChangesMapper = shiftChangesMapper;
    }

    //Obtener cambios de turnos
    public List<MShiftChanges> getShiftChanges (ShiftChangesRequest shiftChangesRequest) throws Exception{
        try{
            List<MShiftChanges> findShiftChanges = iShiftChanges.findByShiftDateBetweenAndCampaignId(
                    shiftChangesRequest.getMondayDate(),
                    shiftChangesRequest.getSundayDate(),
                    shiftChangesRequest.getCampaignId());
            if (!findShiftChanges.isEmpty()){
                return findShiftChanges;
            } throw new Exception("No hay cambios de turnos");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
    //Cargar nuevos cambios de turnos
    public List<MShiftChanges> addNewShiftChanges (List<DTOShiftChanges> dtoShiftChanges) throws Exception{
        try{
            List<MShiftChanges> mShiftChanges = dtoShiftChanges.stream()
                    .map(dtoSC->{
                        try {
                            return entityResolver.resolveShiftChange(dtoSC);
                        } catch (Exception error) {
                            throw new RuntimeException(error.getMessage());
                        }
                    })
                    .toList();
            return iShiftChanges.saveAll(mShiftChanges);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
    //Actualizar cambios de turnos
    public List<MShiftChanges> updateShiftChanges (List<Integer> shiftsIds,List<DTOShiftChanges> dtoShiftChanges) throws Exception{
        try{

            List<MShiftChanges> mShiftChanges = dtoShiftChanges.stream()
                    .map(dtoSC->{
                        try{
                            return entityResolver.resolveShiftChange(dtoSC);
                        } catch (Exception error) {
                            throw new RuntimeException(error.getMessage());
                        }
                    })
                    .toList();

            List<MShiftChanges> findShiftChanges = iShiftChanges.findAllById(shiftsIds);
            if(!findShiftChanges.isEmpty()){
                //Actualizar registros
                //Recorrer lista para actualizar registro por registro
                for (int i=0;i<findShiftChanges.size();i++){
                    MShiftChanges currentShiftChange = findShiftChanges.get(i);
                    MShiftChanges updatedShiftChange = mShiftChanges.get(i);
                    //Actualizar el registro específico
                    currentShiftChange.setOriginalShift(updatedShiftChange.getOriginalShift());
                    currentShiftChange.setUpdatedShift(updatedShiftChange.getUpdatedShift());
                    currentShiftChange.setmApprovals(updatedShiftChange.getmApprovals());
                    currentShiftChange.setmApprover(updatedShiftChange.getmApprover());
                    currentShiftChange.setmUser(updatedShiftChange.getmUser());
                }
                //Guardar cambios actualizados
                return iShiftChanges.saveAll(findShiftChanges);

            } throw new Exception("No se encuentra el cambio de turno");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
}
