package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class FundInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String fundId;

	/** 基金编号 */
	private String fundCode;

	/** 基金名称 */
	private String fundName;

	/** 基金注册名称 */
	private String regName;

	/** 管理公司ID */
	private String mcId;

	/** 管理公司 */
	private String mcName;

	/** 联系人 */
	private String contact;

	/** 联系人电话 */
	private String phoneNo;

	/** 联系人Email */
	private String contactEmail;

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

	/** 托管银行ID */
	private String custodianBankId;

	/** 原托管银行ID */
	private String oldCustodianBank;

	/** 注册资本金 */
	private Double regCapital;

	/** 注册资本金币种 */
	private String regCurrency;

	/** 资本金账户 */
	private String capitalAccount;

	/** 资本回收账户 */
	private String returnAccount;

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

	/** 资本金银行 */
	private String capitalBank;

	/** 资本回收银行 */
	private String returnBank;

	/** 合伙人名单 */
	private String partners;

	/** 基金所属公司ID */
	private Long groupId;

    /** 普通合伙人 */
    private String gp;

	/** 普通合伙人ID */
	private String gpId;

	/** 折算为人民币 */
	private Double rmbRaiseAmount;

	/** 基金认缴规模 */
	private Double currentAmount;

	/** 当前认缴规模币种 */
	private String currentCurrency;

	/** 当前认缴规模折算人民币 */
	private Double rmbCurrentAmount;

	/** 基金信息维护人ID */
	private String maintenanceId;

	/** 基金信息维护人 */
	private String maintenance;

	/** 统一社会信用代码 */
	private String socialCode;

	/** 关键人士锁定 */
	private String keyPersonLock;

	/** null */
	private Double ratioInGp;

	/** null */
	private Double rmbPlanAmount;

	/** null */
	private String fundScope;

	/** 本基金占比 */
	private Double yhRatioAsLp;

	/** null */
	private Double yhRatioInGp;

	/** null */
	private Double yhRatioInMc;

	/** 基金邮箱 */
	private String email;

	/** 基金注册企业ID */
	private String enteId;

	private String perCent;

	/** null */
	private String emailPassword;

	/** null */
	private String financeId;


	/** null */
	private String financeName;

	/** 目标规模(显示) */
	private String planAmountDisplay;

	/** 认缴规模 */
	private String currentAmountDisplay;

	/** 实缴规模 */
	private String raiseAmountDisplay;

	/** 实缴比例=当前实缴规模/当前认缴规模 */
	private String raiseCurrent;

	/** 已投项目数 */
	private String projNum;

	/** 已投资金额 */
	private String investAmount;

	/** 累计决策项目个数 */
	private String totalInvestCount;

	/** 累计投资金额	 */
	private String totalInvestAmount;

	/** 今年投资项目个数 */
	private String thisYearInvestCount;

	/** 今年投资金额 */
	private String thisYearInvestAmount;

	/** 在投项目数 */
	private String investingCount;

	/** 基金管理投资相关信息*/
	private FundInveDescModel fundInveDescModel;

	private List<FundMemberModel> fundMemberList;

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

	private String addressDetails;

	private String actualAmount;

    private String bjjPer;

    private String projctId;

	/** 备案日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date recordDate;

	/** 备案日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date startDate;


	private String executivePartnerId;

	private String executivePartnerName;

	/** 备案证明附件 */
	private String recordAtta;

	/** 基金投资人信息 */
	private List<FundGpInfoModel> fundGpList;

	/** 基金管理人信息 */
	private List<FundMcInfoModel> fundMcList;

	/** 基金出资人信息*/
	private List<FundShareInfoModel> shareInfoList;

	private FundShareInfoModel fundShareInfoModel;

	/** 基金管理人信息 */
	private FundMcInfoModel fundMcInfoModel;


	private FundShareItemModel fundShareItemModel;

	/**是否有托管协议**/
	private String isCustodian;

	/**托管协议协议附件**/
	private String custodianAtta;

	/**是否有委托管理协议**/
	private String isAuth;

	/**委托管理协议附件**/
	private String authAtta;

	/**本基金认缴规模**/
	private Double promiseAmount;
	/**收益**/
	private Double profitAmount;

	private String status;

	private Double inveCurrentAmount;

	private String spvType;

	private String registerCode;

	private String isExcept;

	private Double inveRaiseAmount;

	//是否引导基金
	private String isLead;

   //是否建立退出机制
	private String isExit;

   //省级财政出资
	private String  financeAmount;

    //投资方式
	private String inveType;

    //批准设立部门
	private String appDep;

    //省级资金来源
	private String govFundSrc;
   //设立依据
	private String basis;
   //投资限制
	private String inveLimit;

	//管理部门
	private String manDep;

	private String  shortName;

	private String  creditCode;

	private ProjInfoModel projInfo;

	private ProjAppInfoModel projAppInfo;


	private String  registerStatus;

	private String oldFundGenre;

	private String isDirect;

	private String isDesignate;



	private String fundCodeSign;


	private String organStruct;

	private String otherFile;

	//基金管理人对应的用户id
	private String mcUserId;

	public String getMcUserId() {
		return mcUserId;
	}

	public void setMcUserId(String mcUserId) {
		this.mcUserId = mcUserId;
	}

	public String getOtherFile() {
		return otherFile;
	}

	public void setOtherFile(String otherFile) {
		this.otherFile = otherFile;
	}

	public String getOrganStruct() {
		return organStruct;
	}

	public void setOrganStruct(String organStruct) {
		this.organStruct = organStruct;
	}

	public String getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

    private EntBaseInfoModel entBaseInfo;

    private String fundsType;

	private String parentId;

	private String fundCategory;

	private String oldSubPlatProp;

	private String perRaiseCurrent;

	private String fundCodeType;


	private String  fundLead;

	private String  fundLeadId;

	private String motherFundName;


	private String motherFundId;

    private String repName;
    private String repId;


	private BdTFundCostInfoModel bdTFundCostInfoModel;

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getMotherFundName() {
		return motherFundName;
	}

	public void setMotherFundName(String motherFundName) {
		this.motherFundName = motherFundName;
	}

	public String getMotherFundId() {
		return motherFundId;
	}

	public void setMotherFundId(String motherFundId) {
		this.motherFundId = motherFundId;
	}

	public String getFundLeadId() {
		return fundLeadId;
	}

	public void setFundLeadId(String fundLeadId) {
		this.fundLeadId = fundLeadId;
	}

	public String getFundLead() {
		return fundLead;
	}

	public void setFundLead(String fundLead) {
		this.fundLead = fundLead;
	}

	public String getFundCodeType() {
		return fundCodeType;
	}

	public void setFundCodeType(String fundCodeType) {
		this.fundCodeType = fundCodeType;
	}

	public String getOldFundGenre() {
		return oldFundGenre;
	}

	public void setOldFundGenre(String oldFundGenre) {
		this.oldFundGenre = oldFundGenre;
	}

	public BdTFundCostInfoModel getBdTFundCostInfoModel() {
		return bdTFundCostInfoModel;
	}

	public void setBdTFundCostInfoModel(BdTFundCostInfoModel bdTFundCostInfoModel) {
		this.bdTFundCostInfoModel = bdTFundCostInfoModel;
	}

	public String getPerCent() {
		return perCent;
	}

	public void setPerCent(String perCent) {
		this.perCent = perCent;
	}

	public String getPerRaiseCurrent() {
		return perRaiseCurrent;
	}

	public void setPerRaiseCurrent(String perRaiseCurrent) {
		this.perRaiseCurrent = perRaiseCurrent;
	}

	public String getFundCodeSign() {
		return fundCodeSign;
	}

	public void setFundCodeSign(String fundCodeSign) {
		this.fundCodeSign = fundCodeSign;
	}

	public String getIsDesignate() {
		return isDesignate;
	}

	public void setIsDesignate(String isDesignate) {
		this.isDesignate = isDesignate;
	}

	public String getIsDirect() {
		return isDirect;
	}

	public void setIsDirect(String isDirect) {
		this.isDirect = isDirect;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getFundCategory() {
		return fundCategory;
	}

	public void setFundCategory(String fundCategory) {
		this.fundCategory = fundCategory;
	}

	public String getOldSubPlatProp() {
		return oldSubPlatProp;
	}

	public void setOldSubPlatProp(String oldSubPlatProp) {
		this.oldSubPlatProp = oldSubPlatProp;
	}

	public String getFundsType() {
		return fundsType;
	}

	public void setFundsType(String fundsType) {
		this.fundsType = fundsType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getProjctId() {
        return projctId;
    }

    public void setProjctId(String projctId) {
        this.projctId = projctId;
    }

    public EntBaseInfoModel getEntBaseInfo() {
        return entBaseInfo;
    }

    public void setEntBaseInfo(EntBaseInfoModel entBaseInfo) {
        this.entBaseInfo = entBaseInfo;
    }

    public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getManDep() {
		return manDep;
	}

	public void setManDep(String manDep) {
		this.manDep = manDep;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public String getInveLimit() {
		return inveLimit;
	}

	public void setInveLimit(String inveLimit) {
		this.inveLimit = inveLimit;
	}

	public String getIsLead() {
		return isLead;
	}

	public void setIsLead(String isLead) {
		this.isLead = isLead;
	}

	public String getIsExit() {
		return isExit;
	}

	public void setIsExit(String isExit) {
		this.isExit = isExit;
	}

	public String getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(String financeAmount) {
		this.financeAmount = financeAmount;
	}

	public String getInveType() {
		return inveType;
	}

	public void setInveType(String inveType) {
		this.inveType = inveType;
	}

	public String getAppDep() {
		return appDep;
	}

	public void setAppDep(String appDep) {
		this.appDep = appDep;
	}

	public String getGovFundSrc() {
		return govFundSrc;
	}

	public void setGovFundSrc(String govFundSrc) {
		this.govFundSrc = govFundSrc;
	}

	public ProjAppInfoModel getProjAppInfo() {
		return projAppInfo;
	}

	public void setProjAppInfo(ProjAppInfoModel projAppInfo) {
		this.projAppInfo = projAppInfo;
	}

	public ProjInfoModel getProjInfo() {
		return projInfo;
	}

	public void setProjInfo(ProjInfoModel projInfo) {
		this.projInfo = projInfo;
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

	public String getBjjPer() {
        return bjjPer;
    }

    public void setBjjPer(String bjjPer) {
        this.bjjPer = bjjPer;
    }

    public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public String getSpvType() {
		return spvType;
	}

	public void setSpvType(String spvType) {
		this.spvType = spvType;
	}

	public Double getInveCurrentAmount() {
		return inveCurrentAmount;
	}

	public void setInveCurrentAmount(Double inveCurrentAmount) {
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

	public FundShareItemModel getFundShareItemModel() {
		return fundShareItemModel;
	}

	public void setFundShareItemModel(FundShareItemModel fundShareItemModel) {
		this.fundShareItemModel = fundShareItemModel;
	}

	public Double getPromiseAmount() {
		return promiseAmount;
	}

	public void setPromiseAmount(Double promiseAmount) {
		this.promiseAmount = promiseAmount;
	}

	public String getOldCustodianBank() {
		return oldCustodianBank;
	}

	public void setOldCustodianBank(String oldCustodianBank) {
		this.oldCustodianBank = oldCustodianBank;
	}

	public FundMcInfoModel getFundMcInfoModel() {
		return fundMcInfoModel;
	}

	public void setFundMcInfoModel(FundMcInfoModel fundMcInfoModel) {
		this.fundMcInfoModel = fundMcInfoModel;
	}

	public FundShareInfoModel getFundShareInfoModel() {
		return fundShareInfoModel;
	}

	public void setFundShareInfoModel(FundShareInfoModel fundShareInfoModel) {
		this.fundShareInfoModel = fundShareInfoModel;
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

	public List<FundShareInfoModel> getShareInfoList() {
		return shareInfoList;
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

	public String getPlanAmountDisplay() {
		return planAmountDisplay;
	}

	public void setPlanAmountDisplay(String planAmountDisplay) {
		this.planAmountDisplay = planAmountDisplay;
	}

	public String getCurrentAmountDisplay() {
		return currentAmountDisplay;
	}

	public void setCurrentAmountDisplay(String currentAmountDisplay) {
		this.currentAmountDisplay = currentAmountDisplay;
	}

	public String getRaiseAmountDisplay() {
		return raiseAmountDisplay;
	}

	public void setRaiseAmountDisplay(String raiseAmountDisplay) {
		this.raiseAmountDisplay = raiseAmountDisplay;
	}

	public String getRaiseCurrent() {
		return raiseCurrent;
	}

	public void setRaiseCurrent(String raiseCurrent) {
		this.raiseCurrent = raiseCurrent;
	}

	public String getProjNum() {
		return projNum;
	}

	public void setProjNum(String projNum) {
		this.projNum = projNum;
	}

	public String getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(String investAmount) {
		this.investAmount = investAmount;
	}

	public void setThisYearInvestCount(String thisYearInvestCount) {
		this.thisYearInvestCount = thisYearInvestCount;
	}

	public String getThisYearInvestAmount() {
		return thisYearInvestAmount;
	}

	public void setThisYearInvestAmount(String thisYearInvestAmount) {
		this.thisYearInvestAmount = thisYearInvestAmount;
	}

	public String getInvestingCount() {
		return investingCount;
	}

	public void setInvestingCount(String investingCount) {
		this.investingCount = investingCount;
	}

	public FundInveDescModel getFundInveDescModel() {
		return fundInveDescModel;
	}

	public void setFundInveDescModel(FundInveDescModel fundInveDescModel) {
		this.fundInveDescModel = fundInveDescModel;
	}

	public void setFundMemberList(List<FundMemberModel> fundMemberList) {
		this.fundMemberList = fundMemberList;
	}

	public List<FundGpInfoModel> getFundGpList() {
		return fundGpList;
	}

	public void setFundGpList(List<FundGpInfoModel> fundGpList) {
		this.fundGpList = fundGpList;
	}

	public List<FundMcInfoModel> getFundMcList() {
		return fundMcList;
	}

	public void setFundMcList(List<FundMcInfoModel> fundMcList) {
		this.fundMcList = fundMcList;
	}

	public List<FundMemberModel> getFundMemberList() {
		return fundMemberList;
	}

	public String getTotalInvestCount() {
		return totalInvestCount;
	}

	public void setTotalInvestCount(String totalInvestCount) {
		this.totalInvestCount = totalInvestCount;
	}

	public String getTotalInvestAmount() {
		return totalInvestAmount;
	}

	public void setTotalInvestAmount(String totalInvestAmount) {
		this.totalInvestAmount = totalInvestAmount;
	}

	public String getThisYearInvestCount() {
		return thisYearInvestCount;
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

	public String getMcId() {
		return mcId;
	}

	public void setMcId(String mcId) {
		this.mcId = mcId;
	}

	public String getMcName() {
		return mcName;
	}

	public void setMcName(String mcName) {
		this.mcName = mcName;
	}

	public String getGp() {
		return gp;
	}

	public void setGp(String gp) {
		this.gp = gp;
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

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
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

	public String getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(String capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

	public String getReturnAccount() {
		return returnAccount;
	}

	public void setReturnAccount(String returnAccount) {
		this.returnAccount = returnAccount;
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

	public String getCapitalBank() {
		return capitalBank;
	}

	public void setCapitalBank(String capitalBank) {
		this.capitalBank = capitalBank;
	}

	public String getReturnBank() {
		return returnBank;
	}

	public void setReturnBank(String returnBank) {
		this.returnBank = returnBank;
	}

	public String getPartners() {
		return partners;
	}

	public void setPartners(String partners) {
		this.partners = partners;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGpId() {
		return gpId;
	}

	public void setGpId(String gpId) {
		this.gpId = gpId;
	}

	public Double getRmbRaiseAmount() {
		return rmbRaiseAmount;
	}

	public void setRmbRaiseAmount(Double rmbRaiseAmount) {
		this.rmbRaiseAmount = rmbRaiseAmount;
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

	public Double getRmbCurrentAmount() {
		return rmbCurrentAmount;
	}

	public void setRmbCurrentAmount(Double rmbCurrentAmount) {
		this.rmbCurrentAmount = rmbCurrentAmount;
	}

	public String getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public String getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
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

	public Double getRatioInGp() {
		return ratioInGp;
	}

	public void setRatioInGp(Double ratioInGp) {
		this.ratioInGp = ratioInGp;
	}

	public Double getRmbPlanAmount() {
		return rmbPlanAmount;
	}

	public void setRmbPlanAmount(Double rmbPlanAmount) {
		this.rmbPlanAmount = rmbPlanAmount;
	}

	public String getFundScope() {
		return fundScope;
	}

	public void setFundScope(String fundScope) {
		this.fundScope = fundScope;
	}

	public Double getYhRatioAsLp() {
		return yhRatioAsLp;
	}

	public void setYhRatioAsLp(Double yhRatioAsLp) {
		this.yhRatioAsLp = yhRatioAsLp;
	}

	public Double getYhRatioInGp() {
		return yhRatioInGp;
	}

	public void setYhRatioInGp(Double yhRatioInGp) {
		this.yhRatioInGp = yhRatioInGp;
	}

	public Double getYhRatioInMc() {
		return yhRatioInMc;
	}

	public void setYhRatioInMc(Double yhRatioInMc) {
		this.yhRatioInMc = yhRatioInMc;
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

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getFinanceName() {
		return financeName;
	}

	public void setFinanceName(String financeName) {
		this.financeName = financeName;
	}

	public String getExecutivePartnerId() {
		return executivePartnerId;
	}

	public void setExecutivePartnerId(String executivePartnerId) {
		this.executivePartnerId = executivePartnerId;
	}

	public String getExecutivePartnerName() {
		return executivePartnerName;
	}

	public void setExecutivePartnerName(String executivePartnerName) {
		this.executivePartnerName = executivePartnerName;
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