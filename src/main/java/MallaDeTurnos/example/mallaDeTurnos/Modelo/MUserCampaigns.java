package MallaDeTurnos.example.mallaDeTurnos.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "user_campaigns")
public class MUserCampaigns {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_campaign_id", nullable = false)
    Integer userCampaignId;
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    Integer userId;
    @Column(name = "campaign_id", nullable = false, insertable = false, updatable = false)
    Integer campaignId;

    //Relaciones

    //Varias camapañas pueden estar relacionadas a 1 user
    @ManyToOne
    @JoinColumn(name = "user_id",
                referencedColumnName = "user_id")
    MUser mUser;

    @ManyToOne
    @JoinColumn(name = "campaign_id",
                referencedColumnName = "campaign_id")
    MCampaigns mCampaigns;

    //Constructor


    public MUserCampaigns(Integer campaignId, Integer userCampaignId, Integer userId) {
        this.campaignId = campaignId;
        this.userCampaignId = userCampaignId;
        this.userId = userId;
    }

    public MUserCampaigns() {
    }
    //Encapsulamiento

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public MCampaigns getmCampaigns() {
        return mCampaigns;
    }

    public void setmCampaigns(MCampaigns mCampaigns) {
        this.mCampaigns = mCampaigns;
    }

    public MUser getmUser() {
        return mUser;
    }

    public void setmUser(MUser mUser) {
        this.mUser = mUser;
    }

    public Integer getUserCampaignId() {
        return userCampaignId;
    }

    public void setUserCampaignId(Integer userCampaignId) {
        this.userCampaignId = userCampaignId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
