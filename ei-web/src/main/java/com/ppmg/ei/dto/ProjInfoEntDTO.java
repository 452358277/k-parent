package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ProjInfoEntDTO extends BaseDTO {

	private String projId;

	/** 项目名称 */
	private String projName;

	/** 被投对象 */
	private String projObject;

	/** 被投对象名称 */
	private String projObjectName;
	/** 项目来源 229 */
	private String projSrc;

	/** 项目来源 说明 */
	private String srcDesc;

	private String inveId;

	private String inveName;


	/** 投资轮次 */
	private String inveRounds;

	/** 投前估值 */
	private Double preMoney;

	/** 占股比（%） */
	private Double perShare;


	/** 项目概况 */
	private String projOverview;


	/** 附件说明 */
	private String fileDesc;

	/** 附件附件 */
	private String inveFile;


	private Double intendedAmount;

	private String intendedCurrency;


	/** 出资日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date inveDt;
	/** 投资类型 */
	private String inveType ;

	private String projStatus;

	private String projType;

	private String status;

	private String enterpriseIdStr;

	private String oldEnterpriseId;

	/** 投后估值 */
	private Double postMoney;

	/** POST_CURRENCY */
	private String postCurrency;

	/** 折算为人民币 */
	private Double rmbIntendedAmount;

	private String finaRounds;

	private String finaTimes;

	private String remarkAtt;



}
