package MallaDeTurnos.example.mallaDeTurnos.DTO;

public class DTOCampaigns {
    Integer campaignId;
    String campaignName;
    Boolean status;

    public DTOCampaigns() {
    }

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
