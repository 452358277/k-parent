package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.ProjAppInfoModel;
import com.ppmg.ei.model.ProjInfoModel;
import lombok.Data;

@Data
public class BdTFitRegulationSelfVO extends BaseVO {
    /** 项目名称 */
    private String projName;

    /** 主键 */
    private String projId;

    /** 基金ID */
    private String fundId;

    /** 本期投资金额 */
    private Double curTmoney;

    /** 基金对项目累计投资金额 */
    private Double allTmoney;

    /** 本期投资金额占基金认缴出资总额的比例 */
    private Double curTmoneyPer;

    /** 基金对项目累计投资金额占基金认缴出资总额的比例 */
    private Double allTmoneyPer;

    /** 基金对项目的累计出资比例 */
    private Double funToPro;

    /** 基金是否为最大出资人或股东 */
    private String isSholder;

    /** 项目所属行业/领域 */
    private String industry;

    /** 是否属于基金主投领域 */
    private String isMfield;

    /** 已完成投资项目中非主投领域项目合计投资金额 */
    private Double nmainTmoney;

    /** 已完成投资项目中非主投领域项目合计投资金额占本基金认缴出资总额的比例 */
    private Double nmainTmoneyPer;

    /** 项目是否属于江苏省内企业 */
    private String isJs;

    /** 项目属第12条江苏省内企业的何种情形 */
    private String isTwelve;

    /** 基金已完成投资项目属于情形（一）投资金额 */
    private Double tmoneyOne;

    /** 基金已完成投资项目属于情形（一）投资金额占本基金认缴出资总额的比例 */
    private Double tmoneyOnePer;

    /** 基金已完成投资项目属于情形（二）、（三）、（四）投资金额 */
    private Double tmoneyTtf;

    /** 基金已完成投资项目属于情形（二）、（三）、（四）投资金额占本基金认缴出资总额的比例 */
    private Double tmoneyTtfPer;

    /** 基金已完成对江苏省外企业投资项目的总投资金额 */
    private Double outjsTmoney;

    /** 基金已完成对江苏省外企业投资项目的总投资金额占本基金认缴出资总额的比例 */
    private Double outjsTmoneyPer;

    /** 是否关联交易 */
    private String isCorr;

    /** 关联关系说明 */
    private String corrExplain;

    /** 是否列入《政府核准的投资项目目录》 */
    private String isAllow;

    /** 属于《产业结构调整指导目录》何种类别 */
    private String whichClass;

    /** 是否列入《市场准入负面清单草案（试点版》 */
    private String isNlist;

    /** 是否属于第13条所列业务 */
    private String isThirteen;

    /** 有无其他违反本基金协议的情形 */
    private String anyNlist;

    /** 其他需要说明的情况 */
    private String explain;

    /** 自查结论 */
    private String selfConclusion;
    //投决会情况
    private String tjResult;


    private String isStatus;


    private String isStop;

   //已签署投资协议金额
    private Double signAmount;

    private String industryName;

    private String isSpvName;

    /**拟投资金额 */
    private Double intendedAmount;

    /**是否终止投资**/
    private String projStatusName;

    private ProjInfoModel projInfoModel;

    private ProjAppInfoModel projAppInfoModel;

    //是否需要合规性审查
    private String isFitName;

    private String isFit;

    private Double sumActualPayAmount;


    private String regulationFile;

    private String status;

    private Double secTmoneyPer;

    private Double thrTmoneyPer;

    private String isDecisionPass;

    private String flowStatus;

    private String  taskId;

    private String confirmLetterFile;
}
