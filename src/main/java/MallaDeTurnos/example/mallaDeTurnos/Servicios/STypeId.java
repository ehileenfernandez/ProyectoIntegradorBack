package MallaDeTurnos.example.mallaDeTurnos.Servicios;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOTypeId;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.TypeIdMapper;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MTypeId;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.ITypeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class STypeId {
    @Autowired
    ITypeId iTypeId;
    @Autowired
    TypeIdMapper typeIdMapper;
    //Constructor


    public STypeId(ITypeId iTypeId, TypeIdMapper typeIdMapper) {
        this.iTypeId = iTypeId;
        this.typeIdMapper = typeIdMapper;
    }

    //Obtener todos los tipos de identificación
    public List<MTypeId> getTypesId () throws Exception{
        try{
            return iTypeId.findAll();
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Obtener todos los tipos de identificación activos
    public List<MTypeId> getActiveTypesId() throws Exception{
        try{
            return iTypeId.findByStatus(true);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Agregar nueva tipo de identificación
    public MTypeId addNewTypeId(DTOTypeId dtoTypeId) throws Exception{
        try{
            MTypeId mTypeId = typeIdMapper.toModel(dtoTypeId);
            return iTypeId.save(mTypeId);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
    //Actualizar tipo de identificación
    public MTypeId updateTypeId(Integer typeIdId,DTOTypeId dtoTypeId) throws Exception{
        try{
            MTypeId mTypeId = typeIdMapper.toModel(dtoTypeId);
            Optional<MTypeId> findTypeId = iTypeId.findById(typeIdId);
            if(findTypeId.isPresent()){
                MTypeId typeIdFound = findTypeId.get();
                //Actualizar datos
                typeIdFound.setTypeIdName(mTypeId.getTypeIdName());
                typeIdFound.setStatus(mTypeId.getStatus());
                //Guardar registro
                return iTypeId.save(typeIdFound);
            } else throw new Exception("No se encuentra el tipo de identificación");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
}
