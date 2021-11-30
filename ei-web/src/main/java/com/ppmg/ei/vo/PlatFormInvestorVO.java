package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;


public class PlatFormInvestorVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String id;

    /** 平台ID */
    private String platId;

    /** 投资人ID */
    private String investorId;

    /** 投资人姓名 */
	private String investorName;

	/** 占比 */
	private String ratio;

    /** 出资金额 */
    private String investAmountDisplay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlatId() {
        return platId;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getInvestAmountDisplay() {
        return investAmountDisplay;
    }

    public void setInvestAmountDisplay(String investAmountDisplay) {
        this.investAmountDisplay = investAmountDisplay;
    }
}