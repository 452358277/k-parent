package com.ppmg.ei.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.founder.ssm.core.bean.BaseSearchBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="平台信息列表查询对象searchBean",description="平台信息列表查询对象searchBean")
public class PlatformInfoSearchBean extends BaseSearchBean{

    private static final long serialVersionUID = 1L;

    /** 关键字 */
    @ApiModelProperty(name="keyword", value="关键字", example="法定代表人", dataType="String", required=false)
    private String keyword;


    /** 成立时间开始 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(name="startTime", value="成立时间开始", example="2018-01-01", dataType="Date", required=false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 成立时间结束 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(name="endTime", value="成立时间结束", example="2018-01-01", dataType="Date", required=false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date endTime;

    /** 最高学历 */
    @ApiModelProperty(name="platType", value="平台性质", example="1", dataType="String", required=false)
    private String platType;



    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType;
    }


}
