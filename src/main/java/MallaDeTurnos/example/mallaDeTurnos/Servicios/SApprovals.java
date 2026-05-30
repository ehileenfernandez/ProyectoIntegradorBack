package MallaDeTurnos.example.mallaDeTurnos.Servicios;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOApprovals;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.ApprovalsMapper;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MApprovals;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IApprovals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SApprovals {
    @Autowired
    IApprovals iApprovals;
    @Autowired
    ApprovalsMapper approvalsMapper;
    //Constructor


    public SApprovals(ApprovalsMapper approvalsMapper, IApprovals iApprovals) {
        this.approvalsMapper = approvalsMapper;
        this.iApprovals = iApprovals;
    }

    //Obtener las opciones de aprobaciones
    public List<MApprovals> getApprovals() throws Exception{
        try{
            return iApprovals.findAll();
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Guardar un nueva tipo de aprobacion
    public MApprovals addNewApproval(DTOApprovals dtoApprovals) throws Exception{
        try{
            MApprovals mApprovals = approvalsMapper.toModel(dtoApprovals);
            return iApprovals.save(mApprovals);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Actualizar Aprobacion
    public MApprovals updateApproval(Integer approveId,DTOApprovals dtoApprovals) throws Exception{
        try{
            MApprovals mApprovals = approvalsMapper.toModel(dtoApprovals);
            Optional<MApprovals> foundApproval = iApprovals.findById(approveId);
            if (foundApproval.isPresent()){
                MApprovals approvalFound = foundApproval.get();
                //Actualizar registro
                approvalFound.setApproveName(mApprovals.getApproveName());
                approvalFound.setApproveStatus(mApprovals.getApproveStatus());
                approvalFound.setStatus(mApprovals.getStatus());
                //Guardar registro
                return iApprovals.save(approvalFound);
            } else throw new Exception("No se encuentra la aprobación");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
}
