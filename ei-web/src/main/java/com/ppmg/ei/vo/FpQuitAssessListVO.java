package com.ppmg.ei.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;

public class FpQuitAssessListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** null */
	private String assessId;

	/** 评估人 */
	private String assessBy;

	/** 评估日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date assessDt;

	/** 评估总结 */
	private String assessSummary;

	private List<EiTAttachmentVO> attaFileList;

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

	public String getAssessId() {
		return assessId;
	}

	public void setAssessId(String assessId) {
		this.assessId = assessId;
	}

	public List<EiTAttachmentVO> getAttaFileList() {
		return attaFileList;
	}

	public void setAttaFileList(List<EiTAttachmentVO> attaFileList) {
		this.attaFileList = attaFileList;
	}


}