package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;
import lombok.Data;

@Data
public class CommTLabelItemModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String itemId;

	/** 标签ID */
	private String labelId;

	/** 填报属性（码值：1027） */
	private String property;

	/** 状态（0正常，1删除） */
	private String status;

	private String labelName;



}