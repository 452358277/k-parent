package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import com.founder.ssm.core.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 基金管理人 [Model]
 * @author yuyongjun
 * @date 2019-06-25 09:21
 */
@Data
public class FundMcInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 管理人编号 */
	private String mcId;

	/** 管理人名称 */
	private String mcName;

	/** 注册企业ID */
	private String enteId;

	/** 基金管理人登记情况 */
	private String isRegister;

	/** 基金管理人登记号 */
	private String registerNo;

	/** 办公地址 */
	private String officeAddr;

	/** 用户ID */
	private String userId;

	/** 父级ID */
	private String parentId;


	/**基金管理人登记时间**/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date registerDt;

	private EntBaseInfoModel entBaseInfo;

}