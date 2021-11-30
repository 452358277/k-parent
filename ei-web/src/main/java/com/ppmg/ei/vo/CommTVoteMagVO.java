package com.ppmg.ei.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;

public class CommTVoteMagVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 投票汇总结果（1:同意 ,2: 不同意,3: 暂缓） */
	private String voteResult;

	/** 汇总意见 */
	private String collectOpinion;

	/** 决议人姓名 */
	private String decisionName;

	/** 决议时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date decisionDt;

	/** 决议结果（1：同意，2：退回） */
	private String decisionResult;

	public String getVoteResult() {
		return voteResult;
	}

	public void setVoteResult(String voteResult) {
		this.voteResult = voteResult;
	}

	public String getCollectOpinion() {
		return collectOpinion;
	}

	public void setCollectOpinion(String collectOpinion) {
		this.collectOpinion = collectOpinion;
	}

	public String getDecisionName() {
		return decisionName;
	}

	public void setDecisionName(String decisionName) {
		this.decisionName = decisionName;
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


}