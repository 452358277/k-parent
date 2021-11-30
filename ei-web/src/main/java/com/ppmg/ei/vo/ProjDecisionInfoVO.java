package com.ppmg.ei.vo;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.EiTAttachmentModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value="项目决策信息对象",description="项目决策信息对象")
public class ProjDecisionInfoVO extends BaseVO{

	private static final long serialVersionUID = -3433724188091439299L;

	/** 主键 */
	private String projId;

	/** 出资主体 */
	@ApiModelProperty(value="出资主体",name="inveName")
	private String inveName;

	/** 拟投资比例不超过 */
	@ApiModelProperty(value="拟投资比例不超过",name="perShare")
	private String perShare;
	
	/** 拟投资金额不超过 */
	@ApiModelProperty(value="拟投资金额不超过",name="intendedAmount")
	private String intendedAmount;
	
	/** 拟投资金额币种 */
	@ApiModelProperty(value="拟投资金额币种",name="intendedCurrency")
	private String intendedCurrency;

	/** 折算为人民币 */
	@ApiModelProperty(value="折算为人民币",name="rmbIntendedAmount")
	private Double rmbIntendedAmount;

	/** 决策事项 */
	@ApiModelProperty(value="决策事项",name="majorMatter")
	private String majorMatter;
	
	/** 投资理由 */
	@ApiModelProperty(value="投资理由",name="inveReason")
	private String inveReason;

	/** 咨询委员会成员姓名 */
	private String advisoryCommName;

	/** 决策相关材料 */
	private List<EiTAttachmentVO> remarkAttList;

	/** 决策会议纪要 */
	private List<EiTAttachmentModel> decisionAttList;

	/** 投资决策时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date decisionDt;

	public Date getDecisionDt() {
		return decisionDt;
	}

	public void setDecisionDt(Date decisionDt) {
		this.decisionDt = decisionDt;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public String getPerShare() {
		return perShare;
	}

	public void setPerShare(String perShare) {
		this.perShare = perShare;
	}

	public String getIntendedAmount() {
		return intendedAmount;
	}

	public void setIntendedAmount(String intendedAmount) {
		this.intendedAmount = intendedAmount;
	}

	public String getMajorMatter() {
		return majorMatter;
	}

	public void setMajorMatter(String majorMatter) {
		this.majorMatter = majorMatter;
	}

	public String getInveReason() {
		return inveReason;
	}

	public void setInveReason(String inveReason) {
		this.inveReason = inveReason;
	}

	public String getIntendedCurrency() {
		return intendedCurrency;
	}

	public void setIntendedCurrency(String intendedCurrency) {
		this.intendedCurrency = intendedCurrency;
	}

	public Double getRmbIntendedAmount() {
		return rmbIntendedAmount;
	}

	public void setRmbIntendedAmount(Double rmbIntendedAmount) {
		this.rmbIntendedAmount = rmbIntendedAmount;
	}

	public String getAdvisoryCommName() {
		return advisoryCommName;
	}

	public void setAdvisoryCommName(String advisoryCommName) {
		this.advisoryCommName = advisoryCommName;
	}

	public List<EiTAttachmentVO> getRemarkAttList() {
		return remarkAttList;
	}

	public void setRemarkAttList(List<EiTAttachmentVO> remarkAttList) {
		this.remarkAttList = remarkAttList;
	}

	public List<EiTAttachmentModel> getDecisionAttList() {
		return decisionAttList;
	}

	public void setDecisionAttList(List<EiTAttachmentModel> decisionAttList) {
		this.decisionAttList = decisionAttList;
	}

	
	
	
}
