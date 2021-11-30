package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;

import java.util.List;

/**
 * 合作方机构库查询 [SearchBean]
 * @author syx
 * @date 2019-08-13
 */
public class CooOrgSearchBean extends BaseSearchBean {

    /**  关键字*/
    private String keyWord;

    /** 机构类型 */
    private String orgType;

    private List<String> orgTypes;


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public List<String> getOrgTypes() {
        return orgTypes;
    }

    public void setOrgTypes(List<String> orgTypes) {
        this.orgTypes = orgTypes;
    }
}
