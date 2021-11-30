package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;

import lombok.Data;

import com.founder.ssm.core.model.BaseModel;

/**
 * 描述 [Model]
 * @author null
 * @date 2019-09-04 15:50
 */
@Data
public class InveResumeModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 投资人ID */
	private String investorId;

	/** 简介 */
	private String summary;

	/** 履历 */
	private String resume;

	/** 备注 */
	private String remark;



}