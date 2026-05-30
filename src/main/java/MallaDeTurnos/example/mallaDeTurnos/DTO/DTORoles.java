package MallaDeTurnos.example.mallaDeTurnos.DTO;

public class DTORoles {
    Integer roleId;
    String roleName;
    Boolean addShift;
    Boolean addLateShift;
    Boolean addChanges;
    Boolean addFullChanges;

    public DTORoles() {
    }

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
