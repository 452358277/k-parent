package com.ppmg.ei.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 投资企业项目明细,导出模板
 *
 * @author zhaokuiyu
 * @return
 * @date 2019/12/30 13:32
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
@Data
public class FundProGovernmentReportRowTVO extends BaseRowModel implements Serializable {
    //@ExcelProperty(index = 1, value = {"序号","序号"})
    //private String inveId;

    @ExcelProperty(index = 0, value = {"基本情况", "出资主体"})
    private String inveName;

    @ExcelProperty(index = 1, value = {"基本情况", "公司名称"})
    private String chName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @ExcelProperty(index = 2, value = {"基本情况", "公司成立时间"},format = "yyyy-MM-dd")
    private Date setupDt;

    @ExcelProperty(index = 3, value = {"基本情况", "公司注册地"})
    private String regAdd;

    @ExcelProperty(index = 4, value = {"基本情况", "注册资本"})
    private Double regAmount;

    @ExcelProperty(index = 5, value = {"基本情况", "实际投资额"})
    private String intendedAmount;

    @ExcelProperty(index = 6, value = {"基本情况", "占股比例(%)"})
    private Double perShare;

    //@ExcelProperty(index = 8, value = {"基本情况", "承诺出资额"})
    //private Double totalPlanAmountv;

    @ExcelProperty(index = 7, value = {"基本情况", "占基金总认缴比例(%)"})
    private Double fundPerShareZB;

    @ExcelProperty(index = 8, value = {"基本情况", "属于企业第几轮投资"})
    private String inveRounds;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @ExcelProperty(index = 9, value = {"基本情况", "出资时间"},format = "yyyy-MM-dd")
    private Date investDateDt2;

    @ExcelProperty(index = 10, value = {"基本情况", "所属行业"})
    private String industry;

    @ExcelProperty(index = 11, value = {"基本情况", "是否拥有董事席位"})
    private String isDirectorCnt;

    @ExcelProperty(index = 12, value = {"基本情况", "是否拥有监事席位"})
    private String isSupervisorCnt;

    @ExcelProperty(index = 13, value = {"基本情况", "项目投前估值"})
    private Double preMoney;

    @ExcelProperty(index = 14, value = {"基本情况", "企业阶段"})
    private String phase;

    @ExcelProperty(index = 15, value = {"基本情况", "是否省内企业"})
    private String isProvince;

    @ExcelProperty(index = 16, value = {"", ""})
    private String corporateFinance;

    @ExcelProperty(index = 17, value = {"企业财务情况", "职工总人数"})
    private Double staffNum;

    @ExcelProperty(index = 18, value = {"企业财务情况", "科技研发人数"})
    private Double rdStaffNum;

    @ExcelProperty(index = 19, value = {"企业财务情况", "总资产"})
    private Double totalAssets;

    @ExcelProperty(index = 20, value = {"企业财务情况", "总负债"})
    private Double totalLiabilities;

    @ExcelProperty(index = 21, value = {"企业财务情况", "所有者权益"})
    private Double ownerEquity;

    @ExcelProperty(index = 22, value = {"企业财务情况", "总收入"})
    private Double totalIncome;

    @ExcelProperty(index = 23, value = {"企业财务情况", "利润总额"})
    private Double totalProfit;

    @ExcelProperty(index = 24, value = {"企业财务情况", "净利润"})
    private Double netProfit;

    @ExcelProperty(index = 25, value = {"企业财务情况", "研发费用"})
    private Double rdFee;

    @ExcelProperty(index = 26, value = {"企业财务情况", "上缴税费"})
    private Double payFee;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @ExcelProperty(index = 27, value = {"退出情况", "退出时间"},format = "yyyy-MM-dd")
    private Date exitDt;

    @ExcelProperty(index = 28, value = {"退出情况", "退出方式"})
    private String exitTypeDt5;

    @ExcelProperty(index = 29, value = {"退出情况", "退出本金"})
    private String sumOccurAmount1;

    @ExcelProperty(index = 30, value = {"退出情况", "退出收益"})
    private String sumOccurAmount2;

    public String getInveName() {
        return inveName;
    }

    public void setInveName(String inveName) {
        this.inveName = inveName;
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


    public Double getFundPerShareZB() {
        return fundPerShareZB;
    }

    public void setFundPerShareZB(Double fundPerShareZB) {
        this.fundPerShareZB = fundPerShareZB;
    }

    public String getInveRounds() {
        return inveRounds;
    }

    public void setInveRounds(String inveRounds) {
        this.inveRounds = inveRounds;
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

    public Double getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(Double preMoney) {
        this.preMoney = preMoney;
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

    public String getCorporateFinance() {
        return corporateFinance;
    }

    public void setCorporateFinance(String corporateFinance) {
        this.corporateFinance = corporateFinance;
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

    public Date getExitDt() {
        return exitDt;
    }

    public void setExitDt(Date exitDt) {
        this.exitDt = exitDt;
    }

    public String getExitTypeDt5() {
        return exitTypeDt5;
    }

    public void setExitTypeDt5(String exitTypeDt5) {
        this.exitTypeDt5 = exitTypeDt5;
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
}
