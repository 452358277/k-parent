package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FundProGovernmentGQRJModel;
import com.ppmg.ei.model.FundProGovernmentGQSJModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * 子基金信息
 * @return 
 * @author zhaokuiyu
 * @date 2019/12/3 13:32
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
public class FundProGovernmentReportVO extends BaseVO implements Serializable {

    //序号
    private Double rownum;
    private Double rownums;
    //ok主键id
    private String fundId;
    //ok所属母基金
    private String motherFundName;
    //ok参股子基金名称
    private String fundName;
    //ok注册日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date setupDt;

    //ok认缴出资总额;=if(dt2.select(SUM_PLAN_AMOUNT,false,fund_id=dt1.fund_id)==null,0,dt2.select(SUM_PLAN_AMOUNT,false,fund_id=dt1.fund_id))
    private Double sumPlanAmount;
    //ok母基金认缴出资金额;=if(dt5.select(sum_intended_amount,false,fund_id=dt1.fund_id)==null,0,dt5.select(sum_intended_amount,false,fund_id=dt1.fund_id))
    private Double sumIntendedAmount;
    //ok实际出资总额;=if(dt2.select(SUM_PAID_AMOUNT,false,fund_id=dt1.fund_id)==null,0,dt2.select(SUM_PAID_AMOUNT,false,fund_id=dt1.fund_id))
    private Double sumPaidAmount;
    //ok母基金实际出资额;=if(dt6.select(sum_occur_amount,false,fund_id=dt1.fund_id)==null,0,dt6.select(sum_occur_amount,false,fund_id=dt1.fund_id))
    private Double sumOccurAmount;
    //认缴金额
    private String sumPlanAmountDTName;
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

    //实际出资金额
    //中央财政;investorPro=1
    //private Double sumPlanAmountDt4zycz2;
    //国家基金;investorPro=2
    //private Double sumPlanAmountDt4gjjj2;
    //省级财政;investorPro=3
    //private Double sumPlanAmountDt4sjcz2;
    //省级基金;investorPro=4
   // private Double sumPlanAmountDt4sjjj2;
    //市县财政;investorPro=5
    //private Double sumPlanAmountDt4sxcz2;
    //国有企业;investorPro=6
    //private Double sumPlanAmountDt4gyqy2;
    //国有金融机构;investorPro=7
    //private Double sumPlanAmountDt4gyjrjg2;
    //民营企业;investorPro=8
    //private Double sumPlanAmountDt4myqy2;
    //自然人;investorPro=9
    //private Double sumPlanAmountDt4zrr2;

    //ok管理费;=dt3.select(SUM_MANAGE_FEE,false,FUND_ID=dt1.FUND_ID)
    private Double sumManageFee;
    //ok资金托管费;=dt3.select(SUM_COLLOCATION_FEE,false,FUND_ID=dt1.FUND_ID)
    private Double sumCollocationFee;
    //ok其他费用;=dt3.select(SUM_OTHER_FEE,false,FUND_ID=dt1.FUND_ID)
    private Double sumOtherFee;
    //ok利息收入;=dt3.select(SUM_INTEREST_INCOME,false,FUND_ID=dt1.FUND_ID)
    private Double sumInterestIncome;
    //ok投资收益;=dt3.select(SUM_INVEST_INCOME,false,FUND_ID=dt1.FUND_ID)
    private Double sumInvestIncome;
    //ok其他收入;=dt3.select(SUM_OTHER_INCOME,false,FUND_ID=dt1.FUND_ID)
    private Double sumOtherIncome;
    //ok结余;=dt3.select(SUM_SURPLUS,false,FUND_ID=dt1.FUND_ID)
    private Double sumSurplus;

    //母基金认缴比例;=if(E4==0,null,round(F4/E4,2));ROUND() 是四舍五入函数,ROUND（对像数，小数位数）
    //母基金认缴出资金额/认缴出资总额(sumIntendedAmount/sumPlanAmount)
    private Double sumManageFeeBL;
    //母基金所占部分对应结余;=if(Y4!=null && Z4!=null,round(Y4*Z4,2),0)
    //结余*母基金认缴比例(sumSurplus*sumManageFeeBL)
    private Double motherFundJY;

