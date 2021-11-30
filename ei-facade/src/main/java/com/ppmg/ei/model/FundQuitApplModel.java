package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundQuitApplModel extends BaseModel {

	private static final long serialVersionUID = -7291804359760665711L;

	/** null */
	private String appId;

	/** 投资对象 */
	private String objectId;

	/** 申请人 */
	private String appUser;

	/** 申请日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date appDt;

	/** 退出类型（1：部分退出，2：全部退出） */
	private String quitType;

	/** 退出方式 */
	private String quitWay;
	private String quitWayName;

	/*基金认缴总规模（万元）*/
	private Double fundSubscribed;
	/*退基金实缴规模（万元）*/
	private Double fundPaidIn;






	public Double getFundSubscribed() {
		return fundSubscribed;
	}

	public void setFundSubscribed(Double fundSubscribed) {
		this.fundSubscribed = fundSubscribed;
	}

	public Double getFundPaidIn() {
		return fundPaidIn;
	}

	public void setFundPaidIn(Double fundPaidIn) {
		this.fundPaidIn = fundPaidIn;
	}

	public String getQuitWayName() {
		return quitWayName;
	}

	public void setQuitWayName(String quitWayName) {
		this.quitWayName = quitWayName;
	}

	/** 退出日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date quitDt;

	/** 退出认缴出资额 */
	private Double exitAmount;

	/** 退出金额币种 */
	private String currencyExitamount;

	/** 售股对象 */
	private String saleObject;

	/** 总收入 */
	private Double revenue;

	/** 币种 */
	private String currencyRevenue;

	/** 总成本 */
	private Double cost;

	/** 币种 */
	private String currencyCost;

	/** 总盈利 */
	private Double profit;

	/** 币种 */
	private String currencyProfit;

	/** 原占股比 */
	private Double sharesRate;

	/** 卖出股数 */
	private Double sellShares;

	/** 每股价格 */
	private Double price;

	/** 币种 */
	private String currencyPrice;

	/** 每股盈利 */
	private Double earning;

	/** 币种 */
	private String currencyEarning;

	/** 股份公司 */
	private String company;

	/** 持股主体 */
	private String holderId;

	/** 资料清单 */
	private String docList;

	/** 申请原因 */
	private String appReasons;

	/** 备注 */
	private String remark;

	/** 附件 */
	private String attaFile;

	/** 状态（0：草稿，1：正式，9：删除） */
	private String status;
	private String statusName;

	//基金实缴规模
	private Double raiseAmount;

	//是否分配
	private String isDistribute;
	//分配金额
	private Double distributeMoney;
	//其中省政府投资基金分配额
	private Double distributeGovMoney;
	//分配金额
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date   distributeDt;

	public String getIsDistribute() {
		return isDistribute;
	}

	public void setIsDistribute(String isDistribute) {
		this.isDistribute = isDistribute;
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

	public Double getRaiseAmount() {
		return raiseAmount;
	}

	public void setRaiseAmount(Double raiseAmount) {
		this.raiseAmount = raiseAmount;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/** 流程编号 */
	private String processInstId;

	/** 项目编号 */
	private String projId;

	/** 折算为人民币 */
	private Double rmbExitAmount;

	/** 会议纪要 */
	private String quitAtta;

	/** 申请名称 */
	private String appName;

	/** 股比 */
	private Double shareRatio;

	/** 剩余部分所占股比 */
	private Double restShareRatio;

	/** 决策事项 */
	private String decisionDetail;

	/** 退出理由 */
	private String exitReason;

	/** 退出部分所占股比 */
	private Double exitShareRatio;

	/** 收回金额 */
	private Double backAmount;

	//认缴出资额
	private Double planAmount;
	//实缴出资额
	private Double paidAmount;


	private String meetingDetail;

	private String tag;
	private String quitProjId;

   //退出本金
	private String quitPrincipal;
	//退出收益
	private String quitProfit;


	public String getQuitPrincipal() {
		return quitPrincipal;
	}

	public void setQuitPrincipal(String quitPrincipal) {
		this.quitPrincipal = quitPrincipal;
	}

	public String getQuitProfit() {
		return quitProfit;
	}

	public void setQuitProfit(String quitProfit) {
		this.quitProfit = quitProfit;
	}

	public String getMeetingDetail() {
		return meetingDetail;
	}

	public void setMeetingDetail(String meetingDetail) {
		this.meetingDetail = meetingDetail;
	}

	public Double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getQuitProjId() {
		return quitProjId;
	}

	public void setQuitProjId(String quitProjId) {
		this.quitProjId = quitProjId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getAppUser() {
		return appUser;
	}

	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}

	public Date getAppDt() {
		return appDt;
	}

	public void setAppDt(Date appDt) {
		this.appDt = appDt;
	}

	public String getQuitType() {
		return quitType;
	}

	public void setQuitType(String quitType) {
		this.quitType = quitType;
	}

	public String getQuitWay() {
		return quitWay;
	}

	public void setQuitWay(String quitWay) {
		this.quitWay = quitWay;
	}

	public Date getQuitDt() {
		return quitDt;
	}

	public void setQuitDt(Date quitDt) {
		this.quitDt = quitDt;
	}

	public Double getExitAmount() {
		return exitAmount;
	}

	public void setExitAmount(Double exitAmount) {
		this.exitAmount = exitAmount;
	}

	public String getCurrencyExitamount() {
		return currencyExitamount;
	}

	public void setCurrencyExitamount(String currencyExitamount) {
		this.currencyExitamount = currencyExitamount;
	}

	public String getSaleObject() {
		return saleObject;
	}

	public void setSaleObject(String saleObject) {
		this.saleObject = saleObject;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public String getCurrencyRevenue() {
		return currencyRevenue;
	}

	public void setCurrencyRevenue(String currencyRevenue) {
		this.currencyRevenue = currencyRevenue;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getCurrencyCost() {
		return currencyCost;
	}

	public void setCurrencyCost(String currencyCost) {
		this.currencyCost = currencyCost;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getCurrencyProfit() {
		return currencyProfit;
	}

	public void setCurrencyProfit(String currencyProfit) {
		this.currencyProfit = currencyProfit;
	}

	public Double getSharesRate() {
		return sharesRate;
	}

	public void setSharesRate(Double sharesRate) {
		this.sharesRate = sharesRate;
	}

	public Double getSellShares() {
		return sellShares;
	}

	public void setSellShares(Double sellShares) {
		this.sellShares = sellShares;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrencyPrice() {
		return currencyPrice;
	}

	public void setCurrencyPrice(String currencyPrice) {
		this.currencyPrice = currencyPrice;
	}

	public Double getEarning() {
		return earning;
	}

	public void setEarning(Double earning) {
		this.earning = earning;
	}

	public String getCurrencyEarning() {
		return currencyEarning;
	}

	public void setCurrencyEarning(String currencyEarning) {
		this.currencyEarning = currencyEarning;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getHolderId() {
		return holderId;
	}

	public void setHolderId(String holderId) {
		this.holderId = holderId;
	}

	public String getDocList() {
		return docList;
	}

	public void setDocList(String docList) {
		this.docList = docList;
	}

	public String getAppReasons() {
		return appReasons;
	}

	public void setAppReasons(String appReasons) {
		this.appReasons = appReasons;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public Double getRmbExitAmount() {
		return rmbExitAmount;
	}

	public void setRmbExitAmount(Double rmbExitAmount) {
		this.rmbExitAmount = rmbExitAmount;
	}

	public String getQuitAtta() {
		return quitAtta;
	}

	public void setQuitAtta(String quitAtta) {
		this.quitAtta = quitAtta;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Double getShareRatio() {
		return shareRatio;
	}

	public void setShareRatio(Double shareRatio) {
		this.shareRatio = shareRatio;
	}

	public Double getRestShareRatio() {
		return restShareRatio;
	}

	public void setRestShareRatio(Double restShareRatio) {
		this.restShareRatio = restShareRatio;
	}

	public String getDecisionDetail() {
		return decisionDetail;
	}

	public void setDecisionDetail(String decisionDetail) {
		this.decisionDetail = decisionDetail;
	}

	public String getExitReason() {
		return exitReason;
	}

	public void setExitReason(String exitReason) {
		this.exitReason = exitReason;
	}

	public Double getExitShareRatio() {
		return exitShareRatio;
	}

	public void setExitShareRatio(Double exitShareRatio) {
		this.exitShareRatio = exitShareRatio;
	}

	public Double getBackAmount() {
		return backAmount;
	}

	public void setBackAmount(Double backAmount) {
		this.backAmount = backAmount;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}