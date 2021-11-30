package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FundListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String fundId;

	/** 基金名称 */
	private String fundName;

	/** 管理公司 */
	private String mcName;

	/** 基金状态(1：已登记，2：申请中，3：已立项，4：投资期，5：回收期，6：延展期，7：已关闭，8：已删除，9：已废弃) */
	private String fundSts;

	/** 实缴比例=当前实缴规模/当前认缴规模 */
	private String raiseCurrent;

	/** 目标规模(亿元) */
	private String planAmountDisplay;

	/** 认缴规模(亿元) */
	private String currentAmountDisplay;

	/** 实缴规模(亿元) */
	private String raiseAmountDisplay;

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getMcName() {
		return mcName;
	}

	public void setMcName(String mcName) {
		this.mcName = mcName;
	}

	public String getFundSts() {
		return fundSts;
	}

	public void setFundSts(String fundSts) {
		this.fundSts = fundSts;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
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

}