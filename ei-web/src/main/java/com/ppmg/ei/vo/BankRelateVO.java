package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.LedgerMagModel;
import lombok.Data;

@Data
public class BankRelateVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    /** 基金表主键 */
    private String fundId;

    /** 银行编号 */
    private String bankId;

    /** 银行账户 */
    private String accountName;

    /** 银行帐号 */
    private String accounts;

    /** 资金额度 */
    private Double capitalQuota;

    /** 实到金额 */
    private Double arrivalAmount;

    /** 状态 */
    private String status;

    private String bankName;

    private LedgerMagVO ledgerMagVO;

    private String accountType;

    private String accountTypeName;

    private String oldAccounts;

}
