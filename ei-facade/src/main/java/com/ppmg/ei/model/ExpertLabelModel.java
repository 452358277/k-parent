package com.ppmg.ei.model;



import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-12 15:01 
 */ 

public class ExpertLabelModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 标签ID */
	private String pkId;

	/** 专家ID */
	private String expertId;

	/** 关键字 */
	private String label;

	/** 备注 */
	private String remark;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getExpertId() {
		return expertId;
	}

	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}