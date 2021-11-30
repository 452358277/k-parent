package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;

public class FuncBsVO extends BaseVO {

    private String path;

    private String component;

    private String redirect;

    private Boolean hidden;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
