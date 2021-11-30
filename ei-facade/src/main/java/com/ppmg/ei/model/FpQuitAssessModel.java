package com.ppmg.ei.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class FpQuitAssessModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** null */
	private String assessId;

	/** 项目编号 */
	private String projId;

	/** 评估人 */
	private String assessBy;

	/** 评估日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date assessDt;

	/** 评估总结 */
	private String assessSummary;

	/** 评估附件 */
	private String attaFile;

	private List<EiTAttachmentModel> attaFileList;

	/** 状态（0：草稿，1：正常，9：删除） */
	private String status;

	private EiTAttachmentModel assessAttModel;

	public String getAssessId() {
		return assessId;
	}

	public void setAssessId(String assessId) {
		this.assessId = assessId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getAssessBy() {
		return assessBy;
	}

	public void setAssessBy(String assessBy) {
		this.assessBy = assessBy;
	}

	public Date getAssessDt() {
		return assessDt;
	}

	public void setAssessDt(Date assessDt) {
		this.assessDt = assessDt;
	}

	public String getAssessSummary() {
		return assessSummary;
	}

	public void setAssessSummary(String assessSummary) {
		this.assessSummary = assessSummary;
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

	public EiTAttachmentModel getAssessAttModel() {
		return assessAttModel;
	}

	public void setAssessAttModel(EiTAttachmentModel assessAttModel) {
		this.assessAttModel = assessAttModel;
	}

	public List<EiTAttachmentModel> getAttaFileList() {
		return attaFileList;
	}

	public void setAttaFileList(List<EiTAttachmentModel> attaFileList) {
		this.attaFileList = attaFileList;
	}

}