package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;

import java.util.List;

/**
 * 出资人代表库
 * @author syx
 * @date 2019-08-13
 */
public class AdminInvestSearchBean extends BaseSearchBean {

    private String keyWord;
    /** 组织形式 */
    private String org;

    /** 是否合作过 */
    private String isCoo;

    /** 值 ：rep  是 出资人代表库数据*/
    private String isRep;

    /** 组织形式 */
    private List<String> orgs;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getIsCoo() {
        return isCoo;
    }

    public void setIsCoo(String isCoo) {
        this.isCoo = isCoo;
    }

    public String getIsRep() {
        return isRep;
    }

    public void setIsRep(String isRep) {
        this.isRep = isRep;
    }

    public List<String> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<String> orgs) {
        this.orgs = orgs;
    }
}
