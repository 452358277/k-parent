package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "码表", description = "码表")
public class CodeVO extends BaseVO {
    /** 代码编码 */
    private String codeId;

    /** 上级代码 */
    private String parentId;

    /** 代码名称
     代码名称
     代码名称 */
    private String codeName;

    /** 代码值 */
    private String codeValue;

    /** 代码描述 */
    private String codeDesc;

    /** 顺序编号 */
    private long orderNo;

    /** 读写标记（0：只读，1：可写） */
    private String rwFlag;

    /** 状态（0：正常，1：禁用） */
    private String status;

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public String getRwFlag() {
        return rwFlag;
    }

    public void setRwFlag(String rwFlag) {
        this.rwFlag = rwFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
