package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import com.ppmg.ei.model.AdminModel;
import com.ppmg.ei.model.FundInveDescModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class FundInfoProjDTO extends BaseDTO {
    /** 主键 */
    private String fundId;

    /** 基金编号 */
    private String fundCode;

    /** 基金名称 */
    private String fundName;

    /** 基金注册名称 */
    private String regName;

    /** 联系人 */
    private String contact;

    /** 联系人电话 */
    private String phoneNo;

    /** 拟募资规模 */
    private Double planAmount;

    /** 拟募资币种 */
    private String planCurr;

    /** 基金实缴规模 */
    private Double raiseAmount;

    /** 币种 */
    private String raiseCurrency;

    /** 基金形式（合伙制、公司制、契约制） */
    private String fundStruct;

    /** 基金一类类别（区域基金、国家基金、产业基金） */
    private String platProp;

    /** 基金二类类别 */
    private String subPlatProp;

    /** 基金注册日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date setupDt;

    /** 拟最终关闭时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date closeDt;

    /** 基金注册地址 */
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

    /** 基金状态 */
    private String fundSts;

    /** 合伙协议附件 */
    private String descFile;

    /** 基金来源（1：母基金，2：子基金，3：其他） */
    private String fundSrc;

    /** 基金类型 */
    private String fundGenre;

    /** 托管银行账户 */
    private String custodianBankaccount;

    /** 基金所属公司ID */
    private Long groupId;

    /** 基金认缴规模 */
    private Double currentAmount;

    /** 当前认缴规模币种 */
    private String currentCurrency;

    /** 统一社会信用代码 */
    private String socialCode;

    /** 关键人士锁定 */
    private String keyPersonLock;

    /** 本基金占比 */
    private Double yhRatioAsLp;

    /** 基金注册企业ID */
    private String enteId;

    /** 基金管理投资相关信息*/
    private FundInveDescModel fundInveDescModel;

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

    /** 普通合伙人ID */
    private String gpId;

    /** 普通合伙人 */
    private String gp;

    /**是否有托管协议**/
    private String isCustodian;

    /**托管协议协议附件**/
    private String custodianAtta;

    /**是否有委托管理协议**/
    private String isAuth;

    /**委托管理协议附件**/
    private String authAtta;

    private String oldCustodianBank;
    /**所得收益**/
    private Double profitAmount;

    private String status;
    //本基金认缴规模
    private String inveCurrentAmount;

    /** 主键 */
    private String projId;

    /** 项目编号 */
    private String projNo;

    /** 项目名称 */
    private String projName;

    /** 项目类型（1：投企业，2：投基金，3:子基金项目,4:spv项目） */
    private String projType;

    /** 项目状态（2：储备项目，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
    private String projStatus;

    /** 被投对象 */
    private String projObject;

    /** 被投对象名称 */
    private String projObjectName;

    /** 所投企业当前所属阶段 */
    private String entePhase;

    /** 项目来源 */
    private String projSrc;

    /** 来源说明 */
    private String srcDesc;

    /** 出资主体编号 */
    private String inveId;

    /** 出资主体名称 */
    private String inveName;

    /** 出资主体类型（1：基金，2：直投部门） */
    private String inveType;

    /** 分管合伙人 */
    private String chargePartner;

    /** 保密等级 */
    private String secrecyLevel;

    /** 附件 */
    private String inveFile;


    /** 投资决策时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date decisionDt;


    /** 项目所属公司NAME */
    private String groupName;

    /** 流程实例号 */
    private String processInstId;

    /** null */
    private String projGuid;

    /** 是否为投平台项目（0：否，1：是） */
    private String isPlat;

    /** 立项暂缓（1：表示暂缓:空表示无暂缓） */
    private String pauseApply;

    /** 决策暂缓（1：表示暂缓:空表示无暂缓） */
    private String pauseDecision;

    /** 项目概况 */
    private String projOverview;

    /** 投资经理对项目评价 */
    private String inveEvaluation;

    /** 备注 */
    private String remark;

    /** 附件说明 */
    private String fileDesc;

    /** 决策事项/董监事安排 */
    private String majorMatter;

    /** 项目概况附件 */
    private String projOverviewAtt;

    /** 投资经理对项目评价附件 */
    private String inveEvaluationAtt;

    /** 备注附件 */
    private String remarkAtt;

    /** 决策事项/董监事安排附件 */
    private String majorMatterAtt;

    /** 质量评估评级结果 */
    private Long scoreResult;

    /** 所在区域 */
    private String area;

    /** 邮箱 */
    private String email;

    /** 邮箱密码 */
    private String emailPassword;

    /** 出资主体社会信用代码 */
    private String inveCreditCode;

    /** 母基金名称 */
    private String pefofName;

    /** 母基金社会信用代码 */
    private String pefofCreditCode;

    /** 原项目所属公司ID */
    private Long groupIdOld;

    /** 批量立项标志 */
    private String investTogether;

    /** 咨询委员会成员 */
    private String advisoryCommId;

    /** 咨询委员会成员姓名 */
    private String advisoryCommName;

    /** 商业计划书附件 */
    private String bizPlanAtt;

    /** 立项报告附件 */
    private String projReportAtt;

    /** 其他附件 */
    private String otherAtt;

    private String reserveType;

    /** 当前子基金规模 */
    private String subFundAmontDisplay;

    /** 认缴金额 */
    private String currentAmountDisplay;

    /** 累计出资 */
    private String allRequestMoney;

    /** 退出出资 */
    private String quitMoney;

    /** 最新占比 */
    private String currentRatio;

    /** 协议金额 */
    private String signAmountDisplay;


    /**是否是否关联交易**/
    private String isCorr;

    /**是否上合伙人会议**/
    private String	isMeeting;

    /**项目性质（码值517）**/
    private String[] projPropertyIds;

    private String uniqueCoding ;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date instorageDt;

    /**占股比（%）**/
    private Double  perShare;

    /**拟投资金额**/
    private Double intendedAmount ;

    /**管理员**/
    private String projMc;

    /**注册状态**/
    private String isRegist;

    /** 统一社会信用代码 */
    private String creditCode;

    /** 组织机构代码 */
    private String orgCode;

    /** 法定代表人 */
    private String operName;


    /** 延长期管理费 */
    private Double mgtFeeRate;

    /** 管理费说明 */
    private String mgtFeeDesc;

    /** 投资期(年) */
    private String invePeriod;

    /** 回收期(年) */
    private String paybackPeriod;

    /** 投资期延长 */
    private String rollOver;

    /** 存续期说明 */
    private String durationDesc;

    /** 门槛收益率 */
    private Double hurdleRate;

    /** 超额分配比例-有限合伙人比例 */
    private Double performanceFee;

    /** 收益分配说明 */
    private String incomeDistDesc;

    /** 投资方向及目标 */
    private String inveStrategy;

    /** 投资标准 */
    private String inveStandard;

    /** 业绩报酬提取方式 */
    private String payMethod;

    /** 投资期起算日 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date inveStartDate;

    /** 投资期截止日 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date inveEndDate;

    /** 退出期延长 */
    private String paybackOver;

    /** 存续期 */
    private String durationPeriod;

    /** 管理费方式(码值1010：1按认缴，2按实缴) */
    private String mgtOrPaid;

    /** 超额分配比例-管理人比例 */
    private Double mcFee;
    /** 投资期管理费 */
    private String inveFeeRate;
    /** 退出期管理费 */
    private String paybackFeeRate;

    private String isRollOver;

    private String isPaybackOver;

    //是否引导基金
    private String isLead;

    //是否建立退出机制
    private String isExit;

    //省级财政出资
    private String  financeAmount;


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

    private String mcName;

    private String shortName;

    private String isExcept;

    private Double inveRaiseAmount;

    private String investmentStrategy;

    private String fundsType;

    private String mcId;

    private String sign;

    private String parentId;

    private String fundCategory;

    private String oldSubPlatProp;

    private String isDirect;

    //出资主体是参股子基金把 oldSubPlatProp 的值 放在inveTypeStr
    private String inveTypeStr;

    //出资主体编号
    private String inveFundCode;

    private String inveParentId;

    //下拉款的值
    private String  oldFundCodeSign;

    private String  fundCodeSign;


    private String addressDetails;

    private String termStartDt;

    private Double addPayBackOver;


    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

    private String registerStatus;

    private String repId;

    private String repName;

    private String  isDurationOver;

    private String  durationOver;


    private List<AdminModel> listAdmin;

    private String  otherFile;

    private String  contactEmail;

}
