package MallaDeTurnos.example.mallaDeTurnos.DTO;


public class DTOUserCampaigns {
    Integer userCampaignId;
    Integer userId;
    Integer campaignId;

    public DTOUserCampaigns() {
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
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
