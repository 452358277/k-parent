package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class TzTProjOwnershipInfoVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /** 股权结构信息主键 */
    private String osId;

    /** 项目编号 */
    private String projId;

    /** 记录日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date recordDate;

    /** 股权结构表 */
    private String esSheet;

    /** 验资报告 */
    private String cvRpt;

    /** 状态（0：草稿，1：正常，9：删除） */
    private String status;


    private String fundId;

    private String fundName;


    private String enteId;


    private String enteName;




    private List<ProjShareInfoVO> listVo;

}
