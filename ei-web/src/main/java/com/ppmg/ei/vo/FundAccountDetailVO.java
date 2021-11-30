package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundAccountDetailVO extends BaseVO {

    /** null */
    private String pkId;

    /** 基金id */
    private String fundId;

    /** 备注 */
    private String remark;

    /** null */
    private String fileDesc;

    /** 账户 */
    private String fundAccount;

    /** 分配金额 */
    private Double disAmount;

    /** 分配时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date disData;

    /** 分配金财金额 */
    private Double disJcAmount;

    /** 账户余额 */
    private Double accountBalance;

    /** 基金分配金额 */
    private Double fundAmount;

    /** 附件 */
    private String disFile;

    private String status;


}
