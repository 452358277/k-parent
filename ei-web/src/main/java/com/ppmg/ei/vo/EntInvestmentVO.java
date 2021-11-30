package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EntInvestmentVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 企业ID */
	private String enterpriseId;

	/** 被投企业公司名称 */
	private String name;

	/** 被投企业法定代表人 */
	private String operName;

	/** 被投企业成立日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date startDate;

	/** 被投企业信用代码 */
	private String creditCode;

	/** 被投企业注册资本 */
	private String registCapi;

	/** 被投企业登记状态 */
	private String status;

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}