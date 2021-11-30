package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;

/**
 * 外部用户 [SearchBean]
 * @author yuyongjun
 * @date 2019-06-24 16:17
 */
public class AppUserSearchBean extends BaseSearchBean {
    private static final long serialVersionUID = 1L;

    /** 关键字 */
    private String keyword;

    private String fundName;

    private Long  userType;

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
