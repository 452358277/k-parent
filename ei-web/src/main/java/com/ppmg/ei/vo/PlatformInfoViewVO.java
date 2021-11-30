package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.founder.uim.xdk.AppUser;
import com.ppmg.ei.model.AppUserModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class PlatformInfoViewVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String pkId;

    /** 平台名称 */
    private String platName;

    /** 平台简称 */
    private String platShortName;

    /** 平台代码（对应码表266父键的code_name值） */
    private String platCode;

    /** 统一社会信用代码 */
    private String creditCode;

    /** 平台性质 */
    private String platType;

    /** 平台性质 */
    private String platTypeName;

    /** 注册资本 */
    private String registCapi;

    /** 成立日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date stepDate;

    /** 法定代表人 */
    private String operName;

    /** 住所详细地址 */
    private String addressDetails;

    /** 企业ID（与平台代码字段相同） */
    private String entId;

    /** 管理基金规模 */
    private String rmbCurrentAmount;

    /** 元禾控股在管理公司中持股比例 */
    private String yhRatioInMc;

    /** 主要高管集合 */
    private List<AppUserVO> djgUserList;

    /** 扩展字段1-（保存高管人员ID，逗号分隔） */
    private String ptpiExtendOne;

    private String isEdit;

    protected String updateBy;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JSONField(
            format = "yyyy-MM-dd"
    )
    protected Date updateDt;

    /** 董事长 */
    private String president;

    /** 董事 */
    private String director;

    /** 监事 */
    private String Supervisor;

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSupervisor() {
        return Supervisor;
    }

    public void setSupervisor(String supervisor) {
        Supervisor = supervisor;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getPlatShortName() {
        return platShortName;
    }

    public void setPlatShortName(String platShortName) {
        this.platShortName = platShortName;
    }

    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Date getStepDate() {
        return stepDate;
    }

    public void setStepDate(Date stepDate) {
        this.stepDate = stepDate;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getRegistCapi() {
        return registCapi;
    }

    public void setRegistCapi(String registCapi) {
        this.registCapi = registCapi;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getPtpiExtendOne() {
        return ptpiExtendOne;
    }

    public void setPtpiExtendOne(String ptpiExtendOne) {
        this.ptpiExtendOne = ptpiExtendOne;
    }

    public String getRmbCurrentAmount() {
        return rmbCurrentAmount;
    }

    public void setRmbCurrentAmount(String rmbCurrentAmount) {
        this.rmbCurrentAmount = rmbCurrentAmount;
    }

    public String getYhRatioInMc() {
        return yhRatioInMc;
    }

    public void setYhRatioInMc(String yhRatioInMc) {
        this.yhRatioInMc = yhRatioInMc;
    }

    public List<AppUserVO> getDjgUserList() {
        return djgUserList;
    }

    public void setDjgUserList(List<AppUserVO> djgUserList) {
        this.djgUserList = djgUserList;
    }

    public String getPlatTypeName() {
        return platTypeName;
    }

    public void setPlatTypeName(String platTypeName) {
        this.platTypeName = platTypeName;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
}