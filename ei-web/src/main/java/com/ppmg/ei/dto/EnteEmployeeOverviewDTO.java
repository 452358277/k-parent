package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

@Data
public class EnteEmployeeOverviewDTO extends BaseDTO {


    /** null */
    private String pkId;

    /** 企业编号 */
    private String enterpriseId;

    /** 季度 */
    private String quarter;

    /** 员工人数 */
    private Long totalEmployees;

    /** 兼职人数 */
    private Long partTime;

    /** 博士人数 */
    private Long doctoral;

    /** 硕士人数 */
    private Long master;

    /** 本科人数 */
    private Long bachelor;

    /** 海归人数 */
    private Long returnees;

    /** 其它人员数量 */
    private Long other;

    /** 海外归国人员信息 */
    private String returneesInfo;

    /** 状态（0：正常，1：删除） */
    private String status;

    /** 关联任务号 */
    private String taskId;

    /** 从事研究开发人员数 */
    private Long developer;

    /** null */
    private String projGuid;

    /** 企业外网信息确认 */
    private String confirm;

    /** null */
    private String returneesInfoBak;

    /** 出资主体ID */
    private String investId;

    /** 企业人事推送HANDLE_ID */
    private String handleId;

    /** 是否提供人事数据code 102 */
    private String rsdata;

    /** 无法获取人事信息原因 */
    private String remarkRs;


}
