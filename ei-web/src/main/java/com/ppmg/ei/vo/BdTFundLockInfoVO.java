package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

@Data
public class BdTFundLockInfoVO extends BaseVO {

    /** null */
    private String lockId;

    /** null */
    private String fundId;

    /** null */
    private String lockFile;

    /** null */
    private String remark;

    /** null */
    private String status;

    /** null */
    private String lockName;

    private String updateD;
}
