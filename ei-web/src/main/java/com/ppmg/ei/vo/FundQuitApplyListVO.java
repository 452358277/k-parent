package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundQuitApplyListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** null */
	private String appId;

	/** 退出金额折算为人民币 */
	private Double rmbExitAmount;

	/** 退出日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date quitDt;

	/** 备注 */
	private String remark;

	/** 退出类型（1：部分退出，2：全部退出） */
	private String quitType;

	/** 状态（0：草稿，1：正式，9：删除） */
	private String status;

	/** 状态（0：草稿，1：正式，9：删除） */
	private String statusName;

	/** 流程编号 */
	private String processInstId;

	/** 退出金额 */
	private Double exitAmount;

	private String quitTypeName;

	private String quitWayName;

	private String quitWay;

	/** 附件 */
	private String attaFile;

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


	private Double backAmount;

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

	public Double getBackAmount() {
		return backAmount;
	}

	public void setBackAmount(Double backAmount) {
		this.backAmount = backAmount;
	}

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

	public String getAttaFile() {
		return attaFile;
	}

	public void setAttaFile(String attaFile) {
		this.attaFile = attaFile;
	}

	public String getQuitWay() {
		return quitWay;
	}

	public void setQuitWay(String quitWay) {
		this.quitWay = quitWay;
	}

	public String getQuitWayName() {
		return quitWayName;
	}

	public void setQuitWayName(String quitWayName) {
		this.quitWayName = quitWayName;
	}

	public String getQuitTypeName() {
		return quitTypeName;
	}

	public void setQuitTypeName(String quitTypeName) {
		this.quitTypeName = quitTypeName;
	}

	public Double getExitAmount() {
		return exitAmount;
	}

	public void setExitAmount(Double exitAmount) {
		this.exitAmount = exitAmount;
	}

	private FixflowRunTaskinstanceModel instanceModel;

	public Double getRmbExitAmount() {
		return rmbExitAmount;
	}

	public void setRmbExitAmount(Double rmbExitAmount) {
		this.rmbExitAmount = rmbExitAmount;
	}

	public String getQuitType() {
		return quitType;
	}

	public void setQuitType(String quitType) {
		this.quitType = quitType;
	}

	public Date getQuitDt() {
		return quitDt;
	}

	public void setQuitDt(Date quitDt) {
		this.quitDt = quitDt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public FixflowRunTaskinstanceModel getInstanceModel() {
		return instanceModel;
	}

	public void setInstanceModel(FixflowRunTaskinstanceModel instanceModel) {
		this.instanceModel = instanceModel;
	}

}