package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 外部用户修改 [DTO]
 * @author yuyongjun
 * @date 2019-06-24 16:17
 */
@Data
public class AppUserUpdateDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 用户编号 */
	@ApiModelProperty(name = "id", value = "用户编号", example = "1", dataType = "String", required = true)
	private Long id;

	/** 姓名 */
	@ApiModelProperty(name = "name", value = "姓名", example = "张三", dataType = "String", required = true)
	private String name;

	/** 电子邮箱 */
	@ApiModelProperty(name = "email", value = "电子邮箱", example = "zhangsan@163.com", dataType = "String")
	private String email;

	/** 性别（码值） */
	@ApiModelProperty(name = "sexy", value = "性别（码值）10001:男 10002：女", example = "10001", dataType = "String")
	private Long sexy;

	/** 移动电话 */
	@ApiModelProperty(name = "mobilephone", value = "移动电话", example = "17712789876", dataType = "String")
	private String mobilephone;

	private String loginname;
}