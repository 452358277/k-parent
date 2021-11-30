package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-10-15 16:17 
 */ 
@Data 
public class ProjAppInfoQuitModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private String quitProjId;
	private String projId;

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	/** 退出立项名称 */
	private String quitName;

	/** 退出立项日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date quitDate;

	/** 退出方式 */
	private String quitWay;

	/** 投时估值（万元） */
	private Double whenCurrency;

	/** 退出时估值（万元) */
	private Double quitCurrency;

	/** 当前股比% */
	private Double nowShare;

	/** 转让出资额（万元） */
	private Double overAmount;

	/** 转让股比% */
	private Double overShare;

	/** 剩余股比% */
	private Double residueShare;

	/** 退出理由 */
	private String quitCause;

	/** 备注 */
	private String remark;

	/** 附件 */
	private String quitFile;

	/** 状态 */
	private String status;
	private String tag;
	/** 是否重大事项（0：是，1：否） */
	private String stopGreat;

	//流程实例id
	private String processInstId;
	//终止状态
	private String stopStatus;

	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getStopStatus() {
		return stopStatus;
	}

	public void setStopStatus(String stopStatus) {
		this.stopStatus = stopStatus;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getStopGreat() {
		return stopGreat;
	}

	public void setStopGreat(String stopGreat) {
		this.stopGreat = stopGreat;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getQuitProjId() {
		return quitProjId;
	}

	public void setQuitProjId(String quitProjId) {
		this.quitProjId = quitProjId;
	}

	public String getQuitName() {
		return quitName;
	}

	public void setQuitName(String quitName) {
		this.quitName = quitName;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	public String getQuitWay() {
		return quitWay;
	}

	public void setQuitWay(String quitWay) {
		this.quitWay = quitWay;
	}

	public Double getWhenCurrency() {
		return whenCurrency;
	}

	public void setWhenCurrency(Double whenCurrency) {
		this.whenCurrency = whenCurrency;
	}

	public Double getQuitCurrency() {
		return quitCurrency;
	}

	public void setQuitCurrency(Double quitCurrency) {
		this.quitCurrency = quitCurrency;
	}

	public Double getNowShare() {
		return nowShare;
	}

	public void setNowShare(Double nowShare) {
		this.nowShare = nowShare;
	}

	public Double getOverAmount() {
		return overAmount;
	}

	public void setOverAmount(Double overAmount) {
		this.overAmount = overAmount;
	}

	public Double getOverShare() {
		return overShare;
	}

	public void setOverShare(Double overShare) {
		this.overShare = overShare;
	}

	public Double getResidueShare() {
		return residueShare;
	}

	public void setResidueShare(Double residueShare) {
		this.residueShare = residueShare;
	}

	public String getQuitCause() {
		return quitCause;
	}

	public void setQuitCause(String quitCause) {
		this.quitCause = quitCause;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQuitFile() {
		return quitFile;
	}

	public void setQuitFile(String quitFile) {
		this.quitFile = quitFile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}