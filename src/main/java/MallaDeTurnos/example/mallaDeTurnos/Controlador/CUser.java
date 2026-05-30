package MallaDeTurnos.example.mallaDeTurnos.Controlador;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOUser;
import MallaDeTurnos.example.mallaDeTurnos.Servicios.SUser;
import MallaDeTurnos.example.mallaDeTurnos.Utilidades.ResponseEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

//Origen
@CrossOrigin("*")
public class CUser extends ResponseEntityResponse {
    @Autowired
    SUser sUser;

    //Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<?>getUsers() throws Exception{
        try {
            return responseEntityResponse(HttpStatus.OK,sUser.getUsers());
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }

    //Retornar lista de usuarios por IDs
    @PostMapping("/get_users_by_ids")
    public ResponseEntity<?>getUsersByIds(@RequestBody List<Integer> usersIds) throws Exception{
        try {
            return responseEntityResponse(HttpStatus.OK,sUser.getUsersByIds(usersIds));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }

    //Obtener equipo de un TL que están activos
    @GetMapping("/active_team/{teamLeaderId}")
    public ResponseEntity<?>getActiveTeam(@PathVariable Integer teamLeaderId) throws Exception{
        try {
            return responseEntityResponse(HttpStatus.OK,sUser.getActiveTeam(teamLeaderId));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Obtener todos los usuarios de un equipo que se le deben cargar malla
    @GetMapping("/team_shift_schedule/{campaignId}")
    public ResponseEntity<?>getTeamShiftSchedule(@PathVariable Integer campaignId) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.OK,sUser.getTeamShiftSchedule(campaignId));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Crear nuevo usuario
    @PostMapping
    public ResponseEntity<?>addUser(@RequestBody DTOUser dtoUser) throws Exception{
        try {
            return responseEntityResponse(HttpStatus.CREATED,sUser.addNewUser(dtoUser));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Actualizar información de usuario
    @PutMapping("/{userId}")
    public ResponseEntity<?>updateUser(@PathVariable Integer userId,@RequestBody DTOUser dtoUser) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.OK,sUser.updateUser(userId,dtoUser));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Retornar 1 usuario
    @GetMapping("/{userId}")
    public ResponseEntity<?>getUserById(@PathVariable Integer userId) throws Exception{
        try {
            return responseEntityResponse(HttpStatus.OK,sUser.getUserById(userId));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }

}
