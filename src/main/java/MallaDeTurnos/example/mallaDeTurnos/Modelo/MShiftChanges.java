package MallaDeTurnos.example.mallaDeTurnos.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="shift_changes")
public class MShiftChanges {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "shift_change_id", nullable = false)
    Integer shiftChangeId;
    @Column (name = "shift_id", insertable = false, updatable = false)
    Integer shiftId;
    @Column (name = "original_shift")
    String originalShift;
    @Column (name = "updated_shift", nullable = false)
    String updatedShift;
    @Column (name = "approve_id", insertable = false, updatable = false)
    Integer approveId;
    @Column (name = "approver_id", insertable = false, updatable = false)
    Integer approverId;
    @Column (name = "campaign_id", insertable = false, updatable = false)
    Integer campaignId;
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    Integer userId;
    @Column (name = "shift_date")
    LocalDate shiftDate;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @PrePersist
    void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate(){
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
    }

    // Relaciones

    //1 turno puede tener multiples cambios

    @ManyToOne
    @JoinColumn(name = "shift_id",
                referencedColumnName = "shift_id")
    MShiftSchedules mShiftSchedules;

    // 1 campaña puede estar asignada a multiples cambios
    @ManyToOne
    @JoinColumn(name = "campaign_id",
                referencedColumnName = "campaign_id")
    MCampaigns mCampaigns;
    //1 usuario puede estar asignado a multiples cambios
    @ManyToOne
    @JoinColumn(name = "user_id",
                referencedColumnName = "user_id")
    MUser mUser;

    //1 estado esta asignado a multiples cambios

    @ManyToOne
    @JoinColumn(name = "approve_id",
                referencedColumnName = "approve_id")
    MApprovals mApprovals;

    //1 aprobador esta asignado a multiples cambios
    @ManyToOne
    @JoinColumn(name = "approver_id",
                referencedColumnName = "user_id")
    MUser mApprover;

    //

    //Constructor


    public MShiftChanges(Integer userId, String updatedShift, LocalDateTime updatedAt, Integer shiftId, LocalDate shiftDate, Integer shiftChangeId, String originalShift, LocalDateTime createdAt, Integer campaignId, Integer approverId, Integer approveId) {
        this.userId = userId;
        this.updatedShift = updatedShift;
        this.updatedAt = updatedAt;
        this.shiftId = shiftId;
        this.shiftDate = shiftDate;
        this.shiftChangeId = shiftChangeId;
        this.originalShift = originalShift;
        this.createdAt = createdAt;
        this.campaignId = campaignId;
        this.approverId = approverId;
        this.approveId = approveId;
    }

    public MShiftChanges() {
    }

    // Encapsulamiento

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MApprovals getmApprovals() {
        return mApprovals;
    }

    public void setmApprovals(MApprovals mApprovals) {
        this.mApprovals = mApprovals;
    }

    public MUser getmApprover() {
        return mApprover;
    }

    public void setmApprover(MUser mApprover) {
        this.mApprover = mApprover;
    }

    public MCampaigns getmCampaigns() {
        return mCampaigns;
    }

    public void setmCampaigns(MCampaigns mCampaigns) {
        this.mCampaigns = mCampaigns;
    }

    public MShiftSchedules getmShiftSchedules() {
        return mShiftSchedules;
    }

    public void setmShiftSchedules(MShiftSchedules mShiftSchedules) {
        this.mShiftSchedules = mShiftSchedules;
    }

    public MUser getmUser() {
        return mUser;
    }

    public void setmUser(MUser mUser) {
        this.mUser = mUser;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
