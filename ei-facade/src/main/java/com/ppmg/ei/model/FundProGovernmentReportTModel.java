package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @投资企业项目明细
 * @return 
 * @author zhaokuiyu
 * @date 2019/12/4 11:55
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
public class FundProGovernmentReportTModel extends BaseModel implements Serializable {
    //dt1
    //序列
    private Double rownum;
    private Double rownums;
    private String projId;
    //
    private String inveId;
    //
    private String inveName;
    //
    private String enterpriseId;
    //
    private String chName;
    //
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date setupDt;
    //注册地
    private  String regAdd;
    //注册资本
    private Double regAmount;
    private String industry;
    private String isDirectorCnt;
    private String isSupervisorCnt;
    private String phase;
    private String isProvince;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date investDate;
    private String preInvestYear;
    private String nowYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date exitDt;
    //退出本金
    private String sumOccurAmount1;
    //退出收益
    private String sumOccurAmount2;
    //dt2
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date investDateDt2;

    private String preInvestYearDt2;

    private String nowYearDt2;
    //dt3
    private String enteId;
    private String isPreInvest;
    private Double staffNum;
    private Double rdStaffNum;
    private Double totalAssets;
    private Double totalLiabilities;
    private Double ownerEquity;
    private Double totalIncome;
    private Double totalProfit;
    private Double netProfit;
    private Double rdFee;
    private Double payFee;

    //dt4
    private String intendedAmount;
    private Double perShare;
    private String inveRounds;
    private Double preMoney;
    //dt5
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date exitDtDt5;
    private String exitType;
    //dt6
    private String fundId;
    private Double totalPlanAmountv;
    //dt7
    private String enteId7;
    private String isPreInvest7;
    private Double staffNum7;
    private Double rdStaffNum7;
    private Double totalAssets7;
    private Double totalLiabilities7;
    private Double ownerEquity7;
    private Double totalIncome7;
    private Double totalProfit7;
    private Double netProfit7;
    private Double rdFee7;
    private Double payFee7;

    private FundProGovernmentBeforYearModel fundProGovernmentBeforYearModel;

    private FundProGovernmentNowYearModel fundProGovernmentNowYearModel;

    public Double getRownum() {
        return rownum;
    }

    public void setRownum(Double rownum) {
        this.rownum = rownum;
    }

    public Double getRownums() {
        return rownums;
    }

    public void setRownums(Double rownums) {
        this.rownums = rownums;
    }

    public FundProGovernmentBeforYearModel getFundProGovernmentBeforYearModel() {
        return fundProGovernmentBeforYearModel;
    }

    public void setFundProGovernmentBeforYearModel(FundProGovernmentBeforYearModel fundProGovernmentBeforYearModel) {
        this.fundProGovernmentBeforYearModel = fundProGovernmentBeforYearModel;
    }

    public FundProGovernmentNowYearModel getFundProGovernmentNowYearModel() {
        return fundProGovernmentNowYearModel;
    }

