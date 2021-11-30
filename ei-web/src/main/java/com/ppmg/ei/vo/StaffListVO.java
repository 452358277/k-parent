package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ProjInfoModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StaffListVO extends BaseVO {

    /** null */
    private String pkId;

    /** 项目 */
    private String projId;

    /** 被投对象编号 */
    private String objectId;

    /** 对应元禾内部员工号 */
    private String userId;

    /** 是否董事成员（0：否，1：是） */
    private String isDirector;

    /** 是否监事成员（0：否，1：是） */
    private String isSupervisor;

    /** 是否高管 */
    private String isTopManage;

    /** 咨询委员会成员（0：否，1：是） */
    private String isAdvisory;

    /** 观察员（0：否，1：是） */
    private String isObserver;

    /** 投委会委员（0：否，1：是） */
    private String isIc;

    /** 任职起始时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date jobStartDate;

    /** 任职结束时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date jobEndDate;

    /** 是否领取报酬（0：否，1：是） */
    private String isReward;

    /** 状态（0：正常，1,：离任） */
    private String status;

    /** 备注(委派人员职位) */
    private String remark;

    /** 人员名字 */
    private String userName;

    /** null */
    private String projGuid;

    /** 委派人员类型 */
    private String userType;

    /** 委派人员类型 */
    private String userTypeName;

    /** 人员委派安排ID */
    private String assignId;

    private String Type;

    private String  statusName;

    private String  projStatusName;

    private ProjInfoModel projInfoModel;

    private String taskId;

    private String processInstId;
}
