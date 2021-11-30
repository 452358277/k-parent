package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundFofInfoModel extends BaseModel {
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
   //出资人
    private String inveName;
    //管理人
    private String mcName;
    //管理人
    private String mcId;
    //出资人代表name
    private String repName;

    //出资人代表id
    private String repId;
    //实缴规模
    private Double raiseAmount;
    //认缴规模
    private Double currentAmount;


    private String   percent;


    private String isRecord;

    private String isLead;


    private String invePeriod;


    private String oldSubPlatProp;

    private String fundSts;

    /** 基金注册日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date setupDt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date upDt;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

    /** 基金形式（合伙制、公司制、契约制） */
    private String fundStruct;

    private String projGs;

    private String fundGs;


    private ProjInfoModel  projInfo;




}
