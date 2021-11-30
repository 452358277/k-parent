package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class InveGroupMemberVO extends BaseVO {
    /** null */
    private String pkId;

    /** 主键ID */
    private String groupId;

    /** 投资人ID */
    private String investorId;

    /** 投资人名称 */
    private String investorName;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
}
