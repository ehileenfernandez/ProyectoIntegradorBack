package MallaDeTurnos.example.mallaDeTurnos.Modelo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "campaigns")
public class MCampaigns {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "campaign_id", nullable = false)
    Integer campaignId;
    @Column (name = "campaign_name",length = 80 ,nullable = false)
    String campaignName;
    @Column( nullable = false)
    Boolean status;

    // Relaciones

    //1 campaña puede tener varios usuarios

    @OneToMany(mappedBy = "mCampaigns")
    @JsonIgnore
    List<MUserCampaigns> mUserCampaigns;

    //1 Campaña puede tener varios turnos
    @OneToMany(mappedBy = "mCampaigns")
    @JsonIgnore
    List<MShiftSchedules> mShiftSchedules;
    // 1 campaña puede tener varios cambios de turnos
    @OneToMany(mappedBy = "mCampaigns")
    @JsonIgnore
    List<MShiftChanges> mShiftChanges;
    // Constructor


    public MCampaigns(Integer campaignId, String campaignName, Boolean status) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.status = status;
    }

    public MCampaigns() {
    }

    //Encapsulamiento

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

