package com.ppmg.ei.vo;

import java.util.List;

public class FuncPermissionMetaVO {
    private String title;

    private String icon;

    private Boolean noCache;

    private List<String> roles;

    private List<ButtonsVO> buttons;

    public List<ButtonsVO> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonsVO> buttons) {
        this.buttons = buttons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getNoCache() {
        return noCache;
    }

    public void setNoCache(Boolean noCache) {
        this.noCache = noCache;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
