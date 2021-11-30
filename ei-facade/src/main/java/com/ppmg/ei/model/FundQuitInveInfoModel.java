package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

public class FundQuitInveInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String fqiId;

	/** 基金表主键 */
	private String fundId;

	/** 项目表主键 */
	private String projId;

	/** 投资项目数量 */
	private Long inveProjNum;

	/** 投资企业数量 */
	private Long inveEntNum;

	/** 投资项目企业金额 */
	private Double inveAmt;

	/** 投资省内企业数量 */
	private Long inveProvinceNum;

	/** 投资省内企业金额 */
	private Double inveProvinceAmt;

	/** 已上市数量 */
	private Long inveListedNum;

	/** 已申报IPO数量 */
	private Long inveIpoNum;

	/** 已挂牌新三板数量 */
	private Long inveNewThirdNum;

	/** 删除标记 */
	private String isDelete;

	/** 排序 */
	private Long sortOrder;

	/** 版本号 */
	private Long rowVersion;

	public String getFqiId() {
		return fqiId;
	}

	public void setFqiId(String fqiId) {
		this.fqiId = fqiId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public Long getInveProjNum() {
		return inveProjNum;
	}

	public void setInveProjNum(Long inveProjNum) {
		this.inveProjNum = inveProjNum;
	}

	public Long getInveEntNum() {
		return inveEntNum;
	}

	public void setInveEntNum(Long inveEntNum) {
		this.inveEntNum = inveEntNum;
	}

	public Double getInveAmt() {
		return inveAmt;
	}

	public void setInveAmt(Double inveAmt) {
		this.inveAmt = inveAmt;
	}

	public Long getInveProvinceNum() {
		return inveProvinceNum;
	}

	public void setInveProvinceNum(Long inveProvinceNum) {
		this.inveProvinceNum = inveProvinceNum;
	}

	public Double getInveProvinceAmt() {
		return inveProvinceAmt;
	}

	public void setInveProvinceAmt(Double inveProvinceAmt) {
		this.inveProvinceAmt = inveProvinceAmt;
	}

	public Long getInveListedNum() {
		return inveListedNum;
	}

	public void setInveListedNum(Long inveListedNum) {
		this.inveListedNum = inveListedNum;
	}

	public Long getInveIpoNum() {
		return inveIpoNum;
	}

	public void setInveIpoNum(Long inveIpoNum) {
		this.inveIpoNum = inveIpoNum;
	}

	public Long getInveNewThirdNum() {
		return inveNewThirdNum;
	}

	public void setInveNewThirdNum(Long inveNewThirdNum) {
		this.inveNewThirdNum = inveNewThirdNum;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getRowVersion() {
		return rowVersion;
	}

	public void setRowVersion(Long rowVersion) {
		this.rowVersion = rowVersion;
	}

}