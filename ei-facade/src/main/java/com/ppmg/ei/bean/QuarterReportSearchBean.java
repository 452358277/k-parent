package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;

public class QuarterReportSearchBean extends BaseSearchBean {
    private String keyword;

    private String quarter;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
}
