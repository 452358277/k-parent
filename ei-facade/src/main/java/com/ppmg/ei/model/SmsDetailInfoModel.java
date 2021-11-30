package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;
import lombok.Data;

@Data
public class SmsDetailInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String detailId;

	/** 短信发送主表主键 */
	private String smsId;

	/** 接收者手机号 */
	private String receivePhone;

	/** 接收者ID */
	private String receiveId;

	/** 接收者姓名 */
	private String receiveName;

	/** 删除标记 */
	private String isDelete;

	/** 排序 */
	private Long sortOrder;

	/** 版本号 */
	private Long rowVersion;
}