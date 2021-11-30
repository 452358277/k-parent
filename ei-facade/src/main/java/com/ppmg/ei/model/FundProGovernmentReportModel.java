package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * 子基金信息
 * @return
 * @author zhaokuiyu
 * @date 2019/12/3 11:55
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
public class FundProGovernmentReportModel extends BaseModel implements Serializable {
    //dt1
    private Double rownum;
    private Double rownums;
    //主键id
    private String fundId;
    //子基金首投的母基金名称
    private String motherFundName;
    //基金名称
    private String fundName;
    //成立日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date setupDt;
    //
    private String projId;
    //XJL_T_FP_LEDGER_MAG；LEDGER_TYPE='6'
    private Double sumOccurAmount1;
    //XJL_T_FP_LEDGER_MAG；LEDGER_TYPE='7'
    private Double sumOccurAmount2;
    //发生时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date exitDt;
    //dt2
    //TZ_T_PROJ_OWNERSHIP_INFO；主键id
    private String osId;
    //TZ_T_PROJ_SHARE_INFO；PLAN_AMOUNT；承诺出资额
    private Double sumPlanAmount;
    //TZ_T_PROJ_SHARE_INFO；PAID_AMOUNT；累计出资总额
    private Double sumPaidAmount;
    //dt3
    private String financeId;
    //GF_T_FUND_FINANCE；MANAGE_FEE；本季度管理费
    private Double sumManageFee;
    //GF_T_FUND_FINANCE；COLLOCATION_FEE；本季度资金托管费用
    private Double sumCollocationFee;
    //GF_T_FUND_FINANCE；OTHER_FEE；本季度其他费用
    private Double sumOtherFee;
    //GF_T_FUND_FINANCE；INTEREST_INCOME；本季度利息收入
    private Double sumInterestIncome;
    //GF_T_FUND_FINANCE；INVEST_INCOME；本季度投资收益
    private Double sumInvestIncome;
    //GF_T_FUND_FINANCE；OTHER_INCOME；本季度其他收益
    private Double sumOtherIncome;
    //GF_T_FUND_FINANCE；SURPLUS；本季度结余
    private Double sumSurplus;
    //dt4
    //TZ_T_PROJ_OWNERSHIP_INFO;os_id;股权结构信息主键
    private String osIdDt4;
    //TZ_T_PROJ_SHARE_INFO;investor_pro;股东属性
    private String investorPro;
    //中央财政;investorPro=1
    private Double sumPlanAmountDt4zycz;
    //国家基金;investorPro=2
    private Double sumPlanAmountDt4gjjj;
    //省级财政;investorPro=3
    private Double sumPlanAmountDt4sjcz;
    //省级基金;investorPro=4
    private Double sumPlanAmountDt4sjjj;
    //市县财政;investorPro=5
    private Double sumPlanAmountDt4sxcz;
    //国有企业;investorPro=6
    private Double sumPlanAmountDt4gyqy;
    //国有金融机构;investorPro=7
    private Double sumPlanAmountDt4gyjrjg;
    //民营企业;investorPro=8
    private Double sumPlanAmountDt4myqy;
    //自然人;investorPro=9

    private Double sumPlanAmountDt4zrr;
    //中央财政;investorPro=1
    private Double sumPlanAmountDt4zycz2;
    //国家基金;investorPro=2
    private Double sumPlanAmountDt4gjjj2;
    //省级财政;investorPro=3
    private Double sumPlanAmountDt4sjcz2;
    //省级基金;investorPro=4
    private Double sumPlanAmountDt4sjjj2;
    //市县财政;investorPro=5
    private Double sumPlanAmountDt4sxcz2;
    //国有企业;investorPro=6
    private Double sumPlanAmountDt4gyqy2;
    //国有金融机构;investorPro=7
    private Double sumPlanAmountDt4gyjrjg2;
    //民营企业;investorPro=8
    private Double sumPlanAmountDt4myqy2;
    //自然人;investorPro=9
    private Double sumPlanAmountDt4zrr2;
    //认缴金额
    private FundProGovernmentGQRJModel fundProGovernmentGQRJModel;
    //实际出资金额
    private FundProGovernmentGQSJModel fundProGovernmentGQSJModel;

