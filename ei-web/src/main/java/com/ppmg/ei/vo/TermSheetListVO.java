package com.ppmg.ei.vo;


import java.util.List;

import com.founder.ssm.core.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

public class TermSheetListVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="主键",name="sheetId")
	private String sheetId;

	/** 项目 */
	@ApiModelProperty(value="项目ID",name="projId")
	private String projId;

	/** 意向书类型 */
	@ApiModelProperty(value="意向书类型",name="sheetType")
	private String sheetType;

	/** 状态（0：草稿，1：审核中，2：已审核，9：删除） */
	@ApiModelProperty(value="状态（0：草稿，1：审核中，2：已审核，9：删除）",name="status")
	private String status;

	/** 状态（0：草稿，1：审核中，2：已审核，9：删除） */
	@ApiModelProperty(value="状态（0：草稿，1：审核中，2：已审核，9：删除）",name="status")
	private String statusName;

	private List<EiTAttachmentVO> eDocAttList;
	
	private List<EiTAttachmentVO> formVersionAttList;

	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<EiTAttachmentVO> geteDocAttList() {
		return eDocAttList;
	}

	public void seteDocAttList(List<EiTAttachmentVO> eDocAttList) {
		this.eDocAttList = eDocAttList;
	}

	public List<EiTAttachmentVO> getFormVersionAttList() {
		return formVersionAttList;
	}

	public void setFormVersionAttList(List<EiTAttachmentVO> formVersionAttList) {
		this.formVersionAttList = formVersionAttList;
	}

}