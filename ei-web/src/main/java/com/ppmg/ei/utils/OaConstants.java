package com.ppmg.ei.utils;

public interface OaConstants {

    /**
     * 审计项目形式 ---2640
     */
    String AUDIT_PROJECT_FORM = "2640";
    /**
     * 审计结果类型---- 2650
     */
    String AUDIT_RESULT_TYPE = "2650";
    /**
     * 是否 --- 9013
     */
    String YES_OR_NO = "9013";

    /**
     * 风险等级 ---416
     */
    String RISK_GRADE = "416";

    /**
     * 整改效果
     */
    String RECTIFICATION_EFFECT = "2660";

    /**
     * 部门负责人postId
     */
    Integer DEPARTMENT_MANAGER_POST_ID = 110;

    /**
     * 分管副总postId
     */
    Integer DIVISION_VICE_PRESIDENT_POST_ID = 103;

    /**
     * 管理层(元禾控股-总裁室里的人员)postId
     */
    Integer MANAGERS_GROUP_ID = 1001;

    /**
     * 是否删除(0:否;1:是)
     */
    String IS_DELETE_N = "0";
    String IS_DELETE_Y = "1";

    /**
     * dealMark 删除或新增
     */
    String DEAL_MARK_UPDATE = "update";
    String DEAL_MARK_ADD = "add";

    /**
     * version_number 版本号
     * 第一个版本
     */
    Integer VERSION_NUMBER_1 = 1;

    /**
     * 整改效果:已整改
     */
    String RECTIFICATION_EFFECT_COMPLETED = "0";
    /**
     * 整改效果:整改中
     */
    String RECTIFICATION_EFFECT_UNDERWAY = "1";
    /**
     * 整改效果:无法整改
     */
    String RECTIFICATION_EFFECT_UNABLE = "2";
    /**
     * 整改效果:无需整改
     */
    String RECTIFICATION_EFFECT_NEEDLESS = "3";
}
