package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import com.ppmg.ei.model.FundInveDescModel;
import com.ppmg.ei.model.FundShareInfoModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class FundInfoDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String fundId;

	/** 基金编号 */
	private String fundCode;

	/** 基金名称 */
	private String fundName;

	/** 基金注册名称 */
	private String regName;

	/** 联系人 */
	private String contact;

	/** 联系人电话 */
	private String phoneNo;

	/** 拟募资规模 */
	private Double planAmount;

	/** 拟募资币种 */
	private String planCurr;

	/** 基金实缴规模 */
	private Double raiseAmount;

	/** 币种 */
	private String raiseCurrency;

	/** 基金形式（合伙制、公司制、契约制） */
	private String fundStruct;

	/** 基金一类类别（区域基金、国家基金、产业基金） */
	private String platProp;

	/** 基金二类类别 */
	private String subPlatProp;

	/** 基金注册日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date setupDt;

	/** 拟最终关闭时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date closeDt;

	/** 基金注册地址 */
	private String regAdd;

	/** 组织机构代码 */
	private String orgnCode;

	/** 法定代表人 */
	private String legalPerson;

	/** 托管银行 */
	private String custodianBank;

	/** 注册资本金 */
	private Double regCapital;

	/** 注册资本金币种 */
	private String regCurrency;

	/** 基金状态 */
	private String fundSts;

	/** 合伙协议附件 */
	private String descFile;

	/** 基金来源（1：母基金，2：子基金，3：其他） */
	private String fundSrc;

	/** 基金类型 */
	private String fundGenre;

	/** 托管银行账户 */
	private String custodianBankaccount;

	/** 基金所属公司ID */
	private Long groupId;

	/** 基金认缴规模 */
	private Double currentAmount;

	/** 当前认缴规模币种 */
	private String currentCurrency;

	/** 统一社会信用代码 */
	private String socialCode;

	/** 关键人士锁定 */
	private String keyPersonLock;

	/** 本基金占比 */
	private Double yhRatioAsLp;

	/** 基金邮箱 */
	private String email;

	/** 基金注册企业ID */
	private String enteId;

	/** 基金管理投资相关信息*/
	private FundInveDescModel fundInveDescModel;

	/** 是否需要合规性审查 */
	private String isFit;

	/** 基金托管银行开户行 */
	private String custodianOpenBank;

	/** 基金托管银行帐号 */
	private String custodianBankNum;

	/** 基金备案情况 */
	private String isRecord;

	/** 备案号 */
	private String recordCode;

	/** 备案日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date recordDate;

	/** 备案证明附件 */
	private String recordAtta;

	/** 普通合伙人ID */
	private String gpId;

	/** 普通合伙人 */
	private String gp;

	/**是否有托管协议**/
	private String isCustodian;

	/**托管协议协议附件**/
	private String custodianAtta;

	/**是否有委托管理协议**/
	private String isAuth;

	/**委托管理协议附件**/
	private String authAtta;

	private String oldCustodianBank;
	/**所得收益**/
	private Double profitAmount;

	private String status;
//本基金认缴规模
	private String inveCurrentAmount;
