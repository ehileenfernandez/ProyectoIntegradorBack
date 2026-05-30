package MallaDeTurnos.example.mallaDeTurnos.Controlador;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTORoles;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MRoles;
import MallaDeTurnos.example.mallaDeTurnos.Servicios.SRoles;
import MallaDeTurnos.example.mallaDeTurnos.Utilidades.ResponseEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
//Origen
@CrossOrigin("*")
public class CRoles extends ResponseEntityResponse {
    @Autowired
    SRoles sRoles;

    //Obtener todos los roles
    @GetMapping
    public ResponseEntity<?>getRoles() throws Exception{
        try{
            return responseEntityResponse(HttpStatus.OK,sRoles.getRoles());
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }

    //Obtener rol
    @GetMapping("{rolId}")
    public ResponseEntity<?> getRolById(@PathVariable Integer rolId) throws Exception{
        try {
            return responseEntityResponse(HttpStatus.OK,sRoles.getRoleById(rolId));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }

    //Crear nuevo rol
    @PostMapping
    public ResponseEntity<?>newRole(@RequestBody DTORoles dtoRoles) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.CREATED,sRoles.addNewRole(dtoRoles));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
    //Actualizar rol
    @PutMapping("/{roleId}")
    public ResponseEntity<?>updateRole(
            @PathVariable Integer roleId,
            @RequestBody DTORoles dtoRoles
    ) throws Exception{
        try{
            return responseEntityResponse(HttpStatus.OK,sRoles.updateRole(roleId,dtoRoles));
        } catch (Exception error) {
            return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
        }
    }
}
