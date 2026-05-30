package MallaDeTurnos.example.mallaDeTurnos.Controlador;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOUserCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Servicios.SUserCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Utilidades.ResponseEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_campaigns")

//Origen
@CrossOrigin("*")
public class CUserCampaigns extends ResponseEntityResponse {
    @Autowired
    SUserCampaigns sUserCampaigns;

    //Agregar una nueva relación de usuario
    @PostMapping
    public ResponseEntity<?>addUserCampaign(@RequestBody List<DTOUserCampaigns> dtoUserCampaigns) throws Exception{
        try {
            return responseEntityResponse(HttpStatus.CREATED,sUserCampaigns.addUserCampaign(dtoUserCampaigns));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Actualizar registros
    @PutMapping
    public ResponseEntity<?>updateUserCampaigns(@RequestBody List<DTOUserCampaigns> dtoUserCampaigns) throws Exception{
        try {
            return responseEntityResponse(HttpStatus.OK,sUserCampaigns.updateUserCampaigns(dtoUserCampaigns));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Eliminar Registros
    @DeleteMapping
    public ResponseEntity<?>deleteUserCampaigns(@RequestBody List<Integer> userCampaignsIds) throws Exception{
        try {
            return responseEntityResponse(HttpStatus.OK,sUserCampaigns.deleteUserCampaigns(userCampaignsIds));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }

    //Obtener usuarios asignados a una campaña
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserCampaigns(@PathVariable Integer userId) throws Exception {
        try {
            return responseEntityResponse(HttpStatus.OK,
                    sUserCampaigns.getUserCampaigns(userId));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST, error.getMessage());
        }
    }
}
