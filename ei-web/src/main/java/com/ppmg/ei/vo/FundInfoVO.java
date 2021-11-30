package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class FundInfoVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String fundId;

	/** 基金代码 */
	private String fundCode;

	/** 基金名称 */
	private String fundName;

	/** 基金注册名称 */
	private String regName;

	/** 管理公司编号 */
	private String mcId;

	/** 管理公司 */
	private String mcName;

	/** 普通合伙人 */
	private String gp;

	/** 联系人 */
	private String contact;

	/** 联系人电话 */
	private String phoneNo;

	/** 联系人Email */
	private String contactEmail;

	/** 拟募资规模 */
	private Double planAmount;

	/** 拟募资币种 */
	private String planCurr;

	/** 基金规模 */
	private Double raiseAmount;

	/** 币种 */
	private String raiseCurrency;

	/** 组织形式 */
	private String fundStruct;

	/** 组织形式 */
	private String fundStructName;

	/** 一级类别 */
	private String platProp;

	/** 一级类别 */
	private String platPropName;

	/** 二级类别 */
	private String subPlatProp;

	/** 二级类别 */
	private String subPlatPropName;

	/** 成立日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date setupDt;

    /** 成立日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

	/** 拟最终关闭时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date closeDt;

	/** 注册地 */
	private String regAdd;

	/** 组织机构代码 */
	private String orgnCode;

	/** 法定代表人 */
	private String legalPerson;

	/** 托管银行 */
	private String custodianBank;

	/** 注册资本金 */
	private Double regCapital;

	/** 注册资本金币种 */
	private String regCurrency;

	/** 资本金账户 */
	private String capitalAccount;

	/** 资本回收账户 */
	private String returnAccount;

	/** 基金状态(1：已登记，2：申请中，3：已立项，4：投资期，5：回收期，6：延展期，7：已关闭，8：已删除，9：已废弃) */
	private String fundSts;

	/** 附件 */
	private String descFile;

	/** 基金来源（1：母基金，2：子基金，3：其他） */
	private String fundSrc;

	/** 基金类型 */
	private String fundGenre;

	/** 托管银行账户 */
	private String custodianBankaccount;

	/** 资本金银行 */
	private String capitalBank;

	/** 资本回收银行 */
	private String returnBank;

	/** 合伙人名单 */
	private String partners;

	/** 基金所属公司ID */
	private Long groupId;

	/** 普通合伙人ID */
	private String gpId;

	/** 折算为人民币 */
	private Double rmbRaiseAmount;

	/** 当前认缴规模 */
	private Double currentAmount;

	/** 当前认缴规模币种 */
	private String currentCurrency;

	/** 当前认缴规模折算人民币 */
	private Double rmbCurrentAmount;

	/** 基金信息维护人ID */
	private String maintenanceId;

	/** 基金信息维护人 */
	private String maintenance;

	/** 统一社会信用代码 */
	private String socialCode;

	/** 关键人士锁定 */
	private String keyPersonLock;

	/** 管理公司在GP中持股比例 */
	private Double ratioInGp;

	/** 目标规模折算人民币 */
	private Double rmbPlanAmount;

	/** 境内境外 */
	private String fundScope;

	/** 元禾控股作为LP出资比例 */
	private Double yhRatioAsLp;

	/** 元禾控股在GP中持股比例 */
	private Double yhRatioInGp;

	/** 元禾控股在管理公司中持股比例 */
	private Double yhRatioInMc;

	/** 邮箱 */
	private String email;

	/** 基金注册企业ID */
	private String enteId;

	/** 邮箱密码 */
	private String emailPassword;

	/** 财务维护人ID */
	private String financeId;

	/** 财务维护人 */
	private String financeName;

	/** 实缴比例=当前实缴规模/当前认缴规模 */
	private String raiseCurrent;

	/** 目标规模(亿元) */
	private String planAmountDisplay;

	/** 认缴规模(亿元) */
	private String currentAmountDisplay;

	/** 实缴规模(亿元) */
	private String raiseAmountDisplay;

	/** 项目投资个数 */
	private String projNum;

	/** 项目投资金额（万元） */
	private String investAmount;

	/** 是否需要合规性审查 */
	private String isFit;

	/** 基金托管银行开户行 */
	private String custodianOpenBank;

	/** 基金托管银行帐号 */
	private String custodianBankNum;

	/** 基金备案情况 */
	private String isRecord;

	/** 备案号 */
	private String recordCode;

	/** 备案日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date recordDate;

	/** 备案证明附件 */
	private String recordAtta;

	/** 基金管理费相关信息*/
	private FundInveDescModel fundInveDescModel;

	/** 基金负责人信息*/
	private List<FundMemberModel> fundMemberList;

	/** 基金出资人信息*/
	private List<FundShareInfoModel> shareInfoList;

	List<FundShareItemModel> shareItem;
    /**是否有托管协议**/
	private String isCustodian;

   /**托管协议协议附件**/
	private String custodianAtta;

	/**是否有委托管理协议**/
	private String isAuth;

	/**委托管理协议附件**/
	private String authAtta;

	private String isAuthName;

	private String isCustodianName;

	private String fundGenreName;

	private String  currentCurrencyName;

	private String amount;

	private String countGs;

	private String bankId;

	private String isRegister;

	//第一次开会时间
	private String oldMeetingDate;
//本年度出资人会议召开时间
	private String meetingDate;

	//已取得收益
	private Double profitAmount;

   //已签署协议投资额
	private Double sumSignAmount;
	//每期合计
	private Double sumDuesAmount;

	private Double szfInveAmount;

	private Double szfRaiseAmount;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDt;

	private String status;

	private String custodianBankId;

	private Double inveCurrentAmount;

	private String actualAmount;

	private String bjjPer;

	private Double  inveRaiseAmount;

	private String  isExcept;

	private ProjInfoModel projInfo;

	private ProjAppInfoModel projAppInfo;

	private String quitTypeName;

	private ProjQuitApplModel projQuitApplModel;

	private XjlTPaymentRequestModel xjl;

	private String projStatusName;

	private String spvTypeName;

	private String isRecordName;

	private String isCorrName;

	private String isJsName;

	private String isDecisionPassName;

	private String fundStsName;

	//是否引导基金
	private String isLead;

	//是否建立退出机制
	private String isExit;

	//省级财政出资
	private Double  financeAmount;


	//批准设立部门
	private String appDep;

	//省级资金来源
	private String govFundSrc;

	//设立依据
	private String basis;
	//投资限制
	private String inveLimit;

	//管理部门
	private String manDep;

	private String isLeadName;

	private String inveTypeName;

	private String  manDepName;

	private String appDepName;

	private String govFundSrcName;

	private String fundsTypeName;

	private EntBaseInfoModel entBaseInfoModel;

    private String shortName;

    private String projectId;

    private String inveType;

	private String fundSrcName;

	private String projSrcName;

	private String isDirect;

	private String fundsType;

    private String parentId;

	private String oldSubPlatProp;

	private String fundCategoryName;

	private String fundCategory;

	private String parentName;

	private Double paySumAmount;

	//专项
	private String perRaiseCurrent;

	private String perCent;

	private String fundCodeSign;

	private String creditCode;

	private String registerStatus;


	private String oldSubPlatPropName;

	private String projId;

	private String projName;

	private String inveName;

	private String repId;

	private String repName;


	private String adminNames;

	private String  adminIds;

	private List<FundViewModel> listView;

	private String  otherFile;

	private String  organStruct;

	private String  organStructName;


}