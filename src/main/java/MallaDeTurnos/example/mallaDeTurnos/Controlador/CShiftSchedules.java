package MallaDeTurnos.example.mallaDeTurnos.Controlador;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOShiftSchedules;
import MallaDeTurnos.example.mallaDeTurnos.DTO.ShiftSchedulesRequest;
import MallaDeTurnos.example.mallaDeTurnos.Servicios.SShiftSchedules;
import MallaDeTurnos.example.mallaDeTurnos.Utilidades.ResponseEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift_schedules")

//Origen
@CrossOrigin("*")
public class CShiftSchedules extends ResponseEntityResponse {
    @Autowired
    SShiftSchedules sShiftSchedules;

    //Obtener turnos cargados
    @PostMapping("/get_week_ss")
    public ResponseEntity<?>getShiftSchedules(@RequestBody ShiftSchedulesRequest shiftSchedulesRequest) throws Exception{
       try {
           return responseEntityResponse(HttpStatus.OK,sShiftSchedules.getShiftSchedules(shiftSchedulesRequest.getUsersIds(),
                   shiftSchedulesRequest.getMondayDate(),shiftSchedulesRequest.getSundayDate()));
       } catch (Exception error) {
           return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
       }
   }
   //Cargar nuevos turnos
    @PostMapping
    public ResponseEntity<?>addShiftSchedules(@RequestBody List<DTOShiftSchedules> dtoShiftSchedules) throws Exception{
       try{
           return responseEntityResponse(HttpStatus.CREATED,sShiftSchedules.addNewShiftSchedules(dtoShiftSchedules));
       } catch (Exception error) {
           return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
       }
    }
    //Actualizar turnos
    @PutMapping
    public ResponseEntity<?>updateShiftSchedules(@RequestBody List<DTOShiftSchedules> dtoShiftSchedules) throws Exception{
       try{
           List<Integer> shiftSchedulesIds = dtoShiftSchedules.stream()
                   .map(DTOShiftSchedules::getShiftId)
                   .toList();
           return responseEntityResponse(HttpStatus.OK,sShiftSchedules.updateShiftSchedules(shiftSchedulesIds,dtoShiftSchedules));
       } catch (Exception error) {
           return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
       }
    }
    //Obtener turnos historicos
    @GetMapping("/historical_shifts_schedules")
    public ResponseEntity<?>getHistoricalShiftSchedules(@RequestBody ShiftSchedulesRequest shiftSchedulesRequest) throws Exception{
       try{
           return responseEntityResponse(HttpStatus.OK,sShiftSchedules.getHistoricalShiftSchedules(shiftSchedulesRequest.getUsersIds(),
                   shiftSchedulesRequest.getMondayDate(),shiftSchedulesRequest.getSundayDate()));
       } catch (Exception error) {
           return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
       }
    }
}
