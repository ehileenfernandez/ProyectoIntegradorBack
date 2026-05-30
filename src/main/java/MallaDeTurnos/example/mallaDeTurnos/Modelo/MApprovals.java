package MallaDeTurnos.example.mallaDeTurnos.Modelo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "approvals")
public class MApprovals {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "approve_id",  nullable = false)
    Integer approveId;
    @Column ( name = "approve_name", length = 80 ,nullable = false)
    String approveName;
    @Column(name = "approve_status")
    Boolean approveStatus;
    @Column( nullable = false)
    Boolean status;

    // Relaciones

    //1 approval puede tener multiples cambios
    @OneToMany(mappedBy = "mApprovals")
    @JsonIgnore
    List<MShiftChanges> mShiftChanges;

    //Constructor


    public MApprovals(Integer approveId, String approveName, Boolean approveStatus, Boolean status) {
        this.approveId = approveId;
        this.approveName = approveName;
        this.approveStatus = approveStatus;
        this.status = status;
    }

    public MApprovals() {
    }

    // Encapsulamiento


    public Integer getApproveId() {
        return approveId;
    }

    public void setApproveId(Integer approveId) {
        this.approveId = approveId;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public Boolean getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Boolean approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

