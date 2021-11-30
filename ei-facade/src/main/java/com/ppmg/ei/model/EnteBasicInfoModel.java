package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EnteBasicInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String pkId;

	/** 企业id */
	private String enteId;

	/** 项目投资时就业人数 */
	private String inveEmployment;

	/** 就业人员 */
	private String employment;

	/** 授权专利数 */
	private String empowerPatent;

	/** 未授权专利数 */
	private String noEmpowerPatent;

	/** 上年度纳税额 */
	private String lastTaxAmount;

	/** 上年度预计纳税额 */
	private String lastEstimateTaxAmount;

	/** 年度纳税额 */
	private String yearTaxAmount;

	/** 年度 */
	private String year;

	/** 附件 */
	private String enteFile;

	/** 描述 */
	private String remark;

	/** 其他时间-备用字段 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date fillingDate;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getEnteId() {
		return enteId;
	}

	public void setEnteId(String enteId) {
		this.enteId = enteId;
	}

	public String getInveEmployment() {
		return inveEmployment;
	}

	public void setInveEmployment(String inveEmployment) {
		this.inveEmployment = inveEmployment;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public String getEmpowerPatent() {
		return empowerPatent;
	}

	public void setEmpowerPatent(String empowerPatent) {
		this.empowerPatent = empowerPatent;
	}

	public String getNoEmpowerPatent() {
		return noEmpowerPatent;
	}

	public void setNoEmpowerPatent(String noEmpowerPatent) {
		this.noEmpowerPatent = noEmpowerPatent;
	}

	public String getLastTaxAmount() {
		return lastTaxAmount;
	}

	public void setLastTaxAmount(String lastTaxAmount) {
		this.lastTaxAmount = lastTaxAmount;
	}

	public String getLastEstimateTaxAmount() {
		return lastEstimateTaxAmount;
	}

	public void setLastEstimateTaxAmount(String lastEstimateTaxAmount) {
		this.lastEstimateTaxAmount = lastEstimateTaxAmount;
	}

	public String getYearTaxAmount() {
		return yearTaxAmount;
	}

	public void setYearTaxAmount(String yearTaxAmount) {
		this.yearTaxAmount = yearTaxAmount;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getEnteFile() {
		return enteFile;
	}

	public void setEnteFile(String enteFile) {
		this.enteFile = enteFile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getFillingDate() {
		return fillingDate;
	}

	public void setFillingDate(Date fillingDate) {
		this.fillingDate = fillingDate;
	}

}