package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DataQuarterItemVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String itemId;

    /** 填报任务主键 */
    private String taskId;

    /** 业务表名 */
    private String bizTable;

    /** 业务表主键名 */
    private String bizKey;

    /** 业务表主键值 */
    private String bizKeyValue;

    /** 状态(码值：0未提交，1已提交) */
    private String status;

    /** 提交时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date submitDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date endDate;

    /** 删除标记 */
    private String isDelete;

    /** 排序 */
    private Long sortOrder;

    /** 版本号 */
    private Long rowVersion;

    private String dataType;


    private String taskTitle;


    private String closeDate;


    private String enteId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date createDt;

    private String createDtStr;

}
