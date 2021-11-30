package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ProjContractFileModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ProjContractFileInfoVO extends BaseVO {


    /** null */
    private String id;

    /** null */
    private String projId;

    /** null */
    private String remark;

    /** null */
    private String projName;

    /** null */
    private String inveName;

    /** null */
    private String inveId;

    /** null */
    private String projFile;

    /** null */
    private String founceInfo;

    private String fileTypeName;


    private String  statusName;

    private String  signAmountCurrName;

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String fileId;


    /** 文件类别 */
    private String fileType;

    /** 文件名称 */
    private String fileTitle;

    /** 所属模块（1：项目执行-合同列表，2：项目执行-其他审批文件，3：投后-日常监控-合同，4：投后-退出管理-合同） */
    private String firstParty;

    /** 乙方（弃用） */
    private String secondParty;

    /** 起草人 */
    private String draftsman;

    /** 起草日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date draftDt;

    /** 董事席数 */
    private Long directorCount;

    /** 元禾董事席数 */
    private Long yhDireCount;

    /** 监事席数 */
    private Long supervisorCount;

    /** 元禾监事席数 */
    private Long yhSupeCount;

    /** 合同文件 */
    private String contractFile;

    /** 最终版合同 */
    private String finalFile;

    /** 合同状态（0：草稿，1：审核中，2：已审核，3：已签订，4：无效，9：删除） */
    private String status;

    /** 流程实例 */
    private String processInstId;

    /** null */
    private String projGuid;

    /** null */
    private String processversioninstanceguid;

    /** null */
    private String svgPkGuid;

    /** null */
    private String svgAttaGuid;

    /** 合同签订时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date signDt;

    /** 合同签订金额 */
    private Double signAmount;

    /** 合同签订金额币种 */
    private String signAmountCurr;

    /** 签订金额折算为人民币 */
    private Double signAmountRmb;

    /** 出资主体ID */
    private String afterFunderId;

    /** 最终协议金额 */
    private Double endAgreeAmont;

    /** 最终协议金额币种 */
    private String endAgreeAmontCurr;

    /** 最终协议金额折算为人民币 */
    private Double endAgreeAmontRmb;

    /** 本协议金额 */
    private Double thisAgreeAmont;

    /** 本协议金额币种 */
    private String thisAgreeAmontCurr;

    /** 本协议金额折算为人民币 */
    private Double thisAgreeAmontRmb;

    /** 投前项目ID */
    private String projIdPi;

    /** 测试ID */
    private String csid;

    /** 企业ID */
    private String objectId;

    /** 企业名称 */
    private String projObjectName;

    /** 所属平台 */
    private String groupId;

    /** 占股比 */
    private Double shareRatio;

    /** 当前子基金规模 */
    private Double subFundAmont;

    /** 当前子基金规模币种 */
    private String subFundAmontCurr;

    /** 当前子基金规模人民币 */
    private Double subFundAmontRmb;

    /** 当前认缴规模 */
    private Double currentAmount;

    /** 当前认缴规模币种 */
    private String currentAmountCurr;

    /** 当前认缴规模人民币 */
    private Double currentAmountRmb;

    /** 当前认缴占比 */
    private Long currentRatio;

    private String majorTerm;

    private String fileInfoId;



    private List<ProjContractFileModel> list;

}
