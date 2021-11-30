package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LedgerMagFundDTO extends BaseDTO {



    /** 主键 */
    private String ledgerId;

    /** 投资支付类型（1投资人，2基金，3项目，4平台） */
    private String investPaymentType;

    /** 单据编码 */
    private String fNumber;

    /** 公司名称 */
    private String companyName;

    /** 公司编码 */
    private String companyNumber;

    /** 业务日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date bizDate;

    /** 客户名称 */
    private String customerName;

    /** 客户编码 */
    private String customerNum;

    /** 资金类型（1出资，2入资） */
    private String financeType;

    /** 收款类型（10其他） */
    private String recBillTypeName;

    /** 收款类型编码 */
    private String recBillTypeNum;

    /** 币种（1人民币，2美元，3欧元） */
    private String currencyName;

    /** 币种编码 */
    private String currencyNum;

    /** 金额 */
    private Double amt;

    /** 收款分录行号 */
    private String fseq;

    /** 对方科目名称 */
    private String oppAccountName;

    /** 对方科目编码 */
    private String oppAccountNum;

    /** 核算项目行号 */
    private String assseq;

    /** 收款科目核算辅助中的客户编码 */
    private String assCustomerNum;

    /** 收款科目核算辅助中的客户名称 */
    private String assCustomerName;

    /** 状态（0：正常，1：删除，2：已到帐） */
    private String status;

    /** 附件 */
    private String descFile;

    /** 描述 */
    private String descriptiondata;

    /** 人民币金额 */
    private Double actuallocamt;

    /** 描述 */
    private String remark;

    /** 所属平台 */
    private String groupId;

    /** 所属平台 */
    private String fundName;

    private String fundId;

    private String customerType;

    private String enteId;

    /** 日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date recordDate;
    /** 账户余额 */
    private Double accountBalance;

    /** 起息日 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

    /** 到期日 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date endDate;

    /** 存款期限 */
    private String depositTerm;

    /** 利率*/
    private Double	moneyRate;

    /** */
    private String projId;
    /**资金类型*/
    private String ledgerType;
    /** 发生金额*/
    private Double occurAmount;
    /** 发生币种*/
    private String occurCurr;
    /** 退出方式*/
    private String exitType;
    /** 退出轮次*/
    private String exitRound;
    /** 支付方式*/
    private String payWay;
    /** 发生日期*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date occurDt;
    /** 出资名称*/
    private String inveName;
    /** */
    private String inveId;
    //折算人民币
    private Double rmbInveAmount;
    //选择资金对象，下拉款值导致下面的下拉款变化，其中对应的id
    private String objectId;

    private String objectName;


    private Double intendedAmount;


    private String intendedCurrency;

    private String type;

    private String contact;

    private String contactNo;

    private String accountBank;

    private String accountNo;

    private String ledgerObject;

    private Double fofInveAmount;

    private String fofInveCurr;


    private Double fofInveRmb;

    private String isComplete;


    private String dataType;


}
