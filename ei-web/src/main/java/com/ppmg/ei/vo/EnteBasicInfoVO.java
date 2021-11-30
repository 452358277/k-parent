package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EnteBasicInfoVO extends BaseVO {

    /** null */
    private String pkId;

    /** 企业id */
    private String enteId;

    /** 项目投资时就业人数 */
    private String inveEmployment;

    /** 就业人员 */
    private String employment;

    /** 授权专利数 */
    private String empowerPatent;

    /** 未授权专利数 */
    private String noEmpowerPatent;

    /** 上年度纳税额 */
    private String lastTaxAmount;

    /** 上年度预计纳税额 */
    private String lastEstimateTaxAmount;

    /** 年度纳税额 */
    private String yearTaxAmount;

    /** 年度 */
    private String year;

    /** 附件 */
    private String file;

    /** 描述 */
    private String desc;

    /** 其他时间-备用字段 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date fillingDate;

}
