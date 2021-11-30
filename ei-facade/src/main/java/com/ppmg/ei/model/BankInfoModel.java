package com.ppmg.ei.model;

import lombok.Data;

import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author yuyongjun
 * @date 2019-06-25 09:21 
 */ 
@Data 
public class BankInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 银行编号 */
	private String bankId;

	/** 银行名称 */
	private String bankName;

	/** 用户ID */
	private String userId;

	/** 父级ID */
	private String parentId;

	/** 注册企业ID */
	private String enteId;

	/** 状态 */
	private String status;
	/** 联系人 */
	private String contacts;

	/** 联系人手机好*/
	private String phone;


}