package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AssInfoListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 考核信息ID */
	private String infoId;

	/** 项目ID */
	private String projId;

	/** 考核对象(0公司1部门2个人) */
	private String assObj;

	/** 对象ID */
	private String assObjId;

	/** 对象ID */
	private String assObjName;

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

	/** 项目名称 */
	private String projName;

	/** 项目名称 */
	private String createName;

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

	public String getAssObjName() {
		return assObjName;
	}

	public void setAssObjName(String assObjName) {
		this.assObjName = assObjName;
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

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

}