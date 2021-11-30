package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ApiModel(value="项目信息详细对象",description="项目信息详细对象")
public class ProjInfoVO extends BaseVO{

	private static final long serialVersionUID = -3433724188091439299L;

	/** 主键 */
	private String projId;

	/** 子基金简称 */
	@ApiModelProperty(value="子基金简称",name="projObjectName",example="启明二期")
	private String projObjectName;

	/** 项目来源 */
	@ApiModelProperty(value="项目来源",name="projSrc",example="项目经理自行开拓")
	private String projSrc;


	private String projSrcName;
	
	/** 来源说明 */
	private String srcDesc;

	/** 项目经理 */
	@ApiModelProperty(value="项目经理",name="mangerName")
	private String mangerName;
	
	/** 项目成员 */
	@ApiModelProperty(value="项目成员",name="memberName")
	private String memberName;
	
	/** 分管合伙人 */
	@ApiModelProperty(value="分管合伙人",name="chargePartner",example="金玉玲")
	private String chargePartner;
	
	/** 保密等级 */
	@ApiModelProperty(value="保密等级",name="secrecyLevel",example="机密")
	private String secrecyLevel;

	/** 项目概况 */
	@ApiModelProperty(value="项目概况",name="projOverview",example="")
	private String projOverview;

	/** 项目状态（2：储备项目，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
	private String projStatus;

    /** 项目状态（2：储备项目，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
    private String projStatusName;

	/** 被投对象 */
	private String projObject;

	/** 质量评估评级结果 */
	private Long scoreResult;

	/** 项目所属公司ID */
	private Long groupId;


	/** 项目编号 */
	private String projNo;

	/** 项目名称 */
	private String projName;

	/** 投资经理对项目评价 */
	private String inveEvaluation;


    /** 出资主体编号 */
    private String inveId;

    /** 出资主体名称 */
    private String inveName;


    /** 项目所属公司NAME */
    private String groupName;

    /** 决策日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date decisionDt;

	private Date upDt;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date updateDt;

	/** 首次出资日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date firstCZDt;

	/** 退出日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date quitDt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date inveDt;

	/** 累计出资 */
	private String allRequestMoney;

	/** 退出出资 */
	private String quitMoney;

	/** 最新占比 */
	private String currentRatio;

	/** 投项目占股比-合同 */
	private String holdShare;
	/** 管理公司 */
	private String mcName;
	/** 累计出资金额 */
	private Double sumInveAmount;
	/** 拟投资金额*/
	private Double intendedAmount;

	private Double actualAmount;

	private  Double perShare;

	private String isRecord;

	private String projMc;

	private String  projType;

	private String isFit;
	/**所属行业名称**/
	private String industryName;
	/**所属行业**/
	private String industry;
	/**战略新兴产业**/
	private String newIndustry;
	/**战略新兴产业名称**/
	private String newIndustryName;
	/**是否spv基金投项目**/
	private String isSpvFundId;
	/**SPV基金名称**/
	private String isSpvFundName;
	/**是否SPV基金**/
	private String isSpvProj;

	private String status;

	private String isFundName;

	private String finaRoundsName;

	private String 	isZx;

	private String   isGx;

	private String  isYdyl;

	private String  isZlxx;

	private String  projProperty;

	private String projPropertyName;

	private List<LabelModel> listLabel;

	private List<String> listLabels;

	private XjlTPaymentRequestModel xjl;

	private String inveTypeName;

	private String inveRoleName;

	private String quitTypeName;

	private Long usertype;

	private String isFitStr;

	private String twoFundName;


	private String isRegister;


	private String isRegion;

	private String isRegionName;

	private String enterpriseFormName;

	private EnteInfoModel  enteInfoModel;

	private String  warnMsg;

	private String addressDetails;

	private String industryCategoryName;


    private String isAfterListed;

    private String industryCategory;

    private String   entePhase;

	private String oneIndustry;

	/** 住所省 */
	private String province;

	/** 住所市 */
	private String city;

	/** 住所区 */
	private String addArea;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddArea() {
		return addArea;
	}

	public void setAddArea(String addArea) {
		this.addArea = addArea;
	}

	public String getOneIndustry() {
		return oneIndustry;
	}

	public void setOneIndustry(String oneIndustry) {
		this.oneIndustry = oneIndustry;
	}

	public String getIndustryCategoryName() {
		return industryCategoryName;
	}