//特殊统计情况：三级基金不统计，统计四级基金
	private String isExcept;

	private Double inveRaiseAmount;


	private String isDecisionPass;

	private String	decisionDate;

	private String projStatus;

	private String	inveName;

	private Double amount;

	private String	isJs;

	private String inveId;

	private String appId;

	private String pqId;

	private String projId;

	private String spvType;

	private String isCorr;

	private String typeStr;

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getSpvType() {
		return spvType;
	}

	public void setSpvType(String spvType) {
		this.spvType = spvType;
	}

	public String getIsCorr() {
		return isCorr;
	}

	public void setIsCorr(String isCorr) {
		this.isCorr = isCorr;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getPqId() {
		return pqId;
	}

	public void setPqId(String pqId) {
		this.pqId = pqId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getInveId() {
		return inveId;
	}

	public void setInveId(String inveId) {
		this.inveId = inveId;
	}

	public String getIsDecisionPass() {
		return isDecisionPass;
	}

	public void setIsDecisionPass(String isDecisionPass) {
		this.isDecisionPass = isDecisionPass;
	}

	public String getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(String decisionDate) {
		this.decisionDate = decisionDate;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getIsJs() {
		return isJs;
	}

	public void setIsJs(String isJs) {
		this.isJs = isJs;
	}

	public Double getInveRaiseAmount() {
		return inveRaiseAmount;
	}

	public void setInveRaiseAmount(Double inveRaiseAmount) {
		this.inveRaiseAmount = inveRaiseAmount;
	}

	public String getIsExcept() {
		return isExcept;
	}

	public void setIsExcept(String isExcept) {
		this.isExcept = isExcept;
	}

	public String getInveCurrentAmount() {
		return inveCurrentAmount;
	}

	public void setInveCurrentAmount(String inveCurrentAmount) {
		this.inveCurrentAmount = inveCurrentAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getProfitAmount() {
		return profitAmount;
	}

	public void setProfitAmount(Double profitAmount) {
		this.profitAmount = profitAmount;
	}

	public String getOldCustodianBank() {
		return oldCustodianBank;
	}

	public void setOldCustodianBank(String oldCustodianBank) {
		this.oldCustodianBank = oldCustodianBank;
	}

	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}

	public String getAuthAtta() {
		return authAtta;
	}

	public void setAuthAtta(String authAtta) {
		this.authAtta = authAtta;
	}

	public String getIsCustodian() {
		return isCustodian;
	}

	public void setIsCustodian(String isCustodian) {
		this.isCustodian = isCustodian;
	}

	public String getCustodianAtta() {
		return custodianAtta;
	}

	public void setCustodianAtta(String custodianAtta) {
		this.custodianAtta = custodianAtta;
	}

	public String getGp() {
		return gp;
	}

	public void setGp(String gp) {
		this.gp = gp;
	}

	/** 基金出资人信息*/
	private List<FundShareInfoModel> shareInfoList;

	public List<FundShareInfoModel> getShareInfoList() {
		return shareInfoList;
	}

	/** 托管银行ID */
	private String custodianBankId;

	private String mcId;

	private String mcName;


	public String getGpId() {
		return gpId;
	}

	public void setGpId(String gpId) {
		this.gpId = gpId;
	}

	public String getMcName() {
		return mcName;
	}

	public void setMcName(String mcName) {
		this.mcName = mcName;
	}

	public String getMcId() {
		return mcId;
	}

	public void setMcId(String mcId) {
		this.mcId = mcId;
	}

	public String getCustodianBankId() {
		return custodianBankId;
	}

	public void setCustodianBankId(String custodianBankId) {
		this.custodianBankId = custodianBankId;
	}

	public void setShareInfoList(List<FundShareInfoModel> shareInfoList) {
		this.shareInfoList = shareInfoList;
	}

	public Double getYhRatioAsLp() {
        return yhRatioAsLp;
    }

    public void setYhRatioAsLp(Double yhRatioAsLp) {
        this.yhRatioAsLp = yhRatioAsLp;
    }

    public FundInveDescModel getFundInveDescModel() {
        return fundInveDescModel;
    }

    public void setFundInveDescModel(FundInveDescModel fundInveDescModel) {
        this.fundInveDescModel = fundInveDescModel;
    }

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}

	public String getPlanCurr() {
		return planCurr;
	}

	public void setPlanCurr(String planCurr) {
		this.planCurr = planCurr;
	}

	public Double getRaiseAmount() {
		return raiseAmount;
	}

	public void setRaiseAmount(Double raiseAmount) {
		this.raiseAmount = raiseAmount;
	}

	public String getRaiseCurrency() {
		return raiseCurrency;
	}

	public void setRaiseCurrency(String raiseCurrency) {
		this.raiseCurrency = raiseCurrency;
	}

	public String getFundStruct() {
		return fundStruct;
	}

	public void setFundStruct(String fundStruct) {
		this.fundStruct = fundStruct;
	}

	public String getPlatProp() {
		return platProp;
	}

	public void setPlatProp(String platProp) {
		this.platProp = platProp;
	}

	public String getSubPlatProp() {
		return subPlatProp;
	}

	public void setSubPlatProp(String subPlatProp) {
		this.subPlatProp = subPlatProp;
	}

	public Date getSetupDt() {
		return setupDt;
	}

	public void setSetupDt(Date setupDt) {
		this.setupDt = setupDt;
	}

	public Date getCloseDt() {
		return closeDt;
	}

	public void setCloseDt(Date closeDt) {
		this.closeDt = closeDt;
	}

	public String getRegAdd() {
		return regAdd;
	}

	public void setRegAdd(String regAdd) {
		this.regAdd = regAdd;
	}

	public String getOrgnCode() {
		return orgnCode;
	}

	public void setOrgnCode(String orgnCode) {
		this.orgnCode = orgnCode;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getCustodianBank() {
		return custodianBank;
	}

	public void setCustodianBank(String custodianBank) {
		this.custodianBank = custodianBank;
	}

	public Double getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(Double regCapital) {
		this.regCapital = regCapital;
	}

	public String getRegCurrency() {
		return regCurrency;
	}

	public void setRegCurrency(String regCurrency) {
		this.regCurrency = regCurrency;
	}

	public String getFundSts() {
		return fundSts;
	}

	public void setFundSts(String fundSts) {
		this.fundSts = fundSts;
	}

	public String getDescFile() {
		return descFile;
	}

	public void setDescFile(String descFile) {
		this.descFile = descFile;
	}

	public String getFundSrc() {
		return fundSrc;
	}

	public void setFundSrc(String fundSrc) {
		this.fundSrc = fundSrc;
	}

	public String getFundGenre() {
		return fundGenre;
	}

	public void setFundGenre(String fundGenre) {
		this.fundGenre = fundGenre;
	}

	public String getCustodianBankaccount() {
		return custodianBankaccount;
	}

	public void setCustodianBankaccount(String custodianBankaccount) {
		this.custodianBankaccount = custodianBankaccount;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getCurrentCurrency() {
		return currentCurrency;
	}

	public void setCurrentCurrency(String currentCurrency) {
		this.currentCurrency = currentCurrency;
	}

	public String getSocialCode() {
		return socialCode;
	}

	public void setSocialCode(String socialCode) {
		this.socialCode = socialCode;
	}

	public String getKeyPersonLock() {
		return keyPersonLock;
	}

	public void setKeyPersonLock(String keyPersonLock) {
		this.keyPersonLock = keyPersonLock;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnteId() {
		return enteId;
	}

	public void setEnteId(String enteId) {
		this.enteId = enteId;
	}

	public String getIsFit() {
		return isFit;
	}

	public void setIsFit(String isFit) {
		this.isFit = isFit;
	}

	public String getCustodianOpenBank() {
		return custodianOpenBank;
	}

	public void setCustodianOpenBank(String custodianOpenBank) {
		this.custodianOpenBank = custodianOpenBank;
	}

	public String getCustodianBankNum() {
		return custodianBankNum;
	}

	public void setCustodianBankNum(String custodianBankNum) {
		this.custodianBankNum = custodianBankNum;
	}

	public String getIsRecord() {
		return isRecord;
	}

	public void setIsRecord(String isRecord) {
		this.isRecord = isRecord;
	}

	public String getRecordCode() {
		return recordCode;
	}

	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getRecordAtta() {
		return recordAtta;
	}

	public void setRecordAtta(String recordAtta) {
		this.recordAtta = recordAtta;
	}

}