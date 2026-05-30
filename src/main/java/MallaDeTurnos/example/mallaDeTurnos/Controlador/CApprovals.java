package MallaDeTurnos.example.mallaDeTurnos.Controlador;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOApprovals;
import MallaDeTurnos.example.mallaDeTurnos.Servicios.SApprovals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/approvals")

//Origen
@CrossOrigin(origins = "*")
public class CApprovals {
    @Autowired
    SApprovals sApprovals;

    //Response error
    public ResponseEntity<?>responseEntityError(Exception error){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error.getMessage());
    }

    //Obtener todos los tipos de approvals
    @GetMapping
    public ResponseEntity<?> gettApprovals () throws Exception{
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(sApprovals.getApprovals());
        } catch (Exception error) {
            return responseEntityError(error);
        }
    }
    //Crear nuevo approval
    @PostMapping
    public ResponseEntity<?> newApproval(@RequestBody DTOApprovals dtoApprovals) throws Exception{
        try{
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(sApprovals.addNewApproval(dtoApprovals));
        } catch (Exception error) {
            return responseEntityError(error);
        }
    }
    //Actualizar approval
    @PutMapping("/{approveId}")
    public ResponseEntity<?> updateApproval (
            @PathVariable Integer approveId,
            @RequestBody DTOApprovals dtoApprovals
    ) throws Exception{
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(sApprovals.updateApproval(approveId,dtoApprovals));
        } catch (Exception error) {
            return responseEntityError(error);
        }
    }
}