    //ok子基金直投企业数;=dt8.select(PROJ_NUM,false,INVE_ID=dt1.FUND_ID)
    private String projNum;
    //ok子基金直接投资额;=dt8.select(PROJ_INVEST,false,INVE_ID=dt1.FUND_ID)
    private String projInvest;
    //ok专项基金投资企业数;=dt9.select(PROJ_NUM,false,MOTHER_FUND_ID=dt1.FUND_ID)
    private String projNumDt9;
    //ok子基金投专项基金金额==dt10.select(PROJ_INVEST,false,FUND_ID=dt1.FUND_ID)
    private String projInvestDt10;

    //投资企业项目数;=AB4{}+AD4{}
    //子基金直投企业数+专项基金投资企业数
    private Double sumManageFeeProjCount;
    //投资额;=AC4{}+AE4{}
    //子基金直接投资额+子基金投专项基金金额
    private Double sumManageFeeMoney;

    //ok退出时间dt1.select(EXIT_DT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date exitDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date exitDtDt11 ;

    //退出方式;dt11.select(EXIT_TYPE,false,proj_id=dt1.proj_id and EXIT_DT=dt1.EXIT_DT)
    //private String exitType;//不用
    private String exitTypeDT11;//用这个

    //ok退出本金;=dt1.select(SUM_OCCUR_AMOUNT1)
    private Double sumOccurAmount1;
    //ok退出收益;=dt1.select(SUM_OCCUR_AMOUNT2)
    private Double sumOccurAmount2;

    //认缴金额
    //private FundProGovernmentGQRJModel fundProGovernmentGQRJModel;
    //实际出资金额
    //private FundProGovernmentGQSJModel fundProGovernmentGQSJModel;

   public String getSumPlanAmountDTName() {
       return sumPlanAmountDTName;
   }

    public void setSumPlanAmountDTName(String sumPlanAmountDTName) {
        this.sumPlanAmountDTName = sumPlanAmountDTName;
    }
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

    public Double getSumPlanAmount() {
        return sumPlanAmount;
    }

    public void setSumPlanAmount(Double sumPlanAmount) {
        this.sumPlanAmount = sumPlanAmount;
    }

    public Double getSumIntendedAmount() {
        return sumIntendedAmount;
    }

    public void setSumIntendedAmount(Double sumIntendedAmount) {
        this.sumIntendedAmount = sumIntendedAmount;
    }

    public Double getSumPaidAmount() {
        return sumPaidAmount;
    }

    public void setSumPaidAmount(Double sumPaidAmount) {
        this.sumPaidAmount = sumPaidAmount;
    }

    public Double getSumOccurAmount() {
        return sumOccurAmount;
    }

    public void setSumOccurAmount(Double sumOccurAmount) {
        this.sumOccurAmount = sumOccurAmount;
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

    public Double getSumManageFeeBL() {
        return sumManageFeeBL;
    }

    public void setSumManageFeeBL(Double sumManageFeeBL) {
        this.sumManageFeeBL = sumManageFeeBL;
    }

    public Double getMotherFundJY() {
        return motherFundJY;
    }

    public void setMotherFundJY(Double motherFundJY) {
        this.motherFundJY = motherFundJY;
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

    public Double getSumManageFeeProjCount() {
        return sumManageFeeProjCount;
    }

    public void setSumManageFeeProjCount(Double sumManageFeeProjCount) {
        this.sumManageFeeProjCount = sumManageFeeProjCount;
    }

    public Double getSumManageFeeMoney() {
        return sumManageFeeMoney;
    }

    public void setSumManageFeeMoney(Double sumManageFeeMoney) {
        this.sumManageFeeMoney = sumManageFeeMoney;
    }

    public Date getExitDt() {
        return exitDt;
    }

    public void setExitDt(Date exitDt) {
        this.exitDt = exitDt;
    }

    public Date getExitDtDt11() {
        return exitDtDt11;
    }

    public void setExitDtDt11(Date exitDtDt11) {
        this.exitDtDt11 = exitDtDt11;
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

    public String getExitTypeDT11() {
        return exitTypeDT11;
    }

    public void setExitTypeDT11(String exitTypeDT11) {
        this.exitTypeDT11 = exitTypeDT11;
    }
}
