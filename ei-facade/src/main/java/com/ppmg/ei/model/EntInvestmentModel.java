package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class EntInvestmentModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 对外投资主键 */
	private String investmentId;

	/** 企业ID */
	private String enterpriseId;

	/** 被投企业企查查内部编号 */
	private String keyNo;

	/** 被投企业公司名称 */
	private String name;

	/** 被投企业注册号 */
	private String no;

	/** 被投企业登记机关 */
	private String belongOrg;

	/** 被投企业法定代表人 */
	private String operName;

	/** 被投企业成立日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date startDate;

	/** 被投企业注销/吊销日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date endDate;

	/** 被投企业登记状态 */
	private String status;

	/** 被投企业省份 */
	private String privince;

	/** 被投企业变更日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date updateDate;

	/** 被投企业信用代码 */
	private String creditCode;

	/** 被投企业注册资本 */
	private String registCapi;

	/** 被投企业类型 */
	private String econKind;

	/** 被投企业公司地址 */
	private String address;

	/** 被投企业经营范围 */
	private String scope;

	/** 删除标志(0正常，1删除) */
	private String deleteFlag;

	public String getInvestmentId() {
		return investmentId;
	}

	public void setInvestmentId(String investmentId) {
		this.investmentId = investmentId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getKeyNo() {
		return keyNo;
	}

	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBelongOrg() {
		return belongOrg;
	}

	public void setBelongOrg(String belongOrg) {
		this.belongOrg = belongOrg;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrivince() {
		return privince;
	}

	public void setPrivince(String privince) {
		this.privince = privince;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getRegistCapi() {
		return registCapi;
	}

	public void setRegistCapi(String registCapi) {
		this.registCapi = registCapi;
	}

	public String getEconKind() {
		return econKind;
	}

	public void setEconKind(String econKind) {
		this.econKind = econKind;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}