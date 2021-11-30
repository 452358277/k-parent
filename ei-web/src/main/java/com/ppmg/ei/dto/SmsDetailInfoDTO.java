package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

@Data
public class SmsDetailInfoDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 接收者手机号 */
	private String receivePhone;

	/** 接收者ID */
	private String receiveId;

	/** 接收者姓名 */
	private String receiveName;

}