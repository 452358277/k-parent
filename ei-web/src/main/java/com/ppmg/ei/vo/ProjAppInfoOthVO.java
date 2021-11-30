package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProjAppInfoOthVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String pkId;

	/** 投资决策编号 */
	private String deciNo;

	/** 项目ID */
	private String projId;

	/** 项目名称 */
	private String projName;

	/** 投资类型 */
	private String inveType;

	/** 项目归属行业 */
	private String industry;

	/** 决策评审日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date deciDate;

	/** 所属部门ID */
	private String deptId;

	/** 所属公司ID */
	private String groupId;

	/** 流程实例号 */
	private String processInstId;

	/** 附件 */
	private String attaFile;

	/** 状态 PARENT_ID=264 */
	private String status;

	/** 项目全称 */
	private String projFullName;

	/** 所属公司（公司-部门） */
	private String groupName;

	private FixflowRunTaskinstanceModel instanceModel;

	public FixflowRunTaskinstanceModel getInstanceModel() {
		return instanceModel;
	}

	public void setInstanceModel(FixflowRunTaskinstanceModel instanceModel) {
		this.instanceModel = instanceModel;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getDeciNo() {
		return deciNo;
	}

	public void setDeciNo(String deciNo) {
		this.deciNo = deciNo;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getInveType() {
		return inveType;
	}

	public void setInveType(String inveType) {
		this.inveType = inveType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Date getDeciDate() {
		return deciDate;
	}

	public void setDeciDate(Date deciDate) {
		this.deciDate = deciDate;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
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

	public String getProjFullName() {
		return projFullName;
	}

	public void setProjFullName(String projFullName) {
		this.projFullName = projFullName;
	}

}