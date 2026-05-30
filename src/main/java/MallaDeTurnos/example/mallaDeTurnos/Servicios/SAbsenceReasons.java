package MallaDeTurnos.example.mallaDeTurnos.Servicios;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOAbsenceReasons;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.AbsenceReasonsMapper;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MAbsenceReasons;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IAbsenceReasons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SAbsenceReasons {
    @Autowired
    IAbsenceReasons iAbsenceReasons;
    @Autowired
    AbsenceReasonsMapper absenceReasonsMapper;
    //Constructor

    public SAbsenceReasons(AbsenceReasonsMapper absenceReasonsMapper, IAbsenceReasons iAbsenceReasons) {
        this.absenceReasonsMapper = absenceReasonsMapper;
        this.iAbsenceReasons = iAbsenceReasons;
    }

    //Obtener todos los AbsenceReasons
    public List<MAbsenceReasons> getAbsenceReasons() throws Exception{
        try{
            return iAbsenceReasons.findAll();
        } catch (Exception error){
            throw new Exception(error);
        }
    }

    //Guardar un nuevo AbsenceReason
    public MAbsenceReasons addNewAbsenceReasons(DTOAbsenceReasons dtoAbsenceReasons) throws Exception{
        try{

            MAbsenceReasons mAbsenceReasons = new MAbsenceReasons();
            mAbsenceReasons.setAbsenceName(dtoAbsenceReasons.getAbsenceName());
            mAbsenceReasons.setStatus(dtoAbsenceReasons.getStatus());

            return iAbsenceReasons.save(mAbsenceReasons);
        } catch (Exception error){
            throw new Exception(error);
        }
    }

    //Actualizar el estado de un absencereason
    public MAbsenceReasons updateAbsenceReason(Integer absenceReasonId, DTOAbsenceReasons dtoAbsenceReasons) throws Exception{
        try{
            MAbsenceReasons mAbsenceReasons = absenceReasonsMapper.toModel(dtoAbsenceReasons);
            Optional<MAbsenceReasons> foundAbsenceReason =iAbsenceReasons.findById(absenceReasonId);
            if (foundAbsenceReason.isPresent()){
                MAbsenceReasons foundRegister = foundAbsenceReason.get();
                //Actualizar el registro
                foundRegister.setAbsenceName(mAbsenceReasons.getAbsenceName());
                foundRegister.setStatus(mAbsenceReasons.getStatus());
                //Guardar los cambios
                return iAbsenceReasons.save(foundRegister);
            } else throw new Exception("No se encuentra el motivo de ausencia");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
}
