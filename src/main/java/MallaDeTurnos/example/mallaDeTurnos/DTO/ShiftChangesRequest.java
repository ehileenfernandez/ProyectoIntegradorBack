package MallaDeTurnos.example.mallaDeTurnos.DTO;

import java.time.LocalDate;

public class ShiftChangesRequest {
    Integer campaignId;
    LocalDate mondayDate;
    LocalDate sundayDate;

    public Integer getCampaignId() {
        return campaignId;
    }

    public LocalDate getMondayDate() {
        return mondayDate;
    }

    public LocalDate getSundayDate() {
        return sundayDate;
    }
}
