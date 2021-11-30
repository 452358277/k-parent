package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BdTFundLockInfoDTO extends BaseDTO {


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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    protected Date changeDt;

}
