package com.ppmg.ei.model;

import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

/** 
 * 描述 [Model] 
 * @author null
 * @date 2019-08-13 10:53 
 */ 
@Data 
public class AdminCoreModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 管理基金ID */
	private String coreId;

	/** 基金管理人ID */
	private String adminId;

	/** 姓名 */
	private String name;

	/** 职务 */
	private String position;

	/** 国籍 */
	private String country;

	/** 性别 */
	private String sex;

	/** 出生年月 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date birthday;

	/** 毕业院校 */
	private String university;

	/** 专业 */
	private String major;

	/** 学历 */
	private String education;

	/** 联系电话 */
	private String phoneNo;

	/** 邮箱 */
	private String email;

	/** 联系地址 */
	private String addr;

	/** 投资业绩 */
	private String performance;

}