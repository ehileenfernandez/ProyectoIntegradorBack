    package MallaDeTurnos.example.mallaDeTurnos.Controlador;

    import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOTypeId;
    import MallaDeTurnos.example.mallaDeTurnos.Servicios.STypeId;
    import MallaDeTurnos.example.mallaDeTurnos.Utilidades.ResponseEntityResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/type_id")

    //Origen
    @CrossOrigin("*")
    public class CTypeId extends ResponseEntityResponse {
        @Autowired
        STypeId sTypeId;

        //Obtener todos los tipos de identificación
        @GetMapping
        public ResponseEntity<?>getTypeId() throws Exception{
            try{
                return responseEntityResponse(HttpStatus.OK,sTypeId.getTypesId());
            } catch (Exception error) {
                return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
            }
        }
        //Obtener todos los tipos de identificación activos
        @GetMapping("/active_type_id")
        public ResponseEntity<?>getActiveTypesId () throws Exception{
            try{
                return responseEntityResponse(HttpStatus.OK,sTypeId.getActiveTypesId());
            } catch (Exception error) {
                return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
            }
        }
        //Agregar nueva identificación
        @PostMapping
        public ResponseEntity<?>addTypeId(@RequestBody DTOTypeId dtoTypeId) throws Exception{
            try{
                return responseEntityResponse(HttpStatus.CREATED,sTypeId.addNewTypeId(dtoTypeId));
            } catch (Exception error) {
                return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
            }
        }
        //Actualizar type Id
        @PutMapping("/{idTypeId}")
        public ResponseEntity<?>updateTypeId(@PathVariable Integer idTypeId,
                @RequestBody DTOTypeId dtoTypeId)throws Exception{
            try {
                return responseEntityResponse(HttpStatus.OK,sTypeId.updateTypeId(idTypeId, dtoTypeId));
            } catch (Exception error) {
                return responseEntityResponse(HttpStatus.BAD_REQUEST,error.getMessage());
            }
        }
    }
