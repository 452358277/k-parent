package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ProjAppInfoModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

@Data
public class ProjInfoInternalVO extends BaseVO {
    /** 主键 */
    private String projId;

    /** 项目编号 */
    private String projNo;

    /** 项目名称 */
    private String projName;

    /** 项目类型（1：投企业，2：投基金，3:子基金项目） */
    private String projType;

    /** 项目状态（2：储备项目，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
    private String projStatus;

    /** 被投对象 */
    private String projObject;

    /** 被投对象名称 */
    private String projObjectName;

    /** 所投企业当前所属阶段 */
    private String entePhase;

    /** 项目来源 */
    private String projSrc;

    /** 来源说明 */
    private String srcDesc;

    /** 出资主体编号 */
    private String inveId;

    /** 出资主体名称 */
    private String inveName;

    //累计出资
    private String sumActualPayAmount;

    //注册时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateDt;


    /** 投资类型**/
    private String inveType;

    private String inveTypeName;

    /** 投资角色 */
    private String inveRoleName;


    private ProjAppInfoModel projAppInfoModel;

    private String projSrcName;


    private Map<String,Object> mapName;

    //累计回收金额
    private String sumRaiseAmount;

    //收益
    private String sumCurrentAmount;

    private Double sumInveAmount;








}
