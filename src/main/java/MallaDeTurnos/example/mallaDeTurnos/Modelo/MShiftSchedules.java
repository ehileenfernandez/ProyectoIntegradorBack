package MallaDeTurnos.example.mallaDeTurnos.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "shift_schedules")
    public class MShiftSchedules {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "shift_id", nullable = false)
    Integer shiftId;
    @Column ( nullable = false)
    LocalDate date;
    @Column (name = "user_id", nullable = false, insertable = false, updatable = false)
    Integer userId;
    @Column (name = "start_shift", nullable = false)
    String startShift;
    @Column (name = "end_shift", nullable = false)
    String endShift;
    @Column (nullable = false)
    String break1;
    @Column (nullable = false)
    String break2;
    @Column (nullable = false)
    String lunch;
    @Column (nullable = false)
    String duration;
    @Column (name = "campaign_id", nullable = false, insertable = false, updatable = false)
    Integer campaignId;
    @Column (name = "team_leader_id", nullable = false, insertable = false, updatable = false)
    Integer teamLeaderId;

    // Relaciones

    //1 usuario, tiene multiples turnos

    @ManyToOne
    @JoinColumn(name = "user_id",
                referencedColumnName = "user_id")
    MUser mUser;

    //1 campaña tiene multiples turnos
    @ManyToOne
    @JoinColumn(name = "campaign_id",
                referencedColumnName = "campaign_id")
    MCampaigns mCampaigns;

    //1 Tl tiene multiples turnos
    @ManyToOne
    @JoinColumn(name = "team_leader_id",
                referencedColumnName = "user_id")
    MUser teamLeader;

    //1 turno puede tener multiples cambios
    @OneToMany(mappedBy = "mShiftSchedules")
    @JsonIgnore
    List<MShiftChanges> mShiftChanges;

    //Constructor


    public MShiftSchedules(String break1, String break2, Integer campaignId, LocalDate date, String duration, String endShift, String lunch, Integer shiftId, String startShift, Integer teamLeaderId, Integer userId) {
        this.break1 = break1;
        this.break2 = break2;
        this.campaignId = campaignId;
        this.date = date;
        this.duration = duration;
        this.endShift = endShift;
        this.lunch = lunch;
        this.shiftId = shiftId;
        this.startShift = startShift;
        this.teamLeaderId = teamLeaderId;
        this.userId = userId;
    }

    public MShiftSchedules() {
    }

    //Encapsulamiento


    public String getBreak1() {
        return break1;
    }

    public void setBreak1(String break1) {
        this.break1 = break1;
    }

    public String getBreak2() {
        return break2;
    }

    public void setBreak2(String break2) {
        this.break2 = break2;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEndShift() {
        return endShift;
    }

    public void setEndShift(String endShift) {
        this.endShift = endShift;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public MCampaigns getmCampaigns() {
        return mCampaigns;
    }

    public void setmCampaigns(MCampaigns mCampaigns) {
        this.mCampaigns = mCampaigns;
    }

    public List<MShiftChanges> getmShiftChanges() {
        return mShiftChanges;
    }

    public void setmShiftChanges(List<MShiftChanges> mShiftChanges) {
        this.mShiftChanges = mShiftChanges;
    }

    public MUser getmUser() {
        return mUser;
    }

    public void setmUser(MUser mUser) {
        this.mUser = mUser;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public String getStartShift() {
        return startShift;
    }

    public void setStartShift(String startShift) {
        this.startShift = startShift;
    }

    public MUser getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(MUser teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Integer getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(Integer teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
