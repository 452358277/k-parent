package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.*;

import java.util.List;
import java.util.Map;

public interface PerfService extends BaseService<PerformanceModel> {

    /**
     * 增加保存，一级，二级，三级指标
     */
    void insertPerManage(PerformanceModel performanceModel);

    void updatePerManage(PerformanceModel performanceModel);
    String getKey(String name);

    /**
     * 查询绩效管理列表一到三级指标详情
     * @param id
     * @return
     */
    public PerformanceModel selectPerfDetailSer(String id);

    public PerformanceModel selectPerfDetailSerYear(String year);
    public PerformanceModel selectPerfDetailSerId(String id);

    /**
     * 根据主表id，删除基金绩效管理列表（级联删除，包括下面的一级，二级，三级指标）
     * @param pPerId
     * @return
     */
    public void deletePerAll(String[] pPerId);

    /**
     * 查询一级指标列表
     * @param pPerId
     * @return
     */
    public List<PerfOneNormModel> selectPerfOneNormModel(String pPerId);
    /**
     * 查询一级指标详情
     * @param oNormId
     * @return
     */
    public PerfOneNormModel selectPerfOneNormDetails(String oNormId);

    /**
     * 更新一级指标
     * @param perfOneNormModel
     */
    public void updatePerfOneNorm(PerfOneNormModel perfOneNormModel);
    /**
     * 插入：PERF_ONE_NORM:一级指标表
     * @param perfOneNormModel
     */
    public Integer insertOneNorm(PerfOneNormModel perfOneNormModel);

    /**
     * 根据id删除一指标
     * @param oNormId
     */
    public void delectOneNorm(String oNormId);
    //-----------------二级指标-------------------------

    /**
     * 查询二级指标列表
     * @param oNormId
     * @return
     */
    public List<PerfSecondNormModel> selectPerfSecondNormModel(String oNormId);
    /**
     * 查询二级指标详情
     * @param sNormId
     * @return
     */
    public PerfSecondNormModel selectPerfSecondDetails(String sNormId);
    /**
     * 更新二级指标
     * @param perfSecondNormModel
     */
    public void updatePerfSecondNorm(PerfSecondNormModel perfSecondNormModel);
    /**
     * 插入：PERF_SECOND_NORM:二级指标表
     * @param perfSecondNormModel
     */
    public Integer insertSecondNorm(PerfSecondNormModel perfSecondNormModel);

    /**
     * 根据id删除二指标
     * @param sNormId
     */
    public void delectSecondNorm(String sNormId);
    //-----------------三级指标-------------------------
    /**
     * 查询三级指标列表
     * @param sNormId
     * @return
     */
    public List<PerfThreeNormModel> selectPerfThreeNormModel(String sNormId);
    /**
     * 查询三级指标详情
     * @param tNormId
     * @return
     */
    public PerfThreeNormModel selectPerfThreeNormDetails(String tNormId);
    /**
     * 更新三级指标
     * @param perfThreeNormModel
     */
    public void updatePerfThreeNorm(PerfThreeNormModel perfThreeNormModel);
    /**
     * 插入：PERF_THREE_NORM:三级指标表
     * @param perfThreeNormModel
     */
    public Integer insertThreeNorm(PerfThreeNormModel perfThreeNormModel);
    /**
     * 根据id删除三指标
     * @param id
     */
    public void delectThreeNorm(String id);

    /**
     * 查询审核，自评列表
     * @param name
     * @param year
     * @return
     */
    public List<PerformanceModel> selectReviewSelfReviewLists(String name,String year);

    /**
     * 基金绩效考核审核管理，列表分页查询-
     * @param var1
     * @return
     */
    public PageInfo<PerformanceModel> selectListPageAssess1(SearchCondition var1);
    /**
     * 审核，自评详细列表分页查询
     * @param var1
     * @param type:0:自评；1：审核
     * @param userId:登录用户id
     * @return
     */
    public PageInfo<PerfScoreModel> selectListPageAssess(SearchCondition var1,String type,String userId);

    /**
     * 审核，自评详细列表详情
     * @param var1
     * @return
     */
    public PerformanceModel selectPerfFundDetail(SearchCondition var1,String psId);

    public void insertPerfScores(PerfScoreModel perfScoreModel);

    /**
     * 插入分数表，自评，审核
     * @param
     * @return
     */
    //public void insertPointLists(String[] st,String psId,String fundId,String pPerId,String flag);
    //PerfScoreModel
    public void insertPointLists(PerfScoreModel perfScoreModel);

    /**
     *
     * 查询考核基金
     * @return
     * @author zhaokuiyu
     * @date 2019/12/12 16:01
     * @creed: The best time to plant a tree is ten years ago, followed by now
     */
    public List<FundInfoModel> selectFundLists();

    /**
     *
     * @param performanceModel
     * @return
     */
    public List<PerformanceExportModel> selectPerformanceExportList(PerformanceModel performanceModel);

    public Map<String, List<String[]>> selectPerformanceExport(SearchCondition searchCondition, String pPerId, String[] title);

}
