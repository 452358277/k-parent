package com.ppmg.ei.model;

import lombok.Data;

import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Data 
public class AdminWorkModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 工作经历ID */
	private String workId;

	/** 核心成员ID */
	private String coreId;

	/** 开始年月 */
	private String startDt;

	/** 单位 */
	private String company;

	/** 职务 */
	private String position;

	/** 岗位职责 */
	private String positionDetail;

	/** 结束年月 */
	private String endDt;

}