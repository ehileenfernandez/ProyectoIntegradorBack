package MallaDeTurnos.example.mallaDeTurnos.DTO;

import java.time.LocalDate;

public class DTOShiftSchedules {
    //Atributos
    Integer shiftId;
    LocalDate date;
    Integer userId;
    String startShift;
    String endShift;
    String break1;
    String break2;
    String lunch;
    String duration;
    Integer campaignId;
    Integer teamLeaderId;

    public DTOShiftSchedules() {
    }

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
