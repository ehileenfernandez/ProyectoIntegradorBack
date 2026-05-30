package MallaDeTurnos.example.mallaDeTurnos.Controlador;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOCampaigns;
import MallaDeTurnos.example.mallaDeTurnos.Servicios.SCampaigns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")

//Origen
@CrossOrigin("*")
public class CCampaigns {
    @Autowired
    SCampaigns sCampaigns;

    /// Return de los ResponseEntity
    public ResponseEntity<?> responseEntityResponse(HttpStatus status, Object body){
        return ResponseEntity
                .status(status)
                .body(body);
    }
    //Obtener todas las campañas
    @GetMapping
    public ResponseEntity<?> getCampaigns() throws Exception{
        try{
            return responseEntityResponse(HttpStatus.OK,sCampaigns.getCampaigns());
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST, error.getMessage());
        }
    }

    //Obtener campañas asignadas a 1 usuario
    @PostMapping("/user_campaigns")
    public ResponseEntity<?>getUserCampaings(@RequestBody List<Integer> campaignsIds) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.OK,sCampaigns.getCampaingsById(campaignsIds));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }

    //Obtener información de la campaña
    @GetMapping("/{campaignId}")
    public ResponseEntity<?>getCampaignInfo(@PathVariable Integer campaignId) throws Exception{
        try {
            return responseEntityResponse(HttpStatus.OK,sCampaigns.getCampaignInfo(campaignId));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    
    //crear nueva campaña
    @PostMapping
    public ResponseEntity<?>newCampaign(@RequestBody DTOCampaigns dtoCampaigns) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.CREATED,sCampaigns.addNewCampaign(dtoCampaigns));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Actualizar campaña
    @PutMapping("/{campaignId}")
    public ResponseEntity<?>updateCampaign(
            @PathVariable Integer campaignId,
            @RequestBody DTOCampaigns dtoCampaigns
    ) throws Exception {
        try{
            return responseEntityResponse(HttpStatus.OK,sCampaigns.updateCampaign(campaignId,dtoCampaigns));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
}
