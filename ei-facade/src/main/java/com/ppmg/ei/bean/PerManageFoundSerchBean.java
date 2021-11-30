package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 花生糖块儿
 */
@ApiModel(value = "绩效管理", description = "查询卡盒的基金searchBean")
public class PerManageFoundSerchBean extends BaseSearchBean {
    @ApiModelProperty(name = "year", value = "考核年度", example = "2020", dataType = "String", required = true)
    private String year;
    @ApiModelProperty(name = "pPerId", value = "主表id", example = "10000", dataType = "String", required = true)
    private String pPerId;
    @ApiModelProperty(name = "touStartTime", value = "考核开始年度", example = "2020-04-04", dataType = "String", required = true)
    private String touStartTime;
    @ApiModelProperty(name = "touEndTime", value = "考核结束年度", example = "2020-04-04", dataType = "String", required = true)
    private String touEndTime;
    @ApiModelProperty(name = "fundStartTime", value = "基金考核开始时间", example = "2020-04-04", dataType = "String", required = true)
    private String fundStartTime;
    @ApiModelProperty(name = "fundEndTime", value = "基金考核结束时间", example = "2020-04-04", dataType = "String", required = true)
    private String fundEndTime;

    @ApiModelProperty(name = "platProp", value = "基金属性", example = "1", dataType = "String", required = true)
    private String platProp;

    public String getPlatProp() {
        return platProp;
    }

    public void setPlatProp(String platProp) {
        this.platProp = platProp;
    }

    public String getpPerId() {
        return pPerId;
    }

    public void setpPerId(String pPerId) {
        this.pPerId = pPerId;
    }

    public String getFundStartTime() {
        return fundStartTime;
    }

    public void setFundStartTime(String fundStartTime) {
        this.fundStartTime = fundStartTime;
    }

    public String getFundEndTime() {
        return fundEndTime;
    }

    public void setFundEndTime(String fundEndTime) {
        this.fundEndTime = fundEndTime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTouStartTime() {
        return touStartTime;
    }

    public void setTouStartTime(String touStartTime) {
        this.touStartTime = touStartTime;
    }

    public String getTouEndTime() {
        return touEndTime;
    }

    public void setTouEndTime(String touEndTime) {
        this.touEndTime = touEndTime;
    }
}
