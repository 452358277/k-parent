package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DirectProjResult extends BaseModel {

    private String fundId;

    private String projId;
  //项目名称
    private String projName;
  //投资金额
    private String finaAmount;
  //本轮估值
    private String preMoney;
    //最新估值
    private String  postMoney;
  //已拨付金额
    private String  actualPayAmount;
   //是否属于江苏省项目
    private String js;
    //如是，则为省内第几种情形
    private String isTwelve;
//投资时间
    private String tzDate;
//出资主体名称
    private String inveName;
//项目性质（码值1013）
    private String projProperty;

    private String industry;

    private String industryName;

    private String newIndustry;

    private String newIndustryName;

    private String isSpvProj;

    private String isSpvFundName;

    //是否国民经济行业
    private String isIndustry;
    //是否战略性新兴产业
    private String isNewIndustry;

    private String projType;

    private String regName;

    private String fundName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date actualPayDate;

    private Double actualAmount;

    private Double intendedAmount;

    private String projObject;

    private String labelIds;

    private Double  curTmoneyPer;

    private Double  allTmoney;

    private Double  funToPro;

    private Double  allTmoneyPer;

    private String creditCode;

    private String isPublicComp;
   //是否上市
    private String isSs;
   //证券类别
    private String stockCategory;

    //是否完成上市后投资
    private String isAfterListed;
    //产业类别
    private String industryCategory;

    //是否ipo
    private String isIpo;
   //ipo申请时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date applyIpoDt;

   //项目投资时点就业人数
    private String inveEmployment;
  //就业人口
    private String employment;

    //项目投资时点授权专利数
    private String tzEmpowerPatent;
 //度末授权专利数
    private String lastEmpowerPatent;
//项目投资时前一年纳税额
    private String tzLastYearTaxAmount;
 //年度预计纳税额
    private String lastEstimateTaxAmount;
 //年度纳税额
    private String yearTaxAmount;
    //发展“卡脖子技术”数量
    private String isKbzNum;

    //上市板块
    private String listedSector;


    //融资阶段
    private String entePhase;

    //省
    private String addressProvince;
    //市
    private String addressCity;
   //区
    private String addressArea;
    //地址
    private String addressDetails;


    private String isFit;

    private String fitStatus;

    private String sumAmount;

    private Double raiseAmount;

    private Double szfAmount;

    //退还省政府金额
    private Double govQuitAmount;

   //一级分类
    private String oneIndustry;


    private String enteId;


    private String  isExcept;

    private String  projIdSpv;


    private String ctInveAmount;

    public String getCtInveAmount() {
        return ctInveAmount;
    }

    public void setCtInveAmount(String ctInveAmount) {
        this.ctInveAmount = ctInveAmount;
    }

    public String getIsExcept() {
        return isExcept;
    }

    public void setIsExcept(String isExcept) {
        this.isExcept = isExcept;
    }

    public String getProjIdSpv() {
        return projIdSpv;
    }

    public void setProjIdSpv(String projIdSpv) {
        this.projIdSpv = projIdSpv;
    }

    public String getEnteId() {
        return enteId;
    }

    public void setEnteId(String enteId) {
        this.enteId = enteId;
    }

    public String getOneIndustry() {
        return oneIndustry;
    }

    public void setOneIndustry(String oneIndustry) {
        this.oneIndustry = oneIndustry;
    }

    public Double getGovQuitAmount() {
        return govQuitAmount;
    }

    public void setGovQuitAmount(Double govQuitAmount) {
        this.govQuitAmount = govQuitAmount;
    }

    public Double getRaiseAmount() {
        return raiseAmount;
    }

    public void setRaiseAmount(Double raiseAmount) {
        this.raiseAmount = raiseAmount;
    }

    public Double getSzfAmount() {
        return szfAmount;
    }

    public void setSzfAmount(Double szfAmount) {
        this.szfAmount = szfAmount;
    }

    public String getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(String sumAmount) {
        this.sumAmount = sumAmount;
    }

    public String getIsFit() {
        return isFit;
    }

    public void setIsFit(String isFit) {
        this.isFit = isFit;
    }

    public String getFitStatus() {
        return fitStatus;
    }

    public void setFitStatus(String fitStatus) {
        this.fitStatus = fitStatus;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getEntePhase() {
        return entePhase;
    }

    public void setEntePhase(String entePhase) {
        this.entePhase = entePhase;
    }

    public String getListedSector() {
        return listedSector;
    }

    public void setListedSector(String listedSector) {
        this.listedSector = listedSector;
    }

    public String getIsKbzNum() {
        return isKbzNum;
    }

    public void setIsKbzNum(String isKbzNum) {
        this.isKbzNum = isKbzNum;
    }

    public String getInveEmployment() {
        return inveEmployment;
    }

    public void setInveEmployment(String inveEmployment) {
        this.inveEmployment = inveEmployment;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getTzEmpowerPatent() {
        return tzEmpowerPatent;
    }

    public void setTzEmpowerPatent(String tzEmpowerPatent) {
        this.tzEmpowerPatent = tzEmpowerPatent;
    }

    public String getLastEmpowerPatent() {
        return lastEmpowerPatent;
    }

    public void setLastEmpowerPatent(String lastEmpowerPatent) {
        this.lastEmpowerPatent = lastEmpowerPatent;
    }

    public String getTzLastYearTaxAmount() {
        return tzLastYearTaxAmount;
    }

    public void setTzLastYearTaxAmount(String tzLastYearTaxAmount) {
        this.tzLastYearTaxAmount = tzLastYearTaxAmount;
    }

    public String getLastEstimateTaxAmount() {
        return lastEstimateTaxAmount;
    }

    public void setLastEstimateTaxAmount(String lastEstimateTaxAmount) {
        this.lastEstimateTaxAmount = lastEstimateTaxAmount;
    }

    public String getYearTaxAmount() {
        return yearTaxAmount;
    }

    public void setYearTaxAmount(String yearTaxAmount) {
        this.yearTaxAmount = yearTaxAmount;
    }

    public String getIsAfterListed() {
        return isAfterListed;
    }

    public void setIsAfterListed(String isAfterListed) {
        this.isAfterListed = isAfterListed;
    }

    public String getIndustryCategory() {
        return industryCategory;
    }

    public void setIndustryCategory(String industryCategory) {
        this.industryCategory = industryCategory;
    }

    public String getIsIpo() {
        return isIpo;
    }

    public void setIsIpo(String isIpo) {
        this.isIpo = isIpo;
    }

    public Date getApplyIpoDt() {
        return applyIpoDt;
    }

    public void setApplyIpoDt(Date applyIpoDt) {
        this.applyIpoDt = applyIpoDt;
    }

    public String getStockCategory() {
        return stockCategory;
    }

    public void setStockCategory(String stockCategory) {
        this.stockCategory = stockCategory;
    }

    public String getIsSs() {
        return isSs;
    }

    public void setIsSs(String isSs) {
        this.isSs = isSs;
    }

    public String getIsPublicComp() {
        return isPublicComp;
    }

    public void setIsPublicComp(String isPublicComp) {
        this.isPublicComp = isPublicComp;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Double getCurTmoneyPer() {
        return curTmoneyPer;
    }

    public void setCurTmoneyPer(Double curTmoneyPer) {
        this.curTmoneyPer = curTmoneyPer;
    }

    public Double getAllTmoney() {
        return allTmoney;
    }

    public void setAllTmoney(Double allTmoney) {
        this.allTmoney = allTmoney;
    }

    public Double getFunToPro() {
        return funToPro;
    }

    public void setFunToPro(Double funToPro) {
        this.funToPro = funToPro;
    }

    public Double getAllTmoneyPer() {
        return allTmoneyPer;
    }

    public void setAllTmoneyPer(Double allTmoneyPer) {
        this.allTmoneyPer = allTmoneyPer;
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
    }

    public String getProjObject() {
        return projObject;
    }

    public void setProjObject(String projObject) {
        this.projObject = projObject;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Double getIntendedAmount() {
        return intendedAmount;
    }

    public void setIntendedAmount(Double intendedAmount) {
        this.intendedAmount = intendedAmount;
    }

    public Date getActualPayDate() {
        return actualPayDate;
    }

    public void setActualPayDate(Date actualPayDate) {
        this.actualPayDate = actualPayDate;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getProjType() {
        return projType;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getIsIndustry() {
        return isIndustry;
    }

    public void setIsIndustry(String isIndustry) {
        this.isIndustry = isIndustry;
    }

    public String getIsNewIndustry() {
        return isNewIndustry;
    }

    public void setIsNewIndustry(String isNewIndustry) {
        this.isNewIndustry = isNewIndustry;
    }

    public String getIsSpvFundName() {
        return isSpvFundName;
    }

    public void setIsSpvFundName(String isSpvFundName) {
        this.isSpvFundName = isSpvFundName;
    }

    public String getIsSpvProj() {
        return isSpvProj;
    }

    public void setIsSpvProj(String isSpvProj) {
        this.isSpvProj = isSpvProj;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getNewIndustry() {
        return newIndustry;
    }

    public void setNewIndustry(String newIndustry) {
        this.newIndustry = newIndustry;
    }

    public String getNewIndustryName() {
        return newIndustryName;
    }

    public void setNewIndustryName(String newIndustryName) {
        this.newIndustryName = newIndustryName;
    }

    public String getProjProperty() {
        return projProperty;
    }

    public void setProjProperty(String projProperty) {
        this.projProperty = projProperty;
    }

    public String getInveName() {
        return inveName;
    }

    public void setInveName(String inveName) {
        this.inveName = inveName;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getFinaAmount() {
        return finaAmount;
    }

    public void setFinaAmount(String finaAmount) {
        this.finaAmount = finaAmount;
    }

    public String getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(String preMoney) {
        this.preMoney = preMoney;
    }

    public String getPostMoney() {
        return postMoney;
    }

    public void setPostMoney(String postMoney) {
        this.postMoney = postMoney;
    }

    public String getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(String actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    public String getIsTwelve() {
        return isTwelve;
    }

    public void setIsTwelve(String isTwelve) {
        this.isTwelve = isTwelve;
    }

    public String getTzDate() {
        return tzDate;
    }

    public void setTzDate(String tzDate) {
        this.tzDate = tzDate;
    }
}
