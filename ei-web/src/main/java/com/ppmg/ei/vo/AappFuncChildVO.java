package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class AappFuncChildVO extends BaseVO {
    /** 主键 */
    private long id;

    /** 名称 */
    private String name;

    /** URL */
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
