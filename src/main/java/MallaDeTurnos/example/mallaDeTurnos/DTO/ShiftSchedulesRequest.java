package MallaDeTurnos.example.mallaDeTurnos.DTO;

import java.time.LocalDate;
import java.util.List;

public class ShiftSchedulesRequest {
    List<Integer> usersIds;
    LocalDate mondayDate;
    LocalDate sundayDate;

    public LocalDate getMondayDate() {
        return mondayDate;
    }

    public LocalDate getSundayDate() {
        return sundayDate;
    }

    public List<Integer> getUsersIds() {
        return usersIds;
    }
}
