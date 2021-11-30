package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;
import lombok.Data;

@Data
public class FofInfoModel extends BaseModel {

    private String projId;

    private String projName;

    private String projNo ;

    //被投基金名称
    private String regName;

    //被投基金id
    private String fundId;

    //出资主体名称
    private String inveName;

    //项目来源
    private String projSrc;

    //项目状态
    private String projStatus;

    private String inveId;

    private String projObjectName;

    private String projObject;

}
