package com.ppmg.ei.vo;

import java.util.Date;

import com.founder.ssm.core.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.model.BaseModel;

public class PlatformInfoListVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String pkId;

    /** 平台名称 */
    private String platName;

    /** 平台简称 */
    private String platShortName;

    /** 平台代码（对应码表266父键的code_name值） */
    private String platCode;

    /** 平台性质 */
    private String platType;

    /** 平台性质 */
    private String operName;

    /** 注册资本 */
    private String registCapi;

    /** 成立日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date stepDate;

    /** 企业ID（与平台代码字段相同） */
    private String entId;

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

    public String getRegistCapi() {
        return registCapi;
    }

    public void setRegistCapi(String registCapi) {
        this.registCapi = registCapi;
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


}