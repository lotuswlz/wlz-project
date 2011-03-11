package com.cathywu.report.bean;

import java.util.Date;

public class CampaignLaunchTimeBean {

    private long campaignId;
    private Date beginDate;
    private Date endDate;
    public long getCampaignId() {
        return campaignId;
    }
    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }
    public Date getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public CampaignLaunchTimeBean(long campaignId, Date beginDate, Date endDate) {
        this.campaignId = campaignId;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
}
