package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class SmsInfoVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String smsId;

	/** 发送人ID */
	private String senderId;

	/** 发送人姓名 */
	private String senderName;

	/** 短信类型：0实时短信，1定时短信 */
	private String smsType;

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

	/** 删除标记 */
	private String isDelete;

	/** 排序 */
	private Long sortOrder;

	/** 版本号 */
	private Long rowVersion;

	/** 短信详情 */
	private List<SmsDetailInfoVO> details;

	/** 填报截止日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date finishDate;

	/** 是否需要结束日期 */
	private String isFinish;
	private String isFinishName;

	/** 接收人 */
	private String receivers;

	/** 短信内容 */
	private String smsContentName;



	private String smsTimeStr;


	private String year;


	private String quarter;

	private String quarterName;
}