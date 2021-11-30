package com.ppmg.ei.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.annotation.JSONField;
import com.ppmg.ei.model.FundProGovernmentGQRJModel;
import com.ppmg.ei.model.FundProGovernmentGQSJModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 子基金信息列表导出
 *
 * @author zhaokuiyu
 * @return
 * @date 2019/12/30 14:45
 * @creed: The best time to plant a tree is ten years ago, followed by now
 */
@Data
public class FundProGovernmentReportRowModel extends BaseRowModel implements Serializable {

    @ExcelProperty(index = 1, value = {"序号","序号"})
    private Double rownum;
    @ExcelProperty(index = 2, value = {"所属母基金","所属母基金"})
    private String motherFundName;
    @ExcelProperty(index = 3, value = {"参股子基金名称","参股子基金名称"})
    private String fundName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @ExcelProperty(index = 4, value = {"注册日期","注册日期"},format = "yyyy-MM-dd")
    private Date setupDt;

    @ExcelProperty(index = 5, value = {"认缴出资总额","认缴出资总额"})
    private Double sumPlanAmount;

    @ExcelProperty(index = 6, value = {"母基金认缴出资金额","母基金认缴出资金额"})
    private Double sumIntendedAmount;

    @ExcelProperty(index = 7, value = {"实际出资总额","实际出资总额"})
    private Double sumPaidAmount;

    @ExcelProperty(index = 8, value = {"母基金实际出资额","母基金实际出资额"})
    private Double sumOccurAmount;

    @ExcelProperty(index = 9, value = {"认缴金额","实际出资金额"})
    private String sumPlanAmountDTName;

    @ExcelProperty(value = {"股权性质","中央财政"},index = 10)
    private Double sumPlanAmountDt4zycz;

    @ExcelProperty( value = {"股权性质","国家基金"},index = 11)
    private Double sumPlanAmountDt4gjjj;

    @ExcelProperty(index = 12, value = {"股权性质","省级财政"})
    private Double sumPlanAmountDt4sjcz;

    @ExcelProperty(index = 13, value = {"股权性质","省级基金"})
    private Double sumPlanAmountDt4sjjj;

    @ExcelProperty(index = 14, value = {"股权性质","市县财政"})
    private Double sumPlanAmountDt4sxcz;

    @ExcelProperty(index = 15, value = {"股权性质","国有企业"})
    private Double sumPlanAmountDt4gyqy;

    @ExcelProperty(index = 16, value = {"股权性质","国有金融机构"})
    private Double sumPlanAmountDt4gyjrjg;

    @ExcelProperty(index = 17, value = {"股权性质","民营企业"})
    private Double sumPlanAmountDt4myqy;

    @ExcelProperty(index = 18, value = {"股权性质","自然人"})
    private Double sumPlanAmountDt4zrr;

    @ExcelProperty(index = 19, value = {"管理费","管理费"})
    private Double sumManageFee;

    @ExcelProperty(index = 20, value = {"资金托管费","资金托管费"})
    private Double sumCollocationFee;

    @ExcelProperty(index = 21, value = {"其他费用","其他费用"})
    private Double sumOtherFee;

    @ExcelProperty(index = 22, value = {"利息收入","利息收入"})
    private Double sumInterestIncome;

    @ExcelProperty(index = 23, value = {"投资收益","投资收益"})
    private Double sumInvestIncome;

    @ExcelProperty(index = 24, value = {"其他收入","其他收入"})
    private Double sumOtherIncome;

    @ExcelProperty(index = 25, value = {"结余","结余"})
    private Double sumSurplus;

    //@ExcelProperty(index = 26, value = {"母基金认缴比例","母基金认缴比例"})
    //private Double sumManageFeeBL;

    @ExcelProperty(index = 27, value = {"母基金所占部分对应结余","母基金所占部分对应结余"})
    private Double motherFundJY;

    /* @ExcelProperty(index = 28, value = {"子基金直投企业数","子基金直投企业数"})
     private String projNum;

   /*@ExcelProperty(index = 29, value = {"子基金直接投资额","子基金直接投资额"})
     private String projInvest;

     @ExcelProperty(index = 30, value = {"专项基金投资企业数","专项基金投资企业数"})
     private String projNumDt9;

     @ExcelProperty(index = 31, value = {"子基金投专项基金金额","子基金投专项基金金额"})
     private String projInvestDt10;
 */
    @ExcelProperty(index = 32, value = {"投资企业项目数","投资企业项目数"})
    private Double sumManageFeeProjCount;

    @ExcelProperty(index = 33, value = {"投资额","投资额"})
    private Double sumManageFeeMoney;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @ExcelProperty(index = 34, value = {"退出时间","退出时间"},format = "yyyy-MM-dd")
    private Date exitDt;

    @ExcelProperty(index = 35, value = {"退出方式","退出方式"})
    private String exitTypeDT11;

    @ExcelProperty(index = 36, value = {"退出本金","退出本金"})
    private Double sumOccurAmount1;

    @ExcelProperty(index = 37, value = {"退出收益","退出收益"})
    private Double sumOccurAmount2;


    public Double getRownum() {
        return rownum;
    }

    public void setRownum(Double rownum) {
        this.rownum = rownum;
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

    public String getSumPlanAmountDTName() {
        return sumPlanAmountDTName;
    }

    public void setSumPlanAmountDTName(String sumPlanAmountDTName) {
        this.sumPlanAmountDTName = sumPlanAmountDTName;
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


    public Double getMotherFundJY() {
        return motherFundJY;
    }

    public void setMotherFundJY(Double motherFundJY) {
        this.motherFundJY = motherFundJY;
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

    public String getExitTypeDT11() {
        return exitTypeDT11;
    }

    public void setExitTypeDT11(String exitTypeDT11) {
        this.exitTypeDT11 = exitTypeDT11;
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
}
