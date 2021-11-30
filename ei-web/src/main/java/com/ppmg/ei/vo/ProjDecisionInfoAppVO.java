package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.EiTAttachmentModel;
import com.ppmg.ei.model.YhStaffForDecisionModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ApiModel(value="项目决策信息对象",description="项目决策信息对象")
public class ProjDecisionInfoAppVO extends BaseVO{

	private static final long serialVersionUID = -3433724188091439299L;

	/** 项目 */
	private String projId;

	/** 企业 */
	private String enterpriseId;

	/** 董事席数 */
	private Long directorCount;

	/** 元禾董事席数 */
	private Long yhDireCount;

	/** 监事席数 */
	private Long supervisorCount;

	/** 元禾监事席数 */
	private Long yhSupeCount;

	/** 流程实例号 */
	private String processInstId;

	/** null */
	private String projGuid;

	/** 决策会议记录 */
	private String decisionAtta;

	/** 拟投资金额 */
	private Double intendedAmount;

	/** 拟投资币种 */
	private String intendedCurrency;

	/** 出资主体编号 */
	private String inveId;

	/** 出资主体名称 */
	private String inveName;

	/** 决策材料 */
	private List fileLists;

	/** 委派董监高 */
	private List DJGLists;

	/** 项目决策时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
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

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getDirectorCount() {
		return directorCount;
	}

	public void setDirectorCount(Long directorCount) {
		this.directorCount = directorCount;
	}

	public Long getYhDireCount() {
		return yhDireCount;
	}

	public void setYhDireCount(Long yhDireCount) {
		this.yhDireCount = yhDireCount;
	}

	public Long getSupervisorCount() {
		return supervisorCount;
	}

	public void setSupervisorCount(Long supervisorCount) {
		this.supervisorCount = supervisorCount;
	}

	public Long getYhSupeCount() {
		return yhSupeCount;
	}

	public void setYhSupeCount(Long yhSupeCount) {
		this.yhSupeCount = yhSupeCount;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getProjGuid() {
		return projGuid;
	}

	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}

	public String getDecisionAtta() {
		return decisionAtta;
	}

	public void setDecisionAtta(String decisionAtta) {
		this.decisionAtta = decisionAtta;
	}

	public Double getIntendedAmount() {
		return intendedAmount;
	}

	public void setIntendedAmount(Double intendedAmount) {
		this.intendedAmount = intendedAmount;
	}

	public String getIntendedCurrency() {
		return intendedCurrency;
	}

	public void setIntendedCurrency(String intendedCurrency) {
		this.intendedCurrency = intendedCurrency;
	}

	public String getInveId() {
		return inveId;
	}

	public void setInveId(String inveId) {
		this.inveId = inveId;
	}

	public String getInveName() {
		return inveName;
	}

	public void setInveName(String inveName) {
		this.inveName = inveName;
	}

	public List getFileLists() {
		return fileLists;
	}

	public void setFileLists(List fileLists) {
		this.fileLists = fileLists;
	}

	public List getDJGLists() {
		return DJGLists;
	}

	public void setDJGLists(List DJGLists) {
		this.DJGLists = DJGLists;
	}

	
	
}
