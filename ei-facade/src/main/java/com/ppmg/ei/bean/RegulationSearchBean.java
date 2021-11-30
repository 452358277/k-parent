package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;
import lombok.Data;

@Data
public class RegulationSearchBean extends BaseSearchBean {

    private String fundId;

    private String groupId;

    private String isFit;
}
