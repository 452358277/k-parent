package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class FundInfoRestVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String fundId;

    /** 基金编号 */
    private String fundCode;

    /** 基金名称 */
    private String fundName;

    /** 基金注册名称 */
    private String regName;

    /** 管理公司ID */
    private String mcId;

    /** 管理公司 */
    private String mcName;

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

    /** 基金实缴规模 */
    private Double raiseAmount;

    /** 币种 */
    private String raiseCurrency;

    /** 基金形式（合伙制、公司制、契约制） */
    private String fundStruct;

    private String fundStructName;

    private String platPropName;
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

    /** 托管银行ID */
    private String custodianBankId;

    /** 注册资本金 */
    private Double regCapital;

    /** 注册资本金币种 */
    private String regCurrency;

    /** 资本金账户 */
    private String capitalAccount;

    /** 资本回收账户 */
    private String returnAccount;

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

    /** 资本金银行 */
    private String capitalBank;

    /** 资本回收银行 */
    private String returnBank;

    /** 合伙人名单 */
    private String partners;

    /** 基金所属公司ID */
    private Long groupId;

    /** 普通合伙人 */
    private String gp;

    /** 普通合伙人ID */
    private String gpId;

    /** 折算为人民币 */
    private Double rmbRaiseAmount;

    /** 基金认缴规模 */
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

    /** null */
    private Double ratioInGp;

    /** null */
    private Double rmbPlanAmount;

    /** null */
    private String fundScope;

    /** 本基金占比 */
    private Double yhRatioAsLp;

    /** null */
    private Double yhRatioInGp;

    /** null */
    private Double yhRatioInMc;

    /** 基金邮箱 */
    private String email;

    /** 基金注册企业ID */
    private String enteId;

    /** null */
    private String emailPassword;

    /** null */
    private String financeId;

    /** null */
    private String financeName;

    /** 目标规模(显示) */
    private String planAmountDisplay;

    /** 认缴规模 */
    private String currentAmountDisplay;

    /** 实缴规模 */
    private String raiseAmountDisplay;

    /** 实缴比例=当前实缴规模/当前认缴规模 */
    private String raiseCurrent;

    /** 已投项目数 */
    private String projNum;

    /** 已投资金额 */
    private String investAmount;

    /** 累计决策项目个数 */
    private String totalInvestCount;

    /** 累计投资金额	 */
    private String totalInvestAmount;

    /** 今年投资项目个数 */
    private String thisYearInvestCount;

    /** 今年投资金额 */
    private String thisYearInvestAmount;

    /** 在投项目数 */
    private String investingCount;

    /** 基金管理投资相关信息*/
    private FundInveDescModel fundInveDescModel;

    private List<FundMemberModel> fundMemberList;

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

    private String executivePartnerId;

    private String executivePartnerName;

    /** 备案证明附件 */
    private String recordAtta;

    /** 基金投资人信息 */
    private List<FundGpInfoModel> fundGpList;

    /** 基金管理人信息 */
    private List<FundMcInfoModel> fundMcList;

    /** 基金出资人信息*/
    private List<FundShareInfoModel> shareInfoList;

    /** 基金管理人信息 */
    private FundMcInfoModel fundMcInfoModel;

    /**是否有托管协议**/
    private String isCustodian;

    /**托管协议协议附件**/
    private String custodianAtta;

    /**是否有委托管理协议**/
    private String isAuth;

    /**委托管理协议附件**/
    private String authAtta;

    /**出资人会议是否召开
     **/
    private String iszkhy;
    /**召开会议时间**/
    private String meetingDate;

    private String oldMeetingDate;

    /**出资人名称**/
    private String investorName;

    private int gs;

    private String registerNo;

    private String registerCode;

    private String addressDetails;

    private String fundGenreName;

    private String  creditCode;

    /** 年度 */
    private String year;

    /** 季度 */
    private String quarter;

    /** 资产总额 */
    private Double totalAssets;

    /** 累计管理费 */
    private Double totalManage;

    /** 累计托管费 */
    private Double totalDeposit;

    /** 累计顾问咨询费 */
    private Double totalConsult;

    /** 累计审计费 */
    private Double totalAudit;

    /** 累计律师费 */
    private Double totalLawyer;

    /** 其他费用的列支情况 */
    private Double totalOther;

    private Double czInveAmount;

    private Double czRaiseAmount;

    private String  capitalSourceName;

    private String  inveTypeName;

    private FundShareInfoModel fundShareInfoModel;

    /** 估值基准日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date assessmentDate;

    /** 期末总资产 * */
    private Double endTotalAssets;

    /** 期末净资产 * */
    private Double endNetAssets;

    /** 产品成立以来累计已付管理费  */
    private Double totalManagePay;

    /** 产品成立以来累计已付投资顾问费 * */
    private Double totalConsultPay;

    /** 产品成立以来累计已付托管费/保管费  */
    private Double totalDepositPay;

    /** 产品成立以来累计运营服务费/外包服务费 */
    private Double totalOutsource;

    /** 产品成立以来累计已付运营服务费/外包服务费 * */
    private Double totalOutsourcePay;

    /** 产品成立以来累计业绩报酬  */
    private Double totalAchievement;

    /** 产品成立以来累计已付业绩报酬  */
    private Double totalAchievementPay;

    /** 产品成立以来累计分红 * */
    private Double totalBonus;

    /** 产品成立以来累计本金、收益分配 */
    private Double totalProfit;

    /** 剩余可用资金-预留管理费、审计费等 */
    private Double totalResidue;

    /** 剩余可用资金-可用于投资资金 */
    private Double totalAvailableFunds;

    private String quarterName;
}
