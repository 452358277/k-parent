package com.ppmg.ei.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class CommTVoteMagModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String voteId;

	/** 投票主题 */
	private String voteSubject;

	/** 投票描述 */
	private String voteDesc;

	/** 关联项目（退出决策时存的企业ID） */
	private String relProj;

	/** 事项类型（1：立项会议，2:投资决策会议，3：三会事项，4：非三会事项，5：退出决策会议） */
	private String subjType;

	/** 关联项目会议 */
	private String relMrp;

	/** 投票汇总结果（1:通过 ,2: 不通过,3: 暂缓） */
	private String voteResult;

	/** 流程实例 */
	private String processInstId;

	/** 状态（0：草稿，1：正式，2：删除） */
	private String status;

	/** 决议人 */
	private String decisionId;

	/** 决议时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date decisionDt;

	/** 决议结果（1：签发，2：退回） */
	private String decisionResult;

	/** 备注 */
	private String remark;

	/** 汇总意见 */
	private String collectOpinion;

	/** 决议意见 */
	private String decisionOpinion;

	/** 项目类型（1:基金融资,2:投企业项目包括投前投后,3:投基金项目） */
	private String projType;

	/** 所属公司ID */
	private Long groupId;

	/** 决议人姓名 */
	private String decisionName;

	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public String getVoteSubject() {
		return voteSubject;
	}

	public void setVoteSubject(String voteSubject) {
		this.voteSubject = voteSubject;
	}

	public String getVoteDesc() {
		return voteDesc;
	}

	public void setVoteDesc(String voteDesc) {
		this.voteDesc = voteDesc;
	}

	public String getRelProj() {
		return relProj;
	}

	public void setRelProj(String relProj) {
		this.relProj = relProj;
	}

	public String getSubjType() {
		return subjType;
	}

	public void setSubjType(String subjType) {
		this.subjType = subjType;
	}

	public String getRelMrp() {
		return relMrp;
	}

	public void setRelMrp(String relMrp) {
		this.relMrp = relMrp;
	}

	public String getVoteResult() {
		return voteResult;
	}

	public void setVoteResult(String voteResult) {
		this.voteResult = voteResult;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDecisionId() {
		return decisionId;
	}

	public void setDecisionId(String decisionId) {
		this.decisionId = decisionId;
	}

	public Date getDecisionDt() {
		return decisionDt;
	}

	public void setDecisionDt(Date decisionDt) {
		this.decisionDt = decisionDt;
	}

	public String getDecisionResult() {
		return decisionResult;
	}

	public void setDecisionResult(String decisionResult) {
		this.decisionResult = decisionResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCollectOpinion() {
		return collectOpinion;
	}

	public void setCollectOpinion(String collectOpinion) {
		this.collectOpinion = collectOpinion;
	}

	public String getDecisionOpinion() {
		return decisionOpinion;
	}

	public void setDecisionOpinion(String decisionOpinion) {
		this.decisionOpinion = decisionOpinion;
	}

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getDecisionName() {
		return decisionName;
	}

	public void setDecisionName(String decisionName) {
		this.decisionName = decisionName;
	}

}