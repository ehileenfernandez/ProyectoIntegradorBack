package MallaDeTurnos.example.mallaDeTurnos.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DTOShiftChanges {
    Integer shiftChangeId;
    Integer shiftId;
    String originalShift;
    String updatedShift;
    Integer approveId;
    Integer approverId;
    Integer campaignId;
    Integer userId;
    LocalDate shiftDate;
    LocalDateTime updatedAt;

    public DTOShiftChanges() {
    }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Integer getApproveId() {
        return approveId;
    }

    public void setApproveId(Integer approveId) {
        this.approveId = approveId;
    }

    public Integer getApproverId() {
        return approverId;
    }

    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public String getOriginalShift() {
        return originalShift;
    }

    public void setOriginalShift(String originalShift) {
        this.originalShift = originalShift;
    }

    public Integer getShiftChangeId() {
        return shiftChangeId;
    }

    public void setShiftChangeId(Integer shiftChangeId) {
        this.shiftChangeId = shiftChangeId;
    }

    public LocalDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public String getUpdatedShift() {
        return updatedShift;
    }

    public void setUpdatedShift(String updatedShift) {
        this.updatedShift = updatedShift;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
