package MallaDeTurnos.example.mallaDeTurnos.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "absence_reasons")
public class MAbsenceReasons {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "absence_id", nullable = false)
    Integer absenceId;
    @Column ( name = "absence_name", length = 80 ,nullable = false)
    String absenceName;
    @Column( nullable = false)
    Boolean status;

    // Relaciones

    //constructor


    public MAbsenceReasons(Integer absenceId, String absenceName, Boolean status) {
        this.absenceId = absenceId;
        this.absenceName = absenceName;
        this.status = status;
    }

    public MAbsenceReasons() {
    }

    //encapsular


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

