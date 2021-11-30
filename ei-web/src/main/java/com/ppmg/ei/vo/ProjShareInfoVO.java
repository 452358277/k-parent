package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

@Data
public class ProjShareInfoVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String pkId;

    /** 关联股权结构信息 */
    private String osId;

    /** 投资人ID */
    private String investorId;

    /** 投资人 */
    private String investorName;

    /** 股比（%） */
    private Double shareRate;

    /** 投资金额 */
    private Double inveAmount;

    /** 金额币种 */
    private String inveCurrency;

    /** 状态（0：正常，1：删除） */
    private String status;

    /** 股东性质 */
    private String investorPro;

    /** 承诺出资额 */
    private Double planAmount;
    /** 占总认缴比例 */
    private Double totalRate;
    /** 累计出资总额 */
    private Double paidAmount;
    /** 出资进度 */
    private Double investRate;

    private String investmentAttributes;

    private String investmentAttributesName;

    private String investorProName;


    private String enteId;

    private String enteName;

}
