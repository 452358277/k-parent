package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class EnteInfoDTO extends BaseDTO {
	
	/** 企业编号 */
	private String enterpriseId;

	/** 企业全称 */
	private String chName;
	
	/** 成立日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date setupDt;
	
	/** 董事席数 */
	private Long directorCnt;

	/** 企业英文名 */
	private String enName;

	/** 企业注册商标 */
	private String registerName;

	/** LOGO */
	private String logo;

	/** 所属阶段 */
	private String phase;

	/** 所属行业 */
	private String industry;


	/** 企业类型(1：个人独资，2：合伙企业，3：公司企业) */
	private String enterpriseForm;

	/** 子类型 */
	private String subForm;

	/** 关系类型（1:直投，2:间接投资，3：其他） */
	private String relType;

	/** 联系人 */
	private String contact;

	/** 联系方式 */
	private String contactTel;

	/** 关联公司 */
	private String relCompany;

	/** 实际控制人 */
	private String controller;

	/** 实际控制人电话 */
	private String controllerTel;

	/** 招商载体 */
	private String bizCarrier;

	/** 运营主体 */
	private String operation;

	/** 公司简介 */
	private String companyProfile;

	/** 简介附件编码 */
	private String profileFile;

	/** 税收征管方式 */
	private String taxCollection;

	/** 税收说明 */
	private String taxDesc;

	/** 是否高新技术企业（0：否，1：是） */
	private String isHnTech;

	/** 批准日期(高新技术企业证书获得时间) */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date apprDt;

	/** 证书 */
	private String certificate;

	/** 其他企业类型 ，标签*/
	private String otheEnteType;

	/** 监事席数 */
	private Long supervisorCnt;

	/** 状态（0：正常，1：删除） */
	private String status;

	/** 组织机构代码 */
	private String orgnCode;

	/** 被投企业主营业务 */
	private String mainBusiness;

	/** 所属公司ID */
	private Long groupId;

	/** null */
	private String projGuid;

	/** 获得专利数量 */
	private String patentCount;

	/** 是否上市公司（0：否，1：是） */
	private String isPublicComp;

	/** 股票代码 */
	private String stockCode;

	/** 股票名称 */
	private String stockName;

	/** 投资行业（七大行业） */
	private String industry2;

	/** 统一社会信用代码 */
	private String socialCode;

	private String enteId;
    //是否拥有董事席位
	private String isDirectorCnt;
   //是否拥有监事席位
	private String isSupervisorCnt;
    //是否省内企业
	private String isProvince;
  //注册资本
	private Double regAmount;
    //注册地
	private String regAdd;

	//是否注册
	private String isReg;

	//0是境外 1是境外
	private String isAbroad;


	//是否申报ipo
	private String isApplyIpo;

	//是否挂牌新三板
	private String isNewBoard;

	//是否亮点企业
	private String isHlights;
	//亮点介绍
	private String hlightsRemark;
	//投后估值/市值
	private Double newAmount;

	//主联系人
	private String mainContact;

	/** 邮箱 */
	private String email;


	private String industryName;


	private List<String> labels;

	private String regAmountCurrency;

	private String inveId;

	private String inveName;

	private String projId;

	private String updateNull;

	private String projStatus;


}
