package com.ppmg.ei.model;

import lombok.Data;

import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Data 
public class TzTCooConditionItemModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 合作征集对象ID */
	private String itemId;

	/** 征集信息ID */
	private String infoId;

	/** 基金管理人名称 */
	private String fundAdminName;

	/** 基金管理人ID */
	private String fundAdminId;

	/** 子基金名称 */
	private String fundName;

	/** 子基金ID */
	private String fundId;

	/** 状态 */
	private String status;

	/** 是否符合 */
	private String isFit;

	/** 文件类型 */
	private String fileType;

	/** 附件说明 */
	private String attaDetail;

	/** 附件 */
	private String attaFile;

}