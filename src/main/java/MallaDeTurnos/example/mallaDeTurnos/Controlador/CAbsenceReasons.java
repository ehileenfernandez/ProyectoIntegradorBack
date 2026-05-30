package MallaDeTurnos.example.mallaDeTurnos.Controlador;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOAbsenceReasons;
import MallaDeTurnos.example.mallaDeTurnos.Servicios.SAbsenceReasons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/absence_reasons")

//Origen
@CrossOrigin(origins = "*")
public class CAbsenceReasons {
    @Autowired
    SAbsenceReasons sAbsenceReasons;

    //Obtener todos los AbsenceReasons
    @GetMapping
    public ResponseEntity<?> getAbsenceReasons() throws Exception {
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sAbsenceReasons.getAbsenceReasons());
        } catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
    //Guardar un nuevo Absence Reason
    @PostMapping
    public ResponseEntity<?> addNewAbsenceReason(@RequestBody DTOAbsenceReasons dtoAbsenceReasons) throws Exception{
        try{
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.sAbsenceReasons.addNewAbsenceReasons(dtoAbsenceReasons));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
    //Actualizar un Absence Reason
    @PutMapping("/{absenceReasonId}")
    public ResponseEntity<?> updateAbsenceReason(
            @PathVariable Integer absenceReasonId,
            @RequestBody DTOAbsenceReasons dtoAbsenceReasons
    ) throws Exception {
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sAbsenceReasons.updateAbsenceReason(absenceReasonId,dtoAbsenceReasons));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
