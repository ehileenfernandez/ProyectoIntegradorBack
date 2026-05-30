package MallaDeTurnos.example.mallaDeTurnos.DTO;

public class DTOApprovals {
    Integer approveId;
    String approveName;
    Boolean approveStatus;
    Boolean status;

    public DTOApprovals() {
    }

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
