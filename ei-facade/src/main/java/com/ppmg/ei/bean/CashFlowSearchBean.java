package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="现金流",description="现金流列表查询对象searchBean")
public class CashFlowSearchBean extends BaseSearchBean {

    private String keyword;

    private String startTime;

    private String endTime;

    private String financeType;

    private String groupId;

    private String ids;

    private List<String > fundIds;

    private String type;

    private String fundId;

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public List<String> getFundIds() {
        return fundIds;
    }

    public void setFundIds(List<String> fundIds) {
        this.fundIds = fundIds;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }
}
