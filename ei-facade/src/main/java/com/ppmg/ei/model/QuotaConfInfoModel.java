package com.ppmg.ei.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class QuotaConfInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 所属年份 */
	private String belongYear;

	/** 所属公司ID */
	private String belongId;

	/** 考核结果更新最新季度 */
	private String newQuarter;

	/** 状态（0:正常 9:删除） */
	private String status;

	/** 计划填报人 */
	private String planBy;

	/** 计划填报时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date planDt;

	/** 结果填报人 */
	private String resultBy;

	/** 结果填报时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date resultDt;

	/** 一季度备注 */
	private String remark1;

	/** 二季度备注 */
	private String remark2;

	/** 三季度备注 */
	private String remark3;

	/** 四季度备注 */
	private String remark4;

	/** 一季度附件 */
	private String att1;

	/** 二季度附件 */
	private String att2;

	/** 三季度附件 */
	private String att3;

	/** 四季度附件 */
	private String att4;

	/** 流程名称 */
	private String statusName;

	/** 流程ID */
	private String processInstId;

	/** 经营计划标题 */
	private String quotaTitle;

	/** 所属公司名称 */
	private String belongName;

	private List<FounderFileInfoModel> Att1List;

	private List<FounderFileInfoModel> Att2List;

	private List<FounderFileInfoModel> Att3List;

	private List<FounderFileInfoModel> Att4List;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getBelongYear() {
		return belongYear;
	}

	public void setBelongYear(String belongYear) {
		this.belongYear = belongYear;
	}

	public String getBelongId() {
		return belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	public String getNewQuarter() {
		return newQuarter;
	}

	public void setNewQuarter(String newQuarter) {
		this.newQuarter = newQuarter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlanBy() {
		return planBy;
	}

	public void setPlanBy(String planBy) {
		this.planBy = planBy;
	}

	public Date getPlanDt() {
		return planDt;
	}

	public void setPlanDt(Date planDt) {
		this.planDt = planDt;
	}

	public String getResultBy() {
		return resultBy;
	}

	public void setResultBy(String resultBy) {
		this.resultBy = resultBy;
	}

	public Date getResultDt() {
		return resultDt;
	}

	public void setResultDt(Date resultDt) {
		this.resultDt = resultDt;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getAtt1() {
		return att1;
	}

	public void setAtt1(String att1) {
		this.att1 = att1;
	}

	public String getAtt2() {
		return att2;
	}

	public void setAtt2(String att2) {
		this.att2 = att2;
	}

	public String getAtt3() {
		return att3;
	}

	public void setAtt3(String att3) {
		this.att3 = att3;
	}

	public String getAtt4() {
		return att4;
	}

	public void setAtt4(String att4) {
		this.att4 = att4;
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

	public String getQuotaTitle() {
		return quotaTitle;
	}

	public void setQuotaTitle(String quotaTitle) {
		this.quotaTitle = quotaTitle;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public List<FounderFileInfoModel> getAtt1List() {
		return Att1List;
	}

	public void setAtt1List(List<FounderFileInfoModel> att1List) {
		Att1List = att1List;
	}

	public List<FounderFileInfoModel> getAtt2List() {
		return Att2List;
	}

	public void setAtt2List(List<FounderFileInfoModel> att2List) {
		Att2List = att2List;
	}

	public List<FounderFileInfoModel> getAtt3List() {
		return Att3List;
	}

	public void setAtt3List(List<FounderFileInfoModel> att3List) {
		Att3List = att3List;
	}

	public List<FounderFileInfoModel> getAtt4List() {
		return Att4List;
	}

	public void setAtt4List(List<FounderFileInfoModel> att4List) {
		Att4List = att4List;
	}
}