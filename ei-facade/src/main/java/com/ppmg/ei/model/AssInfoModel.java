package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class AssInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 考核信息ID */
	private String infoId;

	/** 项目ID */
	private String projId;

	/** 考核对象(0公司1部门2个人) */
	private String assObj;

	/** 对象ID */
	private String assObjId;

	/** 岗位ID(针对个人考核) */
	private String postId;

	/** 公司ID(针对个人考核) */
	private String companyId;

	/** 部门ID(针对个人考核) */
	private String deptId;

	/** 考核名称 */
	private String infoName;

	/** 状态 */
	private String status;

	/** 指标设定流程ID */
	private String setProcessId;

	/** 最终得分 */
	private String finalScore;

	/** 备注 */
	private String remark;

	/** 附件 */
	private String infoAtta;

	/** null */
	private String isDelete;

	/** null */
	private String undefined1;

	/** null */
	private String undefined2;

	/** null */
	private String undefined3;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date undefined4;

	/** null */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date undefined5;

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getAssObj() {
		return assObj;
	}

	public void setAssObj(String assObj) {
		this.assObj = assObj;
	}

	public String getAssObjId() {
		return assObjId;
	}

	public void setAssObjId(String assObjId) {
		this.assObjId = assObjId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSetProcessId() {
		return setProcessId;
	}

	public void setSetProcessId(String setProcessId) {
		this.setProcessId = setProcessId;
	}

	public String getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInfoAtta() {
		return infoAtta;
	}

	public void setInfoAtta(String infoAtta) {
		this.infoAtta = infoAtta;
	}

	public String getUndefined1() {
		return undefined1;
	}

	public void setUndefined1(String undefined1) {
		this.undefined1 = undefined1;
	}

	public String getUndefined2() {
		return undefined2;
	}

	public void setUndefined2(String undefined2) {
		this.undefined2 = undefined2;
	}

	public String getUndefined3() {
		return undefined3;
	}

	public void setUndefined3(String undefined3) {
		this.undefined3 = undefined3;
	}

	public Date getUndefined4() {
		return undefined4;
	}

	public void setUndefined4(Date undefined4) {
		this.undefined4 = undefined4;
	}

	public Date getUndefined5() {
		return undefined5;
	}

	public void setUndefined5(Date undefined5) {
		this.undefined5 = undefined5;
	}

}