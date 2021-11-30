package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.AppFuncPermissionModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class AppFuncJcVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    private List<AppFuncPermissionModel> appFuncPermissionModel;
    /** 主键 */
    private long id;

    /** 名称 */
    private String name;

    /** URL */
    private String url;

    /** 描述 */
    private String description;

    /** 父节点 */
    private Long fatherid;

    /** 排序 */
    private Long sortorder;

    /** 权限类型 */
    private String fptype;

    /** js方法名称 */
    private String jsMethod;

    /** 图标 */
    private String iconCls;

    /** 按钮作用范围，参见码表设计 */
    private String btnScope;

    /** null */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date createddate;

    /** null */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date modifieddate;

    /** null */
    private String guid;

    /** null */
    private String position;

    /** null */
    private String filterCon;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFatherid() {
        return fatherid;
    }

    public void setFatherid(Long fatherid) {
        this.fatherid = fatherid;
    }

    public Long getSortorder() {
        return sortorder;
    }

    public void setSortorder(Long sortorder) {
        this.sortorder = sortorder;
    }

    public String getFptype() {
        return fptype;
    }

    public void setFptype(String fptype) {
        this.fptype = fptype;
    }

    public String getJsMethod() {
        return jsMethod;
    }

    public void setJsMethod(String jsMethod) {
        this.jsMethod = jsMethod;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getBtnScope() {
        return btnScope;
    }

    public void setBtnScope(String btnScope) {
        this.btnScope = btnScope;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFilterCon() {
        return filterCon;
    }

    public void setFilterCon(String filterCon) {
        this.filterCon = filterCon;
    }

    public List<AppFuncPermissionModel> getAppFuncPermissionModel() {
        return appFuncPermissionModel;
    }

    public void setAppFuncPermissionModel(List<AppFuncPermissionModel> appFuncPermissionModel) {
        this.appFuncPermissionModel = appFuncPermissionModel;
    }
}
