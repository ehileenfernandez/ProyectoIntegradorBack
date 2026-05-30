package MallaDeTurnos.example.mallaDeTurnos.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "type_id")
public class MTypeId {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_type_id", nullable = false)
    Integer idTypeId;
    @Column (name = "type_id_name", length = 80 ,nullable = false)
    String typeIdName;
    @Column( nullable = false)
    Boolean status;

    // Relaciones

    // Varios usuarios pueden tener un tipo de ID
    @OneToMany(mappedBy = "mTypeId")
    @JsonIgnore
    List<MUser> mUser;

    //Constructor


    public MTypeId(Integer idTypeId, Boolean status, String typeIdName) {
        this.idTypeId = idTypeId;
        this.status = status;
        this.typeIdName = typeIdName;
    }

    public MTypeId() {
    }

    // Encapsulamiento


    public Integer getIdTypeId() {
        return idTypeId;
    }

    public void setIdTypeId(Integer idTypeId) {
        this.idTypeId = idTypeId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTypeIdName() {
        return typeIdName;
    }

    public void setTypeIdName(String typeIdName) {
        this.typeIdName = typeIdName;
    }
}

