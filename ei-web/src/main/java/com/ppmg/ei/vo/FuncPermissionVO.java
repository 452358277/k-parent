package com.ppmg.ei.vo;

import java.util.List;

public class FuncPermissionVO {
    private String path;

    private String component;

    private String redirect;

    private Boolean alwaysshow;

    private String name;

    private String funcType;

    private FuncPermissionMetaVO meta;

    private List<FuncPermissionVO> children;

    private Long id;

    private Long fatherId;

    private FuncBsVO funcBsvo;

    public FuncBsVO getFuncBsvo() {
        return funcBsvo;
    }

    public void setFuncBsvo(FuncBsVO funcBsvo) {
        this.funcBsvo = funcBsvo;
    }

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

    public Boolean getAlwaysshow() {
        return alwaysshow;
    }

    public void setAlwaysshow(Boolean alwaysshow) {
        this.alwaysshow = alwaysshow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFuncType() {
        return funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType;
    }

    public FuncPermissionMetaVO getMeta() {
        return meta;
    }

    public void setMeta(FuncPermissionMetaVO meta) {
        this.meta = meta;
    }

    public List<FuncPermissionVO> getChildren() {
        return children;
    }

    public void setChildren(List<FuncPermissionVO> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }
}
