package MallaDeTurnos.example.mallaDeTurnos.DTO;

import MallaDeTurnos.example.mallaDeTurnos.Modelo.MShiftSchedules;

import java.util.List;

public class ShiftScheduleResponse {
    Integer userId;
    String shiftStatus;
    List<MShiftSchedules> shifts;

    //Constructor

    public ShiftScheduleResponse(List<MShiftSchedules> shifts, String shiftStatus, Integer userId) {
        this.shifts = shifts;
        this.shiftStatus = shiftStatus;
        this.userId = userId;
    }
    //Encapsulamiento


    public List<MShiftSchedules> getShifts() {
        return shifts;
    }

    public void setShifts(List<MShiftSchedules> shifts) {
        this.shifts = shifts;
    }

    public String getShiftStatus() {
        return shiftStatus;
    }

    public void setShiftStatus(String shiftStatus) {
        this.shiftStatus = shiftStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
