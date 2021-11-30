package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundMethodDTO extends BaseDTO {


    /** 主键 */
    private String methodId;

    /** 基金id */
    private String fundId;

    /** 年月 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date archDt;

    /** 管理办法内容 */
    private String methodContent;

    /** 附件 */
    private String methodFiles;

    /** 状态（0：正常，1：删除） */
    private String status;

    /** 标题 */
    private String title;

    /** 批准文号 */
    private String approvalNum;

}
