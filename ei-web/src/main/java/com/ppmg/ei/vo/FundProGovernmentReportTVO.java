package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FundProGovernmentBeforYearModel;
import com.ppmg.ei.model.FundProGovernmentNowYearModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 投资企业项目明细
 * @return 
 * @author zhaokuiyu
 * @date 2019/12/4 13:32
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
public class FundProGovernmentReportTVO extends BaseVO implements Serializable {
    //序列
    private Double rownum;
    private Double rownums;
    private String projId;
    //
    private String inveId;
    //出资主体
    private String inveName;
    //
    private String enterpriseId;
    //公司名称
    private String chName;
    /**
     * 公司成立时间
     * @return
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date setupDt;
    //公司注册地
    private  String regAdd;
    //注册资本
    private Double regAmount;
    //所属行业
    private String industry;
    //是否拥有董事席位
    private String isDirectorCnt;
    //是否拥有监事席位
    private String isSupervisorCnt;
    //企业阶段
    private String phase;
    //是否省内企业
    private String isProvince;
    /*@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date investDate;*/

    private String preInvestYear;
    private String nowYear;
    //退出时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date exitDt;
    //退出本金
    private String sumOccurAmount1;
    //退出收益
    private String sumOccurAmount2;
    //dt2
    //出资时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date investDateDt2;
    private String preInvestYearDt2;
    private String nowYearDt2;
    //dt3
    private String CorporateFinance;

    private String enteId;

    private String isPreInvest;
    //职工总人数
    private Double staffNum;
    //科技研发人数
    private Double rdStaffNum;
    //总资产
    private Double totalAssets;
    //总负债
    private Double totalLiabilities;
    //所有者权益
    private Double ownerEquity;
    //总收入
    private Double totalIncome;
    //利润总额
    private Double totalProfit;
    //净利润
    private Double netProfit;
    //研发费用
    private Double rdFee;
    //上缴税费
    private Double payFee;
    //dt4
    //实际投资额
    private String intendedAmount;
    //占股比例(%)
    private Double perShare;
    //属于企业第几轮投资
    private String inveRounds;
    //项目投前估值
    private Double preMoney;
    //dt5
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date exitDtDt5;
    //退出方式=dt5.select(EXIT_TYPE,false,proj_id=dt1.proj_id and EXIT_DT=dt1.EXIT_DT)
    private String exitTypeDt5;
    //dt6
    private String fundId;
    //承诺出资额
    private Double totalPlanAmountv;
    //占基金总认缴比例(%)((实际投资额/承诺出资额)*100)
    private Double fundPerShareZB;
    //dt7
    /*private String enteId7;
    private String isPreInvest7;
    //职工总人数
    private Double staffNum7;
    //科技研发人数
    private Double rdStaffNum7;
    //总资产
    private Double totalAssets7;
    //总负债
    private Double totalLiabilities7;
    //所有者权益
    private Double ownerEquity7;
    //总收入
    private Double totalIncome7;
    //利润总额
    private Double totalProfit7;
    //净利润
    private Double netProfit7;
    //研发费用
    private Double rdFee7;
    //上缴税费
    private Double payFee7;*/

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

    public String getCorporateFinance() {
        return CorporateFinance;
    }

    public void setCorporateFinance(String corporateFinance) {
        CorporateFinance = corporateFinance;
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

    public String getExitTypeDt5() {
        return exitTypeDt5;
    }

    public void setExitTypeDt5(String exitTypeDt5) {
        this.exitTypeDt5 = exitTypeDt5;
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

    public Double getFundPerShareZB() {
        return fundPerShareZB;
    }

    public void setFundPerShareZB(Double fundPerShareZB) {
        this.fundPerShareZB = fundPerShareZB;
    }
}
