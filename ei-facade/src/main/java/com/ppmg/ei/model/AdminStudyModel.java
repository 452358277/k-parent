package com.ppmg.ei.model;

import lombok.Data;

import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Data 
public class AdminStudyModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 学习经历ID */
	private String studyId;

	/** 核心成员ID */
	private String coreId;

	/** 开始年月 */
	private String startDt;

	/** 受教育院校 */
	private String school;

	/** 专业 */
	private String major;

	/** 学历 */
	private String education;

	/** 结束年月 */
	private String endDt;

}