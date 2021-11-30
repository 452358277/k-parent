package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class SmsInfoDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String smsId;

	/** 发送人ID */
	private String senderId;

	/** 发送人姓名 */
	private String senderName;

	/** 短信类型：0实时短信，1定时短信 */
	private String smsType;

	/** 填报截止日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date finishDate;

	/** 是否需要结束日期 */
	private String isFinish;

	/** 发送时间，如果是定时发送，则为定时发送的时间，如果是实时发送，则为当前时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date smsTime;

	/** 发送状态，码值(60024：0:草稿，1已发送) */
	private String smsStatus;

	private String smsStatusName;

	/** 短信内容 */
	private String smsContent;

	/** 发送结果：0:成功，1失败 */
	private String smsStatus2;

	/**  1.来源直投项目平台短信，2.社保基金短信，3.省政府guanli平台短信 4.省政府平台托管行 */
	private String smsModular;

	private String codeValue;

	private String year;


	private String quarter;

	/** 短信详情 */
	private List<SmsDetailInfoDTO> details;

}