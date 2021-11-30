package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundRelateInfoDTO extends BaseDTO {

    /** 主键 */
    private String relateId;

    /** 基金ID */
    private String fundId;

    /** 资料类型 */
    private String infoType;

    /** 资料附件 */
    private String attaFile;

    /** 归档年月 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date archDt;

    /** 状态（0：正常，1：删除） */
    private String status;

    /** 备注 */
    private String remark;



}
