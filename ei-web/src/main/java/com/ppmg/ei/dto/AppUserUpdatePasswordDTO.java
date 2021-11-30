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
public class AppUserUpdatePasswordDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 用户编号 */
	@ApiModelProperty(name = "id", value = "用户编号", example = "1", dataType = "String", required = true)
	private Long id;

	/** 旧密码 */
	@ApiModelProperty(name = "oldPassword", value = "旧密码", example = "888888", dataType = "String", required = true)
	private String oldPassword;

	/** 新密码 */
	@ApiModelProperty(name = "newPassword", value = "新密码", example = "123456", dataType = "String", required = true)
	private String newPassword;

	@ApiModelProperty(name = "ifmodifypassword", value = "是否修改", example = "1", dataType = "String", required = true)
	private String ifmodifypassword;

}