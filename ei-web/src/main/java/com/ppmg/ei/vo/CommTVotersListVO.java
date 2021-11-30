package com.ppmg.ei.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;

public class CommTVotersListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 投票号 */
	private String voteId;

	/** 投票人名称 */
	private String voterName;

	/** 投票时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date voteDt;

	/** 投票结果（1:同意,2: 不同意,3: 附条件同意） */
	private String voteResult;

	/** 备注 */
	private String remark;

	/** 代投人 */
	private String investId;

	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public Date getVoteDt() {
		return voteDt;
	}

	public void setVoteDt(Date voteDt) {
		this.voteDt = voteDt;
	}

	public String getVoteResult() {
		return voteResult;
	}

	public void setVoteResult(String voteResult) {
		this.voteResult = voteResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}


}