package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="企业库信息列表查询对象searchBean",description="企业库信息列表查询对象searchBean")
public class entBaseInfoSearchBean extends BaseSearchBean {
    private static final long serialVersionUID = 1L;

    /** 关键字 */
    @ApiModelProperty(name="keyword", value="关键字", example="辰坤", dataType="String", required=false)
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
