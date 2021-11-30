package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FundInfoExportModel extends BaseModel {

    private String fundId;

    private String regName;

    private String mcName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date setupDt;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

    private String cooperationYear;


    private String invePeriod;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date inveStartDate;


    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date inveEndDate;


    private Double currentAmount;

    private Double raiseAmount;

    private Double jcCurrentAmount;

    private Double jcCurrentAmountOne;

    private String projGs;

    private String enteGs;


    private String fundGs;

    private String provinceGs;

    private Double provinceAmount;

    private String ssGs;

    private String ipoGs;

    private String newBoardGs;

    private String oldSubPlatProp;

    private String projId;

    private String  projStatus;

    private String  projStatusName;

    //母基金-金财公司实缴
    private Double  jcRaiseAmount;
    //参股金财公司实缴
    private Double  jcRaiseAmountOne;


    private String projAmount;

    private String inveName;


    private FundQuitInveInfoModel quitInveInfoModel;

}
