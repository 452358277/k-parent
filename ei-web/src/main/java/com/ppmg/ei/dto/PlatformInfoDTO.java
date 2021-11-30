package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PlatformInfoDTO extends BaseDTO {
    /** 主键 */
    private String pkId;

    /** 平台名称 */
    private String platName;

    /** 平台简称 */
    private String platShortName;

    /** 平台性质 */
    private String platType;

    /** 成立日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date stepDate;

    /** 扩展字段1-（保存高管人员ID，逗号分隔） */
    private String ptpiExtendOne;

    /** 平台性质 */
    private String entId;

    private String delFlag;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType;
    }

    public String getPtpiExtendOne() {
        return ptpiExtendOne;
    }

    public void setPtpiExtendOne(String ptpiExtendOne) {
        this.ptpiExtendOne = ptpiExtendOne;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public Date getStepDate() {
        return stepDate;
    }

    public void setStepDate(Date stepDate) {
        this.stepDate = stepDate;
    }
}
