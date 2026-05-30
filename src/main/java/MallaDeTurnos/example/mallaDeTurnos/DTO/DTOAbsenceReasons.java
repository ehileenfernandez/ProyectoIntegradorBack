package MallaDeTurnos.example.mallaDeTurnos.DTO;

public class DTOAbsenceReasons {
    Integer absenceId;
    String absenceName;
    Boolean status;

    public DTOAbsenceReasons() {
    }

    public Integer getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(Integer absenceId) {
        this.absenceId = absenceId;
    }

    public String getAbsenceName() {
        return absenceName;
    }

    public void setAbsenceName(String absenceName) {
        this.absenceName = absenceName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