    public void setFundProGovernmentNowYearModel(FundProGovernmentNowYearModel fundProGovernmentNowYearModel) {
        this.fundProGovernmentNowYearModel = fundProGovernmentNowYearModel;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getInveId() {
        return inveId;
    }

    public void setInveId(String inveId) {
        this.inveId = inveId;
    }

    public String getInveName() {
        return inveName;
    }

    public void setInveName(String inveName) {
        this.inveName = inveName;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public Date getSetupDt() {
        return setupDt;
    }

    public void setSetupDt(Date setupDt) {
        this.setupDt = setupDt;
    }

    public String getRegAdd() {
        return regAdd;
    }

    public void setRegAdd(String regAdd) {
        this.regAdd = regAdd;
    }

    public Double getRegAmount() {
        return regAmount;
    }

    public void setRegAmount(Double regAmount) {
        this.regAmount = regAmount;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIsDirectorCnt() {
        return isDirectorCnt;
    }

    public void setIsDirectorCnt(String isDirectorCnt) {
        this.isDirectorCnt = isDirectorCnt;
    }

    public String getIsSupervisorCnt() {
        return isSupervisorCnt;
    }

    public void setIsSupervisorCnt(String isSupervisorCnt) {
        this.isSupervisorCnt = isSupervisorCnt;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getIsProvince() {
        return isProvince;
    }

    public void setIsProvince(String isProvince) {
        this.isProvince = isProvince;
    }

    public Date getInvestDate() {
        return investDate;
    }

    public void setInvestDate(Date investDate) {
        this.investDate = investDate;
    }

    public String getPreInvestYear() {
        return preInvestYear;
    }

    public void setPreInvestYear(String preInvestYear) {
        this.preInvestYear = preInvestYear;
    }

    public String getNowYear() {
        return nowYear;
    }

    public void setNowYear(String nowYear) {
        this.nowYear = nowYear;
    }

    public Date getExitDt() {
        return exitDt;
    }

    public void setExitDt(Date exitDt) {
        this.exitDt = exitDt;
    }

    public String getSumOccurAmount1() {
        return sumOccurAmount1;
    }

    public void setSumOccurAmount1(String sumOccurAmount1) {
        this.sumOccurAmount1 = sumOccurAmount1;
    }

    public String getSumOccurAmount2() {
        return sumOccurAmount2;
    }

    public void setSumOccurAmount2(String sumOccurAmount2) {
        this.sumOccurAmount2 = sumOccurAmount2;
    }

    public Date getInvestDateDt2() {
        return investDateDt2;
    }

    public void setInvestDateDt2(Date investDateDt2) {
        this.investDateDt2 = investDateDt2;
    }


    public String getPreInvestYearDt2() {
        return preInvestYearDt2;
    }

    public void setPreInvestYearDt2(String preInvestYearDt2) {
        this.preInvestYearDt2 = preInvestYearDt2;
    }

    public String getNowYearDt2() {
        return nowYearDt2;
    }

    public void setNowYearDt2(String nowYearDt2) {
        this.nowYearDt2 = nowYearDt2;
    }

    public String getEnteId() {
        return enteId;
    }

    public void setEnteId(String enteId) {
        this.enteId = enteId;
    }

    public String getIsPreInvest() {
        return isPreInvest;
    }

    public void setIsPreInvest(String isPreInvest) {
        this.isPreInvest = isPreInvest;
    }

    public Double getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(Double staffNum) {
        this.staffNum = staffNum;
    }

    public Double getRdStaffNum() {
        return rdStaffNum;
    }

    public void setRdStaffNum(Double rdStaffNum) {
        this.rdStaffNum = rdStaffNum;
    }

    public Double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Double getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(Double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public Double getOwnerEquity() {
        return ownerEquity;
    }

    public void setOwnerEquity(Double ownerEquity) {
        this.ownerEquity = ownerEquity;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

    public Double getRdFee() {
        return rdFee;
    }

    public void setRdFee(Double rdFee) {
        this.rdFee = rdFee;
    }

    public Double getPayFee() {
        return payFee;
    }

    public void setPayFee(Double payFee) {
        this.payFee = payFee;
    }

    public String getIntendedAmount() {
        return intendedAmount;
    }

    public void setIntendedAmount(String intendedAmount) {
        this.intendedAmount = intendedAmount;
    }

    public Double getPerShare() {
        return perShare;
    }

    public void setPerShare(Double perShare) {
        this.perShare = perShare;
    }

    public String getInveRounds() {
        return inveRounds;
    }

    public void setInveRounds(String inveRounds) {
        this.inveRounds = inveRounds;
    }

    public Double getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(Double preMoney) {
        this.preMoney = preMoney;
    }

    public Date getExitDtDt5() {
        return exitDtDt5;
    }

    public void setExitDtDt5(Date exitDtDt5) {
        this.exitDtDt5 = exitDtDt5;
    }

    public String getExitType() {
        return exitType;
    }

    public void setExitType(String exitType) {
        this.exitType = exitType;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public Double getTotalPlanAmountv() {
        return totalPlanAmountv;
    }

    public void setTotalPlanAmountv(Double totalPlanAmountv) {
        this.totalPlanAmountv = totalPlanAmountv;
    }

    public String getEnteId7() {
        return enteId7;
    }

    public void setEnteId7(String enteId7) {
        this.enteId7 = enteId7;
    }

    public String getIsPreInvest7() {
        return isPreInvest7;
    }

    public void setIsPreInvest7(String isPreInvest7) {
        this.isPreInvest7 = isPreInvest7;
    }

    public Double getStaffNum7() {
        return staffNum7;
    }

    public void setStaffNum7(Double staffNum7) {
        this.staffNum7 = staffNum7;
    }

    public Double getRdStaffNum7() {
        return rdStaffNum7;
    }

    public void setRdStaffNum7(Double rdStaffNum7) {
        this.rdStaffNum7 = rdStaffNum7;
    }

    public Double getTotalAssets7() {
        return totalAssets7;
    }

    public void setTotalAssets7(Double totalAssets7) {
        this.totalAssets7 = totalAssets7;
    }

    public Double getTotalLiabilities7() {
        return totalLiabilities7;
    }

    public void setTotalLiabilities7(Double totalLiabilities7) {
        this.totalLiabilities7 = totalLiabilities7;
    }

    public Double getOwnerEquity7() {
        return ownerEquity7;
    }

    public void setOwnerEquity7(Double ownerEquity7) {
        this.ownerEquity7 = ownerEquity7;
    }

    public Double getTotalIncome7() {
        return totalIncome7;
    }

    public void setTotalIncome7(Double totalIncome7) {
        this.totalIncome7 = totalIncome7;
    }

    public Double getTotalProfit7() {
        return totalProfit7;
    }

    public void setTotalProfit7(Double totalProfit7) {
        this.totalProfit7 = totalProfit7;
    }

    public Double getNetProfit7() {
        return netProfit7;
    }

    public void setNetProfit7(Double netProfit7) {
        this.netProfit7 = netProfit7;
    }

    public Double getRdFee7() {
        return rdFee7;
    }

    public void setRdFee7(Double rdFee7) {
        this.rdFee7 = rdFee7;
    }

    public Double getPayFee7() {
        return payFee7;
    }

    public void setPayFee7(Double payFee7) {
        this.payFee7 = payFee7;
    }
}
