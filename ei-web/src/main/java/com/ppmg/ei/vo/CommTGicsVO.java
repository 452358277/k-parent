package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.CommTGicsModel;

import java.util.List;

public class CommTGicsVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    /** 行业编码 */
    private String id;

    /** 上级编码 */
    private String parentId;

    /** 行业名称 */
    private String gicsName;

    /** 行业级别 */
    private String gicsLevel;

    /** 状态(0：正常，1：禁用) */
    private String status;

    /** 顺序编号 */
    private Long orderNo;

    private List<CommTGicsVO> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getGicsName() {
        return gicsName;
    }

    public void setGicsName(String gicsName) {
        this.gicsName = gicsName;
    }

    public String getGicsLevel() {
        return gicsLevel;
    }

    public void setGicsLevel(String gicsLevel) {
        this.gicsLevel = gicsLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public List<CommTGicsVO> getChildren() {
        return children;
    }

    public void setChildren(List<CommTGicsVO> children) {
        this.children = children;
    }
}
