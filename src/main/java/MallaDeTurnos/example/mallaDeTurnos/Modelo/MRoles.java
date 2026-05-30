package MallaDeTurnos.example.mallaDeTurnos.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class MRoles {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "role_id", nullable = false)
    Integer roleId;
    @Column (name = "role_name", length = 80 ,nullable = false)
    String roleName;
    @Column(name = "add_shift", nullable = false)
    Boolean addShift;
    @Column(name = "add_late_shift", nullable = false)
    Boolean addLateShift;
    @Column(name = "add_changes", nullable = false)
    Boolean addChanges;
    @Column(name = "add_full_changes", nullable = false)
    Boolean addFullChanges;

    // Relaciones
    //cada usuario tiene 1 rol, pero varios usuarios pueden tener el msimo rol
    @OneToMany(mappedBy = "mRoles")
    @JsonIgnore
    List<MUser> mUser;


    // Constructor


    public MRoles(Boolean addChanges, Boolean addLateShift, Boolean addFullChanges, Boolean addShift, Integer roleId, String roleName) {
        this.addChanges = addChanges;
        this.addLateShift = addLateShift;
        this.addFullChanges = addFullChanges;
        this.addShift = addShift;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public MRoles() {
    }

    //Encapsulamiento


    public Boolean getAddChanges() {
        return addChanges;
    }

    public void setAddChanges(Boolean addChanges) {
        this.addChanges = addChanges;
    }

    public Boolean getAddFullChanges() {
        return addFullChanges;
    }

    public void setAddFullChanges(Boolean addFullChanges) {
        this.addFullChanges = addFullChanges;
    }

    public Boolean getAddLateShift() {
        return addLateShift;
    }

    public void setAddLateShift(Boolean addLateShift) {
        this.addLateShift = addLateShift;
    }

    public Boolean getAddShift() {
        return addShift;
    }

    public void setAddShift(Boolean addShift) {
        this.addShift = addShift;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}


