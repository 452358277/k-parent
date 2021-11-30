package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

@Data
public class ProjAppInfoDTO extends BaseDTO {

    private String fundId;

    private String fundName;

    private String projId;

    /**基金认缴总规模**/
    private Double currentAmount;

    /**当前认缴规模币种**/
    private String currentCurrency;

    /**其中：本基金认缴规模**/
    private String inveCurrentAmount;

    /**决策事项/董监事安排**/
    private String majorMatter;

    /**投资理由**/
    private String inveReason;

    /**投资理由附件**/
    private String  inveReasonAtt;

    /**是否合作**/
    private String isCoo;

    /**申请母基金额度**/
    private String applyAmount;

    /**新增是0，修改是1**/
    private String type;

    private Boolean isStartFlow;

    private String groupId;

    private String inveId;

    private String inveName;

    private String projName;

    private String status;

    private String inveRounds;

    private String finaRounds;

    private Integer finaTimes;

    private String finaCurrency;

    private Double finaAmount;

    private Double preMoney;

    private String preCurrency;

    private Double postMoney;

    private String postCurrency;

    private String inveRole;

    private Double intendedAmount;

    private String intendedCurrency;

    private Long inveShare;

    private Double perShare;

    private String isCorr;

    private String isMeeting;

    private String focusInfo;

    private String focusInfoAtt;

    private String inveType;

    private String isDispatch;

    private String anotherFile;

    private String projAppStatus;

    private String feedBack;
}
