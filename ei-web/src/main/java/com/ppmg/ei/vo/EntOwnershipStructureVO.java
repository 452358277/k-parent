package com.ppmg.ei.vo;

import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;

public class EntOwnershipStructureVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 股东ID */
	private String stockId;

	/** 企业ID */
	private String enterpriseId;

	/** 股东/发起人类型 */
	private String stockType;

	/** 股份占比 */
	private String stockPercent;

	/** 认缴出资额 */
	private String shouldCapi;

	/** 认缴出资日期 */
	private String shouldDate;

	/** 认缴出资方式 */
	private String investType;

	/** 实缴出资方式 */
	private String investName;

	/** 实缴出资额 */
	private String realCapi;

	/** 实缴出资日期 */
	private String capiDate;

	/** 是否参与经营 */
	private String joinOperator;

	/** 与实际控制人关系 */
	private String relation;

	/** 备注 */
	private String remark;

	/** 删除标志(0正常，1删除) */
	private String deleteFlag;

	/** 股东名称 */
	private String stockName;

	/** null */
	private String stockName1;

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getStockPercent() {
		return stockPercent;
	}

	public void setStockPercent(String stockPercent) {
		this.stockPercent = stockPercent;
	}

	public String getShouldCapi() {
		return shouldCapi;
	}

	public void setShouldCapi(String shouldCapi) {
		this.shouldCapi = shouldCapi;
	}

	public String getShouldDate() {
		return shouldDate;
	}

	public void setShouldDate(String shouldDate) {
		this.shouldDate = shouldDate;
	}

	public String getInvestType() {
		return investType;
	}

	public void setInvestType(String investType) {
		this.investType = investType;
	}

	public String getInvestName() {
		return investName;
	}

	public void setInvestName(String investName) {
		this.investName = investName;
	}

	public String getRealCapi() {
		return realCapi;
	}

	public void setRealCapi(String realCapi) {
		this.realCapi = realCapi;
	}

	public String getCapiDate() {
		return capiDate;
	}

	public void setCapiDate(String capiDate) {
		this.capiDate = capiDate;
	}

	public String getJoinOperator() {
		return joinOperator;
	}

	public void setJoinOperator(String joinOperator) {
		this.joinOperator = joinOperator;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockName1() {
		return stockName1;
	}

	public void setStockName1(String stockName1) {
		this.stockName1 = stockName1;
	}

}