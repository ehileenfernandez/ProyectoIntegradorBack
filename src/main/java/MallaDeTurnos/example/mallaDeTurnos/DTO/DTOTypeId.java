package MallaDeTurnos.example.mallaDeTurnos.DTO;

public class DTOTypeId {
    Integer idTypeId;
    String typeIdName;
    Boolean status;

    public DTOTypeId() {
    }

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
