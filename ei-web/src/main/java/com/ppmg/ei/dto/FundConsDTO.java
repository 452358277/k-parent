package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundConsDTO extends BaseDTO {

    /** 主键 */
    private String consId;

    /** 基金编号 */
    private String fundId;

    /** 归档年月 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date archDt;

    /** 章程及修正内容  */
    private String consContent;

    /** 附件 */
    private String consFiles;

    /** 状态（0：正常，1：删除） */
    private String status;



}
