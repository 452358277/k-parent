package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import com.ppmg.ei.model.LedgerMagModel;
import lombok.Data;

import java.util.List;

@Data
public class ShareInfoDTO extends BaseDTO {


    /** 主键 */
    private String pkId;

    /** 基金编号 */
    private String fundId;

    /** 投资人ID */
    private String investorId;

    /** 投资角色(1:LP,2:GP,3:GM) */
    private String inveRole;

    /** 认缴规模 */
    private Double inveAmount;

    /** 认缴金额币种 */
    private String inveCurrency;

    /** 折算人民币 */
    private Double rmbInveAmount;


    private String rmbIntendedAmount;


    /** 投资人名称 */
    private String investorName;


    private String inveProperty;

    /** 投资人属性 */
    private String investorType;

    private String status;


    private String inveNature;


    private String projId;



    private List<LedgerMagModel> list;


    private String oldInvestorId;


}
