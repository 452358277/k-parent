package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.*;
import lombok.Data;

import java.util.List;

@Data
public class FundCgVO extends BaseVO {

    private FundInfoModel model;

    private  EntBaseInfoModel entBase;

    private FundInveDescModel fundDesc;

    private ProjInfoModel projModel;


    private String memberName;

    private String memberId;

    private String oldSubPlatPropName;

    private String fundStructName;


    private String manDepName;

    private String currentCurrencyName;

    private String rcdAddName;

    private String rcdAdd;

    private String recordInfo;

    private String pkId1;
    private String pkId2;

    private String fundSts;

    private String oldFundGenre;

    private String oldFundGenreName;


    private String   fundStsName;

    private String   fundCodeTypeName;

    private String inveTypeName;


    private String fundCategoryName;


    private String govFundSrcName;

    private String organStruct;

    private String organStructName;

    private String isRecord;

    private String appDepName;

    private String isRecordName;


    private String adminNames;

    private String  adminIds;

    private List<FundViewModel> listView;

    private String  otherFile;
}