    //TZ_T_PROJ_SHARE_INFO;plan_amount;承诺出资额
    private Double sumPlanAmountDt4;
    //TZ_T_PROJ_SHARE_INFO;paid_amount;累计出资总额
    private Double sumPaidAmountDt4;
    //dt5
    //TZ_T_PROJ_APP_INFO;INTENDED_AMOUNT:拟投资金额
    private Double sumIntendedAmount;
    //dt6
    //XJL_T_FP_LEDGER_MAG:OCCUR_AMOUNT:发生金额
    private Double sumOccurAmount;
    //dt7
    private String investor_pro;
    private String sum_plan_amount;
    private String sum_paid_amount;
    //dt8
    private String inveId;
    //
    private String projNum;
    //
    private String projInvest;
    //dt9
    private String motherFundId;
    //
    private String projNumDt9;
    //dt10
    private String projInvestDt10;
    //dt11
    //XJL_T_FP_LEDGER_MAG;occur_dt;发生时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date exitDtDt11;
    //XJL_T_FP_LEDGER_MAG
    private String projIdDt11;
    //
    private String exitType;

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

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getMotherFundName() {
        return motherFundName;
    }

    public void setMotherFundName(String motherFundName) {
        this.motherFundName = motherFundName;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Date getSetupDt() {
        return setupDt;
    }

    public void setSetupDt(Date setupDt) {
        this.setupDt = setupDt;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public Double getSumOccurAmount1() {
        return sumOccurAmount1;
    }

    public void setSumOccurAmount1(Double sumOccurAmount1) {
        this.sumOccurAmount1 = sumOccurAmount1;
    }

    public Double getSumOccurAmount2() {
        return sumOccurAmount2;
    }

    public void setSumOccurAmount2(Double sumOccurAmount2) {
        this.sumOccurAmount2 = sumOccurAmount2;
    }

    public Date getExitDt() {
        return exitDt;
    }

    public void setExitDt(Date exitDt) {
        this.exitDt = exitDt;
    }

    public String getOsId() {
        return osId;
    }

    public void setOsId(String osId) {
        this.osId = osId;
    }

    public Double getSumPlanAmount() {
        return sumPlanAmount;
    }

    public void setSumPlanAmount(Double sumPlanAmount) {
        this.sumPlanAmount = sumPlanAmount;
    }

    public Double getSumPaidAmount() {
        return sumPaidAmount;
    }

    public void setSumPaidAmount(Double sumPaidAmount) {
        this.sumPaidAmount = sumPaidAmount;
    }

    public String getFinanceId() {
        return financeId;
    }

    public void setFinanceId(String financeId) {
        this.financeId = financeId;
    }

    public Double getSumManageFee() {
        return sumManageFee;
    }

    public void setSumManageFee(Double sumManageFee) {
        this.sumManageFee = sumManageFee;
    }

    public Double getSumCollocationFee() {
        return sumCollocationFee;
    }

    public void setSumCollocationFee(Double sumCollocationFee) {
        this.sumCollocationFee = sumCollocationFee;
    }

    public Double getSumOtherFee() {
        return sumOtherFee;
    }

    public void setSumOtherFee(Double sumOtherFee) {
        this.sumOtherFee = sumOtherFee;
    }

    public Double getSumInterestIncome() {
        return sumInterestIncome;
    }

    public void setSumInterestIncome(Double sumInterestIncome) {
        this.sumInterestIncome = sumInterestIncome;
    }

    public Double getSumInvestIncome() {
        return sumInvestIncome;
    }

    public void setSumInvestIncome(Double sumInvestIncome) {
        this.sumInvestIncome = sumInvestIncome;
    }

    public Double getSumOtherIncome() {
        return sumOtherIncome;
    }

    public void setSumOtherIncome(Double sumOtherIncome) {
        this.sumOtherIncome = sumOtherIncome;
    }

    public Double getSumSurplus() {
        return sumSurplus;
    }

    public void setSumSurplus(Double sumSurplus) {
        this.sumSurplus = sumSurplus;
    }

    public String getOsIdDt4() {
        return osIdDt4;
    }

    public void setOsIdDt4(String osIdDt4) {
        this.osIdDt4 = osIdDt4;
    }

    public String getInvestorPro() {
        return investorPro;
    }

    public void setInvestorPro(String investorPro) {
        this.investorPro = investorPro;
    }

    public Double getSumPlanAmountDt4zycz() {
        return sumPlanAmountDt4zycz;
    }

    public void setSumPlanAmountDt4zycz(Double sumPlanAmountDt4zycz) {
        this.sumPlanAmountDt4zycz = sumPlanAmountDt4zycz;
    }

    public Double getSumPlanAmountDt4gjjj() {
        return sumPlanAmountDt4gjjj;
    }

    public void setSumPlanAmountDt4gjjj(Double sumPlanAmountDt4gjjj) {
        this.sumPlanAmountDt4gjjj = sumPlanAmountDt4gjjj;
    }

    public Double getSumPlanAmountDt4sjcz() {
        return sumPlanAmountDt4sjcz;
    }

    public void setSumPlanAmountDt4sjcz(Double sumPlanAmountDt4sjcz) {
        this.sumPlanAmountDt4sjcz = sumPlanAmountDt4sjcz;
    }

    public Double getSumPlanAmountDt4sjjj() {
        return sumPlanAmountDt4sjjj;
    }

    public void setSumPlanAmountDt4sjjj(Double sumPlanAmountDt4sjjj) {
        this.sumPlanAmountDt4sjjj = sumPlanAmountDt4sjjj;
    }

    public Double getSumPlanAmountDt4sxcz() {
        return sumPlanAmountDt4sxcz;
    }

    public void setSumPlanAmountDt4sxcz(Double sumPlanAmountDt4sxcz) {
        this.sumPlanAmountDt4sxcz = sumPlanAmountDt4sxcz;
    }

    public Double getSumPlanAmountDt4gyqy() {
        return sumPlanAmountDt4gyqy;
    }

    public void setSumPlanAmountDt4gyqy(Double sumPlanAmountDt4gyqy) {
        this.sumPlanAmountDt4gyqy = sumPlanAmountDt4gyqy;
    }

    public Double getSumPlanAmountDt4gyjrjg() {
        return sumPlanAmountDt4gyjrjg;
    }

    public void setSumPlanAmountDt4gyjrjg(Double sumPlanAmountDt4gyjrjg) {
        this.sumPlanAmountDt4gyjrjg = sumPlanAmountDt4gyjrjg;
    }

    public Double getSumPlanAmountDt4myqy() {
        return sumPlanAmountDt4myqy;
    }

    public void setSumPlanAmountDt4myqy(Double sumPlanAmountDt4myqy) {
        this.sumPlanAmountDt4myqy = sumPlanAmountDt4myqy;
    }

    public Double getSumPlanAmountDt4zrr() {
        return sumPlanAmountDt4zrr;
    }

    public void setSumPlanAmountDt4zrr(Double sumPlanAmountDt4zrr) {
        this.sumPlanAmountDt4zrr = sumPlanAmountDt4zrr;
    }

    public Double getSumPlanAmountDt4zycz2() {
        return sumPlanAmountDt4zycz2;
    }

    public void setSumPlanAmountDt4zycz2(Double sumPlanAmountDt4zycz2) {
        this.sumPlanAmountDt4zycz2 = sumPlanAmountDt4zycz2;
    }

    public Double getSumPlanAmountDt4gjjj2() {
        return sumPlanAmountDt4gjjj2;
    }

    public void setSumPlanAmountDt4gjjj2(Double sumPlanAmountDt4gjjj2) {
        this.sumPlanAmountDt4gjjj2 = sumPlanAmountDt4gjjj2;
    }

    public Double getSumPlanAmountDt4sjcz2() {
        return sumPlanAmountDt4sjcz2;
    }

    public void setSumPlanAmountDt4sjcz2(Double sumPlanAmountDt4sjcz2) {
        this.sumPlanAmountDt4sjcz2 = sumPlanAmountDt4sjcz2;
    }

    public Double getSumPlanAmountDt4sjjj2() {
        return sumPlanAmountDt4sjjj2;
    }

    public void setSumPlanAmountDt4sjjj2(Double sumPlanAmountDt4sjjj2) {
        this.sumPlanAmountDt4sjjj2 = sumPlanAmountDt4sjjj2;
    }

    public Double getSumPlanAmountDt4sxcz2() {
        return sumPlanAmountDt4sxcz2;
    }

    public void setSumPlanAmountDt4sxcz2(Double sumPlanAmountDt4sxcz2) {
        this.sumPlanAmountDt4sxcz2 = sumPlanAmountDt4sxcz2;
    }

    public Double getSumPlanAmountDt4gyqy2() {
        return sumPlanAmountDt4gyqy2;
    }

    public void setSumPlanAmountDt4gyqy2(Double sumPlanAmountDt4gyqy2) {
        this.sumPlanAmountDt4gyqy2 = sumPlanAmountDt4gyqy2;
    }

    public Double getSumPlanAmountDt4gyjrjg2() {
        return sumPlanAmountDt4gyjrjg2;
    }

    public void setSumPlanAmountDt4gyjrjg2(Double sumPlanAmountDt4gyjrjg2) {
        this.sumPlanAmountDt4gyjrjg2 = sumPlanAmountDt4gyjrjg2;
    }

    public Double getSumPlanAmountDt4myqy2() {
        return sumPlanAmountDt4myqy2;
    }

    public void setSumPlanAmountDt4myqy2(Double sumPlanAmountDt4myqy2) {
        this.sumPlanAmountDt4myqy2 = sumPlanAmountDt4myqy2;
    }

    public Double getSumPlanAmountDt4zrr2() {
        return sumPlanAmountDt4zrr2;
    }

    public void setSumPlanAmountDt4zrr2(Double sumPlanAmountDt4zrr2) {
        this.sumPlanAmountDt4zrr2 = sumPlanAmountDt4zrr2;
    }

    public Double getSumPlanAmountDt4() {
        return sumPlanAmountDt4;
    }

    public void setSumPlanAmountDt4(Double sumPlanAmountDt4) {
        this.sumPlanAmountDt4 = sumPlanAmountDt4;
    }

    public Double getSumPaidAmountDt4() {
        return sumPaidAmountDt4;
    }

    public void setSumPaidAmountDt4(Double sumPaidAmountDt4) {
        this.sumPaidAmountDt4 = sumPaidAmountDt4;
    }

    public Double getSumIntendedAmount() {
        return sumIntendedAmount;
    }

    public void setSumIntendedAmount(Double sumIntendedAmount) {
        this.sumIntendedAmount = sumIntendedAmount;
    }

    public Double getSumOccurAmount() {
        return sumOccurAmount;
    }

    public void setSumOccurAmount(Double sumOccurAmount) {
        this.sumOccurAmount = sumOccurAmount;
    }

    public String getInvestor_pro() {
        return investor_pro;
    }

    public void setInvestor_pro(String investor_pro) {
        this.investor_pro = investor_pro;
    }

    public String getSum_plan_amount() {
        return sum_plan_amount;
    }

    public void setSum_plan_amount(String sum_plan_amount) {
        this.sum_plan_amount = sum_plan_amount;
    }

    public String getSum_paid_amount() {
        return sum_paid_amount;
    }

    public void setSum_paid_amount(String sum_paid_amount) {
        this.sum_paid_amount = sum_paid_amount;
    }

    public String getInveId() {
        return inveId;
    }

    public void setInveId(String inveId) {
        this.inveId = inveId;
    }

    public String getProjNum() {
        return projNum;
    }

    public void setProjNum(String projNum) {
        this.projNum = projNum;
    }

    public String getProjInvest() {
        return projInvest;
    }

    public void setProjInvest(String projInvest) {
        this.projInvest = projInvest;
    }

    public String getMotherFundId() {
        return motherFundId;
    }

    public void setMotherFundId(String motherFundId) {
        this.motherFundId = motherFundId;
    }

    public String getProjNumDt9() {
        return projNumDt9;
    }

    public void setProjNumDt9(String projNumDt9) {
        this.projNumDt9 = projNumDt9;
    }

    public String getProjInvestDt10() {
        return projInvestDt10;
    }

    public void setProjInvestDt10(String projInvestDt10) {
        this.projInvestDt10 = projInvestDt10;
    }

    public Date getExitDtDt11() {
        return exitDtDt11;
    }

    public void setExitDtDt11(Date exitDtDt11) {
        this.exitDtDt11 = exitDtDt11;
    }

    public String getProjIdDt11() {
        return projIdDt11;
    }

    public void setProjIdDt11(String projIdDt11) {
        this.projIdDt11 = projIdDt11;
    }

    public String getExitType() {
        return exitType;
    }

    public void setExitType(String exitType) {
        this.exitType = exitType;
    }

    public FundProGovernmentGQRJModel getFundProGovernmentGQRJModel() {
        return fundProGovernmentGQRJModel;
    }

    public void setFundProGovernmentGQRJModel(FundProGovernmentGQRJModel fundProGovernmentGQRJModel) {
        this.fundProGovernmentGQRJModel = fundProGovernmentGQRJModel;
    }

    public FundProGovernmentGQSJModel getFundProGovernmentGQSJModel() {
        return fundProGovernmentGQSJModel;
    }

    public void setFundProGovernmentGQSJModel(FundProGovernmentGQSJModel fundProGovernmentGQSJModel) {
        this.fundProGovernmentGQSJModel = fundProGovernmentGQSJModel;
    }
}
