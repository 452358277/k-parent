package com.ppmg.ei.model;



import com.founder.ssm.core.model.BaseModel;

import java.util.List;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-12 15:01 
 */ 

public class ExpertLabelSqlModel extends BaseModel {

	/** 标签ID */
	private String pkId;

	/** 专家ID */
	private List<String> expertIds;

	/** 关键字 */
	private String label;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public List<String> getExpertIds() {
		return expertIds;
	}

	public void setExpertIds(List<String> expertIds) {
		this.expertIds = expertIds;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}