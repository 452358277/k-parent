package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import com.ppmg.ei.model.RegulationApproveCommModel;
import lombok.Data;

import java.util.List;


@Data
public class RegulationApproveDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String iraId;

    /** 企业注册状态(码值：) */
    private String entStatus;

    /** 被投企业ID */
    private String entId;

    /** 被投企业名称 */
    private String entName;

    /** 企业类型(码值：) */
    private String financeDate;

    /** 出资主体ID */
    private String inveId;

    /** 出资主体名称 */
    private String inveName;

    /** 投资金额 */
    private Double inveAmt;

    /** 所属行业 */
    private String industry;

    /** 所属行业名称 */
    private String industryName;

    /** 说明 */
    private String remark;

    /** 附件 */
    private String attaFile;

    /** 项目ID */
    private String projId;

    /** 项目名称 */
    private String projName;

    /** 删除标记 */
    private String isDelete;


    /** 是否发布流程 */
    private Boolean isStartFlow;

    //0草稿
    private String processStatus;

    private String commFile;



    private List<RegulationApproveCommModel> list;

}
