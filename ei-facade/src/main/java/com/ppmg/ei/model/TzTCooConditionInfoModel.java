package com.ppmg.ei.model;

import lombok.Data;

import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Data 
public class TzTCooConditionInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 合作条件ID */
	private String infoId;

	/** 公开征集ID */
	private String conditionId;

	/** 已征集规模 */
	private Double cooSize;

	/** 已征集数量（家） */
	private Long totalCooNum;

	/** 符合征集条件（家） */
	private Long fitCooNum;

	/** 会议纪要（决策文件） */
	private String meetingFile;

	/** 相关附件 */
	private String attaFile;

	/** 流程ID */
	private String processInstId;

	/** 状态 */
	private String status;

	/** 母基金ID */
	private String fundId;

	/** 附件说明 */
	private String attaDetail;

}