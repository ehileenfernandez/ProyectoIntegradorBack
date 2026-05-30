package MallaDeTurnos.example.mallaDeTurnos.Controlador;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOLoginUser;
import MallaDeTurnos.example.mallaDeTurnos.Servicios.SLoginUser;
import MallaDeTurnos.example.mallaDeTurnos.Utilidades.ResponseEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login_user")

//Origen
@CrossOrigin("*")
public class CLoginUser extends ResponseEntityResponse {
    @Autowired
    SLoginUser sLoginUser;

    //Verificar Login
    @PostMapping
    public ResponseEntity<?>validateLogin(@RequestBody DTOLoginUser dtoLoginUser) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.OK,
                    sLoginUser.userLoginConfirmation(dtoLoginUser.getUserEmail(), dtoLoginUser.getUserPassword()));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Crear nuevo Login
    @PostMapping("/register")
    public ResponseEntity<?>createLogin(@RequestBody DTOLoginUser dtoLoginUser) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.CREATED,sLoginUser.newUserLogin(dtoLoginUser));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Actualizar Login
    @PutMapping("/{userLoginId}")
    public ResponseEntity<?>updateLogin(
            @PathVariable Integer userLoginId,
            @RequestBody DTOLoginUser dtoLoginUser
    ) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.OK,sLoginUser.updateUserLogin(userLoginId,dtoLoginUser));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
}
