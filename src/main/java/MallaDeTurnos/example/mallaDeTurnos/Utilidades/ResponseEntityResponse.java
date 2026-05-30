package MallaDeTurnos.example.mallaDeTurnos.Utilidades;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityResponse {
    //Retornar ResponseEntity respuesta
    public ResponseEntity<?>responseEntityResponse(HttpStatus status, Object body){
        return ResponseEntity
                .status(status)
                .body(body);
    }
}
