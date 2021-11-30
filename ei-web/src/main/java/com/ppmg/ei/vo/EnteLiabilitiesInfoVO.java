package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EnteLiabilitiesInfoVO extends BaseVO {


    private static final long serialVersionUID = 1L;

    /** null */
    private String pkId;

    /** 企业编号 */
    private String enterpriseId;

    /** 货币类型 */
    private String currencyType;

    /** 借款总额 */
    private Double borrowAmount;

    /** 借款方 */
    private String borrower;

    /** 借款期限(月) */
    private Long borrowTerm;

    /** 年利率（%） */
    private Double annualRate;

    /** 担保方式 */
    private String guarantyStyle;

    /** 借款时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date borrowStartDt;

    /** 到期时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date borrowEndDt;

    /** 状态（0：正常，1：删除） */
    private String status;

    /** 企业外网信息确认 */
    private String confirm;

    /** null */
    private String projGuid;

    /** null */
    private String borrowTermBak;

    /** null */
    private String annualRateBak;

    private String  guarantyStyleName;
}
