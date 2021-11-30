package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundAllocationVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String allId;

    /** 基金ID */
    private String fundId;

    /** 基金账号余额 */
    private Double accountAmount;

    /** 基金分配时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date allocationDt;

    /** 基金分配金额(万元) */
    private Double allocationAmount;

    /** 其中,分配金财投资金额(万元) */
    private Double allocationJcAmount;

    /** 状态 */
    private String status;

    /** 备注 */
    private String remark;

    /** 附件 */
    private String allocationFile;

    /** 删除标记 */
    private String isDelete;

    /** 排序 */
    private Long sortOrder;

    /** 版本号 */
    private Long rowVersion;

}
