package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;
import lombok.Data;

import java.util.List;

@Data
public class ProjInfoSearchBean extends BaseSearchBean {

    private String projStatus;

    private String keyword;

    private String inveType;

    private String name;

    private String  inveId;

    private String inveName;

    //项目名称
    private String projName;

    private String isJs;

    private String regName;

    private String fundName;

    private String projProperty;

    private List<String> ids;

    private List<String> isDecisionPassiDS;

    private String isDecisionPass;

    private String isFit;

    private List<String> isFitIds;

    private String fundSrc;

    private String projType;

    private  String fundId;

    private  String fundStruct;

    private  String subPlatProp;

    private  String label;

    private String platProp;


    private List<String> plat;

    private List<String> fundSt;

    private List<String> subPlat;

     private String status;

     private String labelIds;

     private List<String> labelId;

     private String fundGenre;

    private  List<String> fundGenres;

    private String groupId;

    private String projSrc;

    private String isPublicComp;

    private String fundSts;


    private String isDirect;


    private String lableId;

    private String twoFundName;

    //统计时间类型
    private String timeType;

    //开始时间
    private String beginTime;
//结束时间
    private String endTime;

    private String year;

    private String industry;

}
