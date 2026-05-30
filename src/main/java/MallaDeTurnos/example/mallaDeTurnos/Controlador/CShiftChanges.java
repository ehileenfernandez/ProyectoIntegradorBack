package MallaDeTurnos.example.mallaDeTurnos.Controlador;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOShiftChanges;
import MallaDeTurnos.example.mallaDeTurnos.DTO.ShiftChangesRequest;
import MallaDeTurnos.example.mallaDeTurnos.Servicios.SShiftChanges;
import MallaDeTurnos.example.mallaDeTurnos.Utilidades.ResponseEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift_changes")
//Origen
@CrossOrigin("*")
public class CShiftChanges extends ResponseEntityResponse {
    @Autowired
    SShiftChanges sShiftChanges;

    //Obtener Cambios de turnos
    @PutMapping("/get_shift_changes")
    public ResponseEntity<?>getShiftChanges(@RequestBody ShiftChangesRequest shiftChangesRequest) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.OK,sShiftChanges.getShiftChanges(shiftChangesRequest));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }

    //Cargar Nuevos Cambios de Turnos
    @PostMapping
    public ResponseEntity<?>addShiftChanges(@RequestBody List<DTOShiftChanges> dtoShiftChanges) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.CREATED,sShiftChanges.addNewShiftChanges(dtoShiftChanges));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Actualizar Nuevos Cambios de turnos
    @PutMapping
    public ResponseEntity<?>updateShiftChanges(@RequestBody List<DTOShiftChanges> dtoShiftChanges) throws Exception{

        try{
            List<Integer> shiftsIds = dtoShiftChanges.stream()
                    .map(DTOShiftChanges::getShiftChangeId)
                    .toList();
            return responseEntityResponse(HttpStatus.OK,sShiftChanges.updateShiftChanges(shiftsIds,dtoShiftChanges));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
}
