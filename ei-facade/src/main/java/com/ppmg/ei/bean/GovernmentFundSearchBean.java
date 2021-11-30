package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;
import lombok.Data;

@Data
public class GovernmentFundSearchBean extends BaseSearchBean {

    /** 关键字 */
    private String keyword;

    /** 基金类别 */
    private String platProp;

    /** 基金形式 */
    private String  fundStruct;

    /** 备案情况 */
    private String  isRecord;

    private String subPlatProp;

    private String fundGenre;

    private String year;

    private String quarter;

    private String type;

}
