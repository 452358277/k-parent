package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundInfoCgDTO  extends BaseDTO {

    /** 基金ID */
    private String fundId;

    /** 基金注册状态 */
    private String registerStatus;

    /** 基金注册名称 */
    private String regName;

    /** 基金注册简称 */
    private String fundName;

    /** 基金性质 */
    private String oldSubPlatProp;
    /** 注册地 */
    private String regAdd;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date setupDt;


    /**  统一社会信用代码/注册号 */
    private String regCode;


    private String gp;

    /**  管理人名称*/
    private String mcName;
    /**  管理人id*/
    private String mcId;

    /**  基金认缴总规模*/
    private Double currentAmount;

    /**  基金认缴总规模-币种*/
    private String currentCurrency;

    /**  基金认缴总规模-折算人民币*/
    private Double rmbCurrentAmount;


    /**  基金实缴规模*/
    private Double raiseAmount;

    /**  基金实缴规模*-币种*/
    private String raiseCurrency;

    /**  基金实缴规模-折算人民币*/
    private Double rmbRaiseAmount;


    /** 投资期起算日 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date inveStartDate;

    /** 投资期截止日 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date inveEndDate;

    /** 最终关闭日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date closeDt;

    /** 基金状态 */
    private String fundSts;

    /** 组织形式 */
    private String fundStruct;

    /** 基金类型 */
    private String oldFundGenre;

    /** 基金联系人 */
    private String contact;

    /** 基金联系人电话 */
    private String phoneNo;

    /** 基金email */
    private String contactEmail;

    /** 批准设立部门 */
    private String appDep;

    /** 管理部门 */
    private String manDep;

    /** 基金团队成员  保存在 BD_T_FUND_MEMBER中*/
    private String fundNums;

    /** 设立依据*/
    private String basis;

    /** 备案情况-复选框*/
    private String rcdAdd;
    /** 备案情况 保存在 BD_T_FUND_RECORD_INFO表中*/
    private String recordInfo;

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

    /** 备注 */
    private String remark;

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
    /** 投资策略 */
    private String investmentStrategy;


    /** 投资限制 */
    private String inveLimit;
    /** 附件 */
    private String descFile;
    /** 队员 */
    private String pkId1;
    /** 备案情况 */
    private String pkId2;

    private String enteId;

    private String gpId;

    private String socialCode;


    private String isRollOver ;

    private String isPaybackOver;

    /** 省级自己来源  90019 */
    private String oldFundSrc;

    /** 投资方式  90018 */
    private String inveType;

    /** 基金类型 9002 */
    private String fundCodeType;

    private String repId;

    private String repName;

    private String fundCategory;

    private String  govFundSrc  ;

    private String isRecord;

    private String recordCode;
    private String fundLead;
    private String fundLeadId;

    private String isLead;

    private String isExit;
    private String shortName;

    private Double planAmount;

    private Double financeAmount;

    private String status;

    //组织形式
    private String organStruct;

    private String  isDurationOver;

    private String  durationOver;


    private String addPayBackOver;

    private String otherFile;
}