	public void setIndustryCategoryName(String industryCategoryName) {
		this.industryCategoryName = industryCategoryName;
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

    public String getEntePhase() {
        return entePhase;
    }

    public void setEntePhase(String entePhase) {
        this.entePhase = entePhase;
    }

    public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public String getWarnMsg() {
		return warnMsg;
	}

	public void setWarnMsg(String warnMsg) {
		this.warnMsg = warnMsg;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public Date getUpDt() {
		return upDt;
	}

	public void setUpDt(Date upDt) {
		this.upDt = upDt;
	}

	public String getEnterpriseFormName() {
		return enterpriseFormName;
	}

	public void setEnterpriseFormName(String enterpriseFormName) {
		this.enterpriseFormName = enterpriseFormName;
	}

	public EnteInfoModel getEnteInfoModel() {
		return enteInfoModel;
	}

	public void setEnteInfoModel(EnteInfoModel enteInfoModel) {
		this.enteInfoModel = enteInfoModel;
	}

	public Date getInveDt() {
		return inveDt;
	}

	public void setInveDt(Date inveDt) {
		this.inveDt = inveDt;
	}

	public String getProjSrcName() {
		return projSrcName;
	}

	public void setProjSrcName(String projSrcName) {
		this.projSrcName = projSrcName;
	}

	public String getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}

	public String getIsRegion() {
		return isRegion;
	}

	public void setIsRegion(String isRegion) {
		this.isRegion = isRegion;
	}

	public String getIsRegionName() {
		return isRegionName;
	}

	public void setIsRegionName(String isRegionName) {
		this.isRegionName = isRegionName;
	}

	public String getTwoFundName() {
		return twoFundName;
	}

	public void setTwoFundName(String twoFundName) {
		this.twoFundName = twoFundName;
	}

	public String getIsFitStr() {
		return isFitStr;
	}

	public void setIsFitStr(String isFitStr) {
		this.isFitStr = isFitStr;
	}

	public Long getUsertype() {
		return usertype;
	}

	public void setUsertype(Long usertype) {
		this.usertype = usertype;
	}

	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public List<String> getListLabels() {
		return listLabels;
	}

	public void setListLabels(List<String> listLabels) {
		this.listLabels = listLabels;
	}

	public String getInveRoleName() {
		return inveRoleName;
	}

	public void setInveRoleName(String inveRoleName) {
		this.inveRoleName = inveRoleName;
	}

	public String getQuitTypeName() {
		return quitTypeName;
	}

	public void setQuitTypeName(String quitTypeName) {
		this.quitTypeName = quitTypeName;
	}

	public String getInveTypeName() {
		return inveTypeName;
	}

	public void setInveTypeName(String inveTypeName) {
		this.inveTypeName = inveTypeName;
	}

	public String getFinaRoundsName() {
		return finaRoundsName;
	}

	public void setFinaRoundsName(String finaRoundsName) {
		this.finaRoundsName = finaRoundsName;
	}

	public XjlTPaymentRequestModel getXjl() {
		return xjl;
	}

	public void setXjl(XjlTPaymentRequestModel xjl) {
		this.xjl = xjl;
	}

	public List<LabelModel> getListLabel() {
		return listLabel;
	}

	public void setListLabel(List<LabelModel> listLabel) {
		this.listLabel = listLabel;
	}

	public String getProjProperty() {
		return projProperty;
	}

	public void setProjProperty(String projProperty) {
		this.projProperty = projProperty;
	}

	public String getProjPropertyName() {
		return projPropertyName;
	}

	public void setProjPropertyName(String projPropertyName) {
		this.projPropertyName = projPropertyName;
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

	public String getIsZlxx() {
		return isZlxx;
	}

	public void setIsZlxx(String isZlxx) {
		this.isZlxx = isZlxx;
	}

	private ProjQuitApplModel projQuitApplModel;

	private ProjAppInfoModel projAppInfoModel;

	private FundInfoModel fundInfo;

	private EntBaseInfoModel entBaseInfoModel;

	public FundInfoModel getFundInfo() {
		return fundInfo;
	}

	public void setFundInfo(FundInfoModel fundInfo) {
		this.fundInfo = fundInfo;
	}

	public EntBaseInfoModel getEntBaseInfoModel() {
		return entBaseInfoModel;
	}

	public void setEntBaseInfoModel(EntBaseInfoModel entBaseInfoModel) {
		this.entBaseInfoModel = entBaseInfoModel;
	}

	public BdTFitRegulationSelfModel getBdTFitRegulationSelfModel() {
		return bdTFitRegulationSelfModel;
	}

	public void setBdTFitRegulationSelfModel(BdTFitRegulationSelfModel bdTFitRegulationSelfModel) {
		this.bdTFitRegulationSelfModel = bdTFitRegulationSelfModel;
	}

	private BdTFitRegulationSelfModel bdTFitRegulationSelfModel;


	public ProjAppInfoModel getProjAppInfoModel() {
		return projAppInfoModel;
	}

	public void setProjAppInfoModel(ProjAppInfoModel projAppInfoModel) {
		this.projAppInfoModel = projAppInfoModel;
	}

	public ProjQuitApplModel getProjQuitApplModel() {
		return projQuitApplModel;
	}

	public void setProjQuitApplModel(ProjQuitApplModel projQuitApplModel) {
		this.projQuitApplModel = projQuitApplModel;
	}

	public String getIsFundName() {
		return isFundName;
	}

	public void setIsFundName(String isFundName) {
		this.isFundName = isFundName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
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

	public String getIsSpvFundId() {
		return isSpvFundId;
	}

	public void setIsSpvFundId(String isSpvFundId) {
		this.isSpvFundId = isSpvFundId;
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

	public String getIsFit() {
		return isFit;
	}

	public void setIsFit(String isFit) {
		this.isFit = isFit;
	}

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public String getProjMc() {
		return projMc;
	}

	public void setProjMc(String projMc) {
		this.projMc = projMc;
	}

	public String getIsRecord() {
		return isRecord;
	}

	public void setIsRecord(String isRecord) {
		this.isRecord = isRecord;
	}

	public Double getPerShare() {
		return perShare;
	}

	public void setPerShare(Double perShare) {
		this.perShare = perShare;
	}

	public String getMcName() {
		return mcName;
	}

	public void setMcName(String mcName) {
		this.mcName = mcName;
	}

	public Double getSumInveAmount() {
		return sumInveAmount;
	}

	public void setSumInveAmount(Double sumInveAmount) {
		this.sumInveAmount = sumInveAmount;
	}

	public Double getIntendedAmount() {
		return intendedAmount;
	}

	public void setIntendedAmount(Double intendedAmount) {
		this.intendedAmount = intendedAmount;
	}

	public String getHoldShare() {
		return holdShare;
	}

	public void setHoldShare(String holdShare) {
		this.holdShare = holdShare;
	}

	public String getCurrentRatio() {
		return currentRatio;
	}

	public void setCurrentRatio(String currentRatio) {
		this.currentRatio = currentRatio;
	}

	public Date getFirstCZDt() {
		return firstCZDt;
	}

	public void setFirstCZDt(Date firstCZDt) {
		this.firstCZDt = firstCZDt;
	}

	public Date getQuitDt() {
		return quitDt;
	}

	public void setQuitDt(Date quitDt) {
		this.quitDt = quitDt;
	}

	public String getAllRequestMoney() {
		return allRequestMoney;
	}

	public void setAllRequestMoney(String allRequestMoney) {
		this.allRequestMoney = allRequestMoney;
	}

	public String getQuitMoney() {
		return quitMoney;
	}

	public void setQuitMoney(String quitMoney) {
		this.quitMoney = quitMoney;
	}

	public Date getDecisionDt() {
        return decisionDt;
    }

    public String getProjStatusName() {
        return projStatusName;
    }

    public void setProjStatusName(String projStatusName) {
        this.projStatusName = projStatusName;
    }

    public void setDecisionDt(Date decisionDt) {
        this.decisionDt = decisionDt;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getInveEvaluation() {
		return inveEvaluation;
	}

	public void setInveEvaluation(String inveEvaluation) {
		this.inveEvaluation = inveEvaluation;
	}

	public String getProjObjectName() {
		return projObjectName;
	}

	public void setProjObjectName(String projObjectName) {
		this.projObjectName = projObjectName;
	}

	public String getProjSrc() {
		return projSrc;
	}

	public void setProjSrc(String projSrc) {
		this.projSrc = projSrc;
	}

	public String getChargePartner() {
		return chargePartner;
	}

	public void setChargePartner(String chargePartner) {
		this.chargePartner = chargePartner;
	}

	public String getSecrecyLevel() {
		return secrecyLevel;
	}

	public void setSecrecyLevel(String secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	public String getProjOverview() {
		return projOverview;
	}

	public void setProjOverview(String projOverview) {
		this.projOverview = projOverview;
	}

	public String getMangerName() {
		return mangerName;
	}

	public void setMangerName(String mangerName) {
		this.mangerName = mangerName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public String getProjObject() {
		return projObject;
	}

	public void setProjObject(String projObject) {
		this.projObject = projObject;
	}

	public Long getScoreResult() {
		return scoreResult;
	}

	public void setScoreResult(Long scoreResult) {
		this.scoreResult = scoreResult;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getSrcDesc() {
		return srcDesc;
	}

	public void setSrcDesc(String srcDesc) {
		this.srcDesc = srcDesc;
	}

	
	
	
}
