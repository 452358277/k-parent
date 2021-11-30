package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DirectProjResultVO extends BaseVO {
    private String projId;
    //项目名称
    private String projName;
    //本轮投资总额
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
//合同签署金额
    private double signAmount;
//放款金额
    private Double sumActualPayAmount;
//项目属性
    private String projProperty;
//中小企业
    private String isZx;
    //高新企业
    private String isGx;

    //一带一路
    private String isYdyl;

    private String isZlxx;
    //卡脖子
    private String isKbz;
   //是否属于专精特新小巨人
    private String isXjr;
    //是否属于行业“隐性冠军”
    private String isGj;
    //是否上市
    private String isSs;
    //所属行业三级级分类
    private String industry;
    //国民经济行业
    private String industryName;

    private String newIndustry;
    //战略性新兴产业
    private String newIndustryName;
//是否spv
    private String isSpvProj;
//SPV基金名称
    private String isSpvFundName;

    //是否国民经济行业
    private String isIndustry;
    //是否战略性新兴产业
    private String isNewIndustry;

    /** 退出日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date quitDt;
    //退出阶段
    private String quitTypeStatus;
    //退出总额
    private Double quitSumAmount;

    private String projType;

    private String regName;

    private String fundId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date actualPayDate;

    private Double actualAmount;

    private Double intendedAmount;

    private String projObject;

    private Double  curTmoney;

    private Double  allTmoney;

    private Double  funToPro;

    private Double  allTmoneyPer;

    private Double curTmoneyPer;

    private String fundName;

    private String creditCode;

    private String isPublicComp;


    private String isDistributeName;

    //分配金额
    private Double distributeMoney;
    //其中省政府投资基金分配额
    private Double distributeGovMoney;
    //分配金额
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date   distributeDt;

    private String labelIds;

    //证券类别
    private String stockCategory;


    //是否完成上市后投资
    private String isAfterListed;
    //产业类别
    private String industryCategory;

    private String industryCategoryName;

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
    //上年度末授权专利数
    private String lastEmpowerPatent;
//项目投资时前一年纳税额

    private String tzLastYearTaxAmount;
    //上年度预计纳税额
    private String lastEstimateTaxAmount;
    //年度纳税额
    private String yearTaxAmount;


    //上市板块
    private String listedSector;


    //融资阶段
    private String entePhase;

    private String entePhaseName;
    //是否合规
    private String isFitName;

    private String isFit;
   //合规状态
    private String fitStatusName;
    //出资总金额
    private String sumAmount;
   //基金实缴金额
    private Double raiseAmount;
    //省政府
    private Double szfAmount;

    //省政府基金穿透到二级基金投项目的金额
    private String ctInveAmount;

   //-退还省政府投资金额
    private Double govQuitAmount;

   //所属行业一级分类
    private String oneIndustry;

    //发展“卡脖子技术”数量
    private String isKbzNum;
    //省
    private String addressProvince;
    //市
    private String addressCity;
    //区
    private String addressArea;
    //地址
    private String addressDetails;


    private String enteId;


    private String  projIdSpv;

    private String  isExcept;



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

    public String getIsFit() {
        return isFit;
    }

    public void setIsFit(String isFit) {
        this.isFit = isFit;
    }

    public String getEnteId() {
        return enteId;
    }

    public void setEnteId(String enteId) {
        this.enteId = enteId;
    }

    public String getIndustryCategoryName() {
        return industryCategoryName;
    }

    public void setIndustryCategoryName(String industryCategoryName) {
        this.industryCategoryName = industryCategoryName;
    }

    public String getIsKbzNum() {
        return isKbzNum;
    }

    public void setIsKbzNum(String isKbzNum) {
        this.isKbzNum = isKbzNum;
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

    public String getEntePhaseName() {
        return entePhaseName;
    }

    public void setEntePhaseName(String entePhaseName) {
        this.entePhaseName = entePhaseName;
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

    public String getCtInveAmount() {
        return ctInveAmount;
    }

    public void setCtInveAmount(String ctInveAmount) {
        this.ctInveAmount = ctInveAmount;
    }

    public String getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(String sumAmount) {
        this.sumAmount = sumAmount;
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

    public String getIsFitName() {
        return isFitName;
    }

    public void setIsFitName(String isFitName) {
        this.isFitName = isFitName;
    }

    public String getFitStatusName() {
        return fitStatusName;
    }

    public void setFitStatusName(String fitStatusName) {
        this.fitStatusName = fitStatusName;
    }

    public String getListedSector() {
        return listedSector;
    }

    public void setListedSector(String listedSector) {
        this.listedSector = listedSector;
    }

    public String getEntePhase() {
        return entePhase;
    }

    public void setEntePhase(String entePhase) {
        this.entePhase = entePhase;
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

    public String getStockCategory() {
        return stockCategory;
    }

    public void setStockCategory(String stockCategory) {
        this.stockCategory = stockCategory;
    }

    public String getIsXjr() {
        return isXjr;
    }

    public void setIsXjr(String isXjr) {
        this.isXjr = isXjr;
    }

    public String getIsGj() {
        return isGj;
    }

    public void setIsGj(String isGj) {
        this.isGj = isGj;
    }

    public String getIsSs() {
        return isSs;
    }

    public void setIsSs(String isSs) {
        this.isSs = isSs;
    }

    public String getIsKbz() {
        return isKbz;
    }

    public void setIsKbz(String isKbz) {
        this.isKbz = isKbz;
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
    }

    public Double getDistributeMoney() {
        return distributeMoney;
    }

    public void setDistributeMoney(Double distributeMoney) {
        this.distributeMoney = distributeMoney;
    }

    public Double getDistributeGovMoney() {
        return distributeGovMoney;
    }

    public void setDistributeGovMoney(Double distributeGovMoney) {
        this.distributeGovMoney = distributeGovMoney;
    }

    public Date getDistributeDt() {
        return distributeDt;
    }

    public void setDistributeDt(Date distributeDt) {
        this.distributeDt = distributeDt;
    }

    public String getIsDistributeName() {
        return isDistributeName;
    }

    public void setIsDistributeName(String isDistributeName) {
        this.isDistributeName = isDistributeName;
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

    public Double getCurTmoney() {
        return curTmoney;
    }

    public void setCurTmoney(Double curTmoney) {
        this.curTmoney = curTmoney;
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

    public String getIsZlxx() {
        return isZlxx;
    }

    public void setIsZlxx(String isZlxx) {
        this.isZlxx = isZlxx;
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

    public Double getQuitSumAmount() {
        return quitSumAmount;
    }

    public void setQuitSumAmount(Double quitSumAmount) {
        this.quitSumAmount = quitSumAmount;
    }

    public String getQuitTypeStatus() {
        return quitTypeStatus;
    }

    public void setQuitTypeStatus(String quitTypeStatus) {
        this.quitTypeStatus = quitTypeStatus;
    }

    public Date getQuitDt() {
        return quitDt;
    }

    public void setQuitDt(Date quitDt) {
        this.quitDt = quitDt;
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

    public String getIsSpvProj() {
        return isSpvProj;
    }

    public void setIsSpvProj(String isSpvProj) {
        this.isSpvProj = isSpvProj;
    }

    public String getIsZx() {
        return isZx;
    }

    public void setIsZx(String isZx) {
        this.isZx = isZx;
    }

    public String getIsGx() {
        return isGx;
    }

    public void setIsGx(String isGx) {
        this.isGx = isGx;
    }

    public String getIsYdyl() {
        return isYdyl;
    }

    public void setIsYdyl(String isYdyl) {
        this.isYdyl = isYdyl;
    }

    public String getProjProperty() {
        return projProperty;
    }

    public void setProjProperty(String projProperty) {
        this.projProperty = projProperty;
    }

    public Double getSumActualPayAmount() {
        return sumActualPayAmount;
    }

    public void setSumActualPayAmount(Double sumActualPayAmount) {
        this.sumActualPayAmount = sumActualPayAmount;
    }

    public double getSignAmount() {
        return signAmount;
    }

    public void setSignAmount(double signAmount) {
        this.signAmount = signAmount;
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
