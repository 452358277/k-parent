package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ProjAppDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 项目 */
    private String projId;

    /** 流程实例号 */
    private String processInstId;

    /** 被投对象融资轮数 */
    private String finaRounds;

    /** 被投对象融资次数 */
    private String finaTimes;

    /** 被投对象融资金额 */
    private Double finaAmount;

    /** FINA_CURRENCY */
    private String finaCurrency;

    /** 投资角色 */
    private String inveRole;

    /** 投资类型 */
    private String inveType;

    /** 投资轮次 */
    private String inveRounds;

    /** Pre_Money */
    private Double preMoney;

    /** PRE_CURRENCY */
    private String preCurrency;

    /** Post_Money */
    private Double postMoney;

    /** POST_CURRENCY */
    private String postCurrency;

    /** 拟投资金额 */
    private Double intendedAmount;

    /** 拟投资币种 */
    private String intendedCurrency;

    /** 购买股数 */
    private Long inveShare;

    /** 占股比（%） */
    private Double perShare;

    /** null */
    private String projGuid;

    /** 折算为人民币 */
    private Double rmbIntendedAmount;

    /** 尽职调查重点/其它重点关注信息 */
    private String focusInfo;

    /** 投资理由 */
    private String inveReason;

    /** 决策事项/董监事安排 */
    private String majorMatter;

    /** null */
    private String remark;

    /** 尽职调查重点/其它重点关注信息附件 */
    private String focusInfoAtt;

    /** 投资理由附件 */
    private String inveReasonAtt;

    /** 决策事项/董监事安排附件 */
    private String majorMatterAtt;

    /** 备注附件 */
    private String remarkAtt;

    /** 立项问题反馈 */
    private String feedBack;


    private String projType;

    /** 投决是否通过**/
    private String isDecisionPass;


    /**投决会召开时间**/
    private Date decisionDate;


    /**本基金认缴规模**/
    private Double promiseAmount;


    /**本基金投资额**/
    private Double  actualAmount;

    private Double  allTmoney;

    /**基金**/
    private String  projObject;

    /**出资主体**/
    private String inveId;

    /**出资主体名称**/
    private String inveName;

    /**决策附件**/
    private String  decisionFile;

    /**标签数组**/
    private String[] ids;

    private String entId;

    private Double inveCurrentAmount;

    private Double inveRaiseAmount;

}
