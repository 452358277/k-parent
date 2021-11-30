package com.ppmg.ei.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ProjInfoExportModel extends BaseModel {

    private String inveId;


    private String projName;

    private String projId;

    private String projObject;

    private String inveName;

    private String enteName;


    /** 项目立项时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date setupDt;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    private String addressDetails;

    private String regAdd;

    private String registCapi;


    private Double regAmount;

    private Double intendedAmount;

    private Double rmbIntendedAmount;

    private Double perShare;

    private String percent;

    private String inveRounds;

    private String finaRounds;

    private String finaTimes;

    private String regAmountCurrency;


    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date occurDt;

    private String inveType;

    private String industryName;

    private String provinceAmount;

    private String isPrivate;

    private String isTechnology;

    private String isStrategy;

    private String isDirectorCnt;

    private String isSupervisorCnt;

    private String postMoney;

    private String entePostValue;

    private String inveNewValue;

    private String holdShare;


    private String isPublicComp;

    private String isProvince;


    private String   phase;

    private String hlightsRemark;


    private String quitRmbInveAmount;

    private String quitSy;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date quitDt;

    private String exitType;

    private String  yearStr;

    private List<FundEnteModel> listM;


}
