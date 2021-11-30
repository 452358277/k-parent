package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

@Data
public class ProvincialVO extends BaseVO {

    private Integer oneGxjsqy;

    private Integer  oneYdyl;

    private Integer oneZxqy;

    private Integer oneZlxx;

    private Integer oneMy;

    private Integer twoGxjsqy;

    private Integer  twoYdyl;

    private Integer twoZxqy;

    private Integer twoZlxx;

    private Integer fourGxjsqy;

    private Integer  fourYdyl;

    private Integer fourZxqy;

    private Integer fourZlxx;

    /**基金名称**/
    private String name;

    private String fundId;

    private String enteId;
    /**总数**/
    private Integer oneCount;
    /**省内**/
    private Integer oneCountJs;
    /**本轮投资金额**/
    private Double oneFinaAmount;

    private Double oneIntendedAmount;
    /**本基金**/
    private Double  oneSumSignAmount;
    /**投前估值**/
    private Double onepreMoney;
    /**投后估值**/
    private Double onePostMoney;

    private Double oneActualAmount;

    /**带动其他社会资本
     **/
    private Double oneQtMoney;

    private Double ztPayAmount;

   //**********************************
   //基金规模
   private Double sumCurrentAmount;

    //投资子基金总个数
    private  Integer twoCont;

    //省内
    private  Integer twoCountJs;


    private Double promiseAmount;
    //本基金认缴金额
    private Double inveCurrentAmount;

    //其他机构认缴金额
    private Double qtAmount;

    //基金实缴规模
    private Double raiseAmount;

    //投子基金-已拨付金额
    private Double payAmount;



   // ********************************

    private  Integer fourCont;

    private  Integer fourCountjs;
    //本轮投资总额
    private Double fourFinaAmount;

    private Double fourIntendedAmount;
//本基金投资额
    private Double sumSignAmount;
    //带动其他社会资本
    private Double qtshzbAount;
    //已投项目-已拨付金额
    private Double payYtAmount;
    //投前估值
    private Double fourPreMoney;
    //投后估值
    private Double fourPostMoney;


    /** 基金形式（合伙制、公司制、契约制） */
    private String fundStruct;

    /** 基金一类类别（区域基金、国家基金、产业基金） */
    private String platProp;

    /** 基金二类类别 */
    private String subPlatProp;

    private String fundStructName;

    private String platPropName;

    private String subPlatPropName;

//本基金投资额
    private Double  fourActualAmount;


    private String  fundGenreName;

    private Integer fourMy;
}
