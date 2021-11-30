package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;

public class ShareInfoSearchBean extends BaseSearchBean {

    private String fundId;

    private Long closeRound;

    private String investorName;

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public Long getCloseRound() {
        return closeRound;
    }

    public void setCloseRound(Long closeRound) {
        this.closeRound = closeRound;
    }
}
