package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ProjInfoModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundFofInfoVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String fundId;

    /** 基金编号 */
    private String fundCode;

    /** 基金名称 */
    private String fundName;

    /** 基金注册名称 */
    private String regName;


    private String projId;

    private String projName;

    private String inveId;

    private String inveName;

    private String mcName;

    private String mcId;

    private Double raiseAmount;

    private String invePeriod;

    private String fundSts;

    /** 基金注册日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date setupDt;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern="yyy-MM-dd HH:mm:ss")
    @JSONField(format="yyy-MM-dd HH:mm:ss")
    private Date updateDt;

    private String fundStsName;

    //认缴规模
    private Double currentAmount;


    /** 基金形式（合伙制、公司制、契约制） */
    private String fundStruct;

    //出资人代表name
    private String repName;

    private String   percent;


    private String fundStructName;

    private String projGs;

    private String fundGs;

    private String isRecord;

    private String isRecordName;

    private String oldSubPlatProp;


    private String  oldSubPlatPropName;

    private ProjInfoModel projInfo;

    private String isLead;

}
